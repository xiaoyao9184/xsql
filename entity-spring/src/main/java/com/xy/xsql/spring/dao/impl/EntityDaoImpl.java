package com.xy.xsql.spring.dao.impl;

import com.xy.xsql.entity.api.dialect.type.TypeMapper;
import com.xy.xsql.entity.api.dialect.render.jsql.*;
import com.xy.xsql.entity.core.template.EAnnotationEntityInfoBuilder;
import com.xy.xsql.entity.core.template.EntityColumnFilter;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityColumn;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;
import com.xy.xsql.spring.config.*;
import com.xy.xsql.spring.dialect.DialectType;
import com.xy.xsql.spring.dialect.TemplateRendererFactory;
import com.xy.xsql.spring.dialect.TypeMapperFactory;
import com.xy.xsql.spring.mapping.BaseEntityRowMapper;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import com.xy.xsql.spring.mapping.MappingFieldRowNameHandler;
import com.xy.xsql.spring.dao.EntityDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class EntityDaoImpl<Entity, ID>
        implements EntityDao<Entity, ID> {

    /**
     * LOG
     */
    protected Log log;

    @Resource
    protected DialectConfiguration dialectConfiguration;

    @Resource
    protected JdbcTemplate jdbcTemplate;

    protected EntityInfo entityInfo;

    protected TemplateCRUDRenderer entityCRUDSql;

    protected TypeMapper<Class<?>,String> typeMapper;

    protected RowMapper<Entity> rowMapper;
    
    protected EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder;

    protected EntityColumnsArgsBuilder<Entity> entityIdsArgBuilder;

    protected List<FieldRowNameHandler> fieldRowNameHandlerList;

    @SuppressWarnings({"Convert2Diamond", "unchecked"})
    @PostConstruct
    public void init() throws Exception {
        this.log = LogFactory.getLog(this.getClass());
        this.fieldRowNameHandlerList = new ArrayList<>();
        this.fieldRowNameHandlerList.add(new MappingFieldRowNameHandler());

        EntityDaoInit entityDaoInit = null;
        if(this instanceof EntityDaoInit){
            entityDaoInit = (EntityDaoInit)this;
        }

        if(entityDaoInit != null){
            entityDaoInit.beforeInit();
        }

        initDialect();
        if(entityDaoInit != null){
            entityDaoInit.afterDbDialectInit();
        }

        initEntity();
        if(entityDaoInit != null){
            entityDaoInit.afterEntityDataInit();
        }
        
        //语句相关
        if(dialectConfiguration.isOrmTableAutoCreate() &&
                (this.entityCRUDSql instanceof TemplateTableManageRenderer)) {
            if(!checkTable()){
                createTable();
            }
        }

        if(entityDaoInit != null){
            entityDaoInit.afterInit();
        }
    }


    public DialectType getDialectType() {
        String dbType = dialectConfiguration.getOrmDialect();
        return DialectType.valueOf(dbType);
    }

    public void initDialect(){
        //数据库相关
        DialectType dialect = getDialectType();

        this.typeMapper = new TypeMapperFactory()
                .withClassName(dialectConfiguration.getOrmDialectTypeMapper())
                .build(dialect);

        this.entityCRUDSql = new TemplateRendererFactory()
                .withClassName(dialectConfiguration.getOrmDialectEntitySql())
                .build(dialect);
    }

    public void initEntity(){
        //实体相关
        Type type = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)type).getActualTypeArguments();
        //noinspection unchecked
        Class<Entity> clazz = (Class<Entity>)params[0];

        this.entityInfo = new EAnnotationEntityInfoBuilder()
                .configStart()
                    .withTypeMapper(this.typeMapper)
                    .out()
                .build(clazz);

        this.rowMapper = new BaseEntityRowMapper<>(clazz)
                .withRowNameHandlerList(this.fieldRowNameHandlerList);
        this.entityColumnsArgsBuilder = new EntityColumnsArgsBuilder<Entity>()
                .withColumns(this.entityInfo.getColumns());

        this.entityIdsArgBuilder = new EntityColumnsArgsBuilder<Entity>()
                .withColumns(this.entityInfo.getKeys());
    }





    @Override
    public Boolean checkTable() {
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        String sql = entityTableManageSql.getTableCountSql(this.entityInfo);
        log.debug("SQL create:\n" + sql);
        Integer cnt = jdbcTemplate.queryForObject(sql,Integer.class);
        return cnt != null && cnt > 0;
    }

    @Override
    public void createTable() {
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        String sql = entityTableManageSql.getCreateTableSql(this.entityInfo);
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropTable() {
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        String sql = entityTableManageSql.getDropTableSql(this.entityInfo);
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.execute(sql);
    }


    @Override
    public Entity getById(ID id) {
        String sql = this.entityCRUDSql.getSelectByIdSql(this.entityInfo);
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.queryForObject(sql, this.rowMapper, id);
    }

    @Override
    public List<Entity> listByIds(ID... id) {
        String sql;
        if(id.length == 1){
            sql = this.entityCRUDSql.getSelectByIdSql(this.entityInfo);
        }else{
            sql = this.entityCRUDSql.getSelectByIdsSql(this.entityInfo,id.length);
        }
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.query(sql, this.rowMapper, (Object[]) id);
    }

    @Override
    public void deleteById(ID... id) {
        String sql;
        if(id.length == 1){
            sql = this.entityCRUDSql.getDeleteByIdSql(this.entityInfo);
        }else{
            sql = this.entityCRUDSql.getDeleteByIdsSql(this.entityInfo,id.length);
        }
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.update(sql, (Object[]) id);
    }

    @Override
    public void save(Entity... entity) {
        String sql;
        if(entity.length == 1){
            sql = this.entityCRUDSql.getInsertSql(this.entityInfo);
        }else{
            sql = this.entityCRUDSql.getInsertByEntityCountSql(this.entityInfo,entity.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();
        for (Entity e: entity) {
            try {
                params.addAll(entityColumnsArgsBuilder.build(e));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        jdbcTemplate.update(sql, params.toArray());
    }

    @Override
    public void update(Entity... entity) {
        String sql;
        if(entity.length == 1){
            sql = this.entityCRUDSql.getUpdateByIdSql(this.entityInfo);
        }else{
            sql = this.entityCRUDSql.getUpdateByIdsSql(this.entityInfo,entity.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();

        if(entity.length == 1){
            try {
                params.addAll(entityColumnsArgsBuilder.build(entity[0]));
                //TODO Multiple primary keys
                params.add(entityIdsArgBuilder.build(entity[0]).get(0));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else{
            List<Object> paramsId = new ArrayList<>();
            List<List<Object>> paramsEntityColumn = new ArrayList<>();
            for (Entity e: entity) {
                try {
                    //TODO Multiple primary keys
                    Object id = entityIdsArgBuilder.build(e).get(0);
                    paramsId.add(id);
                    List<Object> paramsColumn = entityColumnsArgsBuilder.build(e);
                    paramsEntityColumn.add(paramsColumn);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            //WHEN THEN
            for (int indexColumn = 0; indexColumn < this.entityInfo.getColumns().size(); indexColumn++) {
                for (int indexEntity = 0; indexEntity < paramsEntityColumn.size(); indexEntity++) {
                    List<Object> paramsColumns = paramsEntityColumn.get(indexEntity);
                    params.add(paramsId.get(indexEntity));
                    params.add(paramsColumns.get(indexColumn));
                }
            }
            //WHERE
            params.addAll(paramsId);
        }
        jdbcTemplate.update(sql, params.toArray());
    }


    @Override
    public void updatePath(Entity entity) {
        List<EntityColumn> entityColumns = new EntityColumnFilter()
                .withNotNullPropertyEntity(entity)
                .build(this.entityInfo.getColumns());

        EntityInfo template = new EntityInfo()
                .withTable(this.entityInfo.getTable())
                .withKey(this.entityInfo.getKeys().toArray(new EntityColumn[]{}))
                .withColumns(entityColumns);

        String sql = this.entityCRUDSql.getUpdateByIdSql(template);
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();
        params.addAll(new EntityColumnsArgsBuilder<Entity>()
                .withColumns(entityColumns)
                .build(entity));
        params.addAll(entityIdsArgBuilder.build(entity));
        jdbcTemplate.update(sql, params.toArray());
    }


    @Override
    public void updateStatus(Enum status, ID... id) {
        TemplateUpdateStatusIdRenderer entityUpdateStatusSql = safeTo(TemplateUpdateStatusIdRenderer.class);
        String sql;
        if(id.length == 1){
            sql = entityUpdateStatusSql.getUpdateStatusByIdSql(this.entityInfo);
        }else{
            sql = entityUpdateStatusSql.getUpdateStatusByIdsSql(this.entityInfo,id.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();
        params.add(status.ordinal());
        params.addAll(Arrays.asList(id));
        jdbcTemplate.update(sql, params.toArray());
    }


    @Override
    public Entity getByArg(Object... arg) {
        TemplateSelectArgRenderer entitySelectArgSql = safeTo(TemplateSelectArgRenderer.class);
        PlaceholderJSql sql = entitySelectArgSql.getSelectByArgsSql(this.entityInfo,arg);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.queryForObject(sql.getSql(), this.rowMapper, sql.getArgs());
    }

    @Override
    public List<Entity> listByArg(Object... args) {
        TemplateSelectArgRenderer entitySelectArgSql = safeTo(TemplateSelectArgRenderer.class);
        PlaceholderJSql sql = entitySelectArgSql.getSelectByArgsSql(this.entityInfo,args);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(sql.getSql(), this.rowMapper, sql.getArgs());
    }


    @Override
    public void deleteListByArg(Object... args) {
        TemplateDeleteArgRenderer entityDeleteArgSql = safeTo(TemplateDeleteArgRenderer.class);
        PlaceholderJSql sql = entityDeleteArgSql.getDeleteByArgsSql(this.entityInfo,args);
        log.debug("SQL create:\n" + sql.getSql());

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }


    @Override
    public <T> T searchById(Class<T> resultClass, ID id) {
        TemplateSearchIdRenderer entitySearchIdSql = safeTo(TemplateSearchIdRenderer.class);
        String sql = entitySearchIdSql.getSelectJoinByIdSql(this.entityInfo);
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.queryForObject(
                sql,
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                id);
    }

    @Override
    public <T> List<T> searchListByIds(Class<T> resultClass, ID... ids) {
        TemplateSearchIdRenderer entitySearchIdSql = safeTo(TemplateSearchIdRenderer.class);
        String sql = entitySearchIdSql.getSelectJoinByIdsSql(this.entityInfo,ids.length);
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.query(
                sql,
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                (Object[]) ids);
    }


    @Override
    public <T> List<T> searchListByArg(Class<T> resultClass, Object... args) {
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityInfo,args);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PageResult<T> searchPageByArg(PageQuery<T> pageQuery, Object... args) {
        if(pageQuery.getTarClass() == null){
            pageQuery.setTarClass(this.entityInfo.getClazz());
        }

        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityInfo,args);
        log.debug("SQL create:\n" + sql.getSql());

        SqlPageRenderer pageSql = safeTo(SqlPageRenderer.class);
        String sqlCount = pageSql.getCountSql(sql.getSql());
        log.debug("SQL create:\n" + sqlCount);

        String sqlPage = pageSql.getPageSql(sql.getSql(),pageQuery.getPageStartWithOne(),pageQuery.getPageSize());
        log.debug("SQL create:\n" + sqlPage);

        Integer count = jdbcTemplate.queryForObject(sqlCount, sql.getArgs(), Integer.class);
        List<T> data = jdbcTemplate.query(
                sqlPage,
                new BaseEntityRowMapper<>(pageQuery.getTarClass())
                        .withRowNumberName(pageSql.getPageSqlRowNumberName())
                        .withPageRowNumberName(this.dialectConfiguration.getOrmPageRowNumberName())
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
        return new PageResult<T>()
                .withRowCount(count)
                .withPageCount(count / pageQuery.getPageSize())
                .withRowNumber((pageQuery.getPageStartWithOne()-1) * pageQuery.getPageSize() + 1)
                .withRowData(data);
    }


    @Override
    public <T> List<T> searchListByTreeArg(Class<T> resultClass, EntityTemplateTreeArg entityTemplateTreeArg){
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByTreeArgSql(this.entityInfo, entityTemplateTreeArg);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> PageResult<T> searchPageByTreeArg(PageQuery<T> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg){
        if(pageQuery.getTarClass() == null){
            pageQuery.setTarClass(this.entityInfo.getClazz());
        }

        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByTreeArgSql(this.entityInfo, entityTemplateTreeArg);
        log.debug("SQL create:\n" + sql.getSql());

        SqlPageRenderer pageSql = safeTo(SqlPageRenderer.class);
        String sqlCount = pageSql.getCountSql(sql.getSql());
        log.debug("SQL create:\n" + sqlCount);

        String sqlPage = pageSql.getPageSql(sql.getSql(),pageQuery.getPageStartWithOne(),pageQuery.getPageSize());
        log.debug("SQL create:\n" + sqlPage);

        Integer count = jdbcTemplate.queryForObject(sqlCount, sql.getArgs(), Integer.class);
        List<T> data = jdbcTemplate.query(
                sqlPage,
                new BaseEntityRowMapper<>(pageQuery.getTarClass())
                        .withRowNumberName(pageSql.getPageSqlRowNumberName())
                        .withPageRowNumberName(this.dialectConfiguration.getOrmPageRowNumberName())
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
        return new PageResult<T>()
                .withRowCount(count)
                .withPageCount(count / pageQuery.getPageSize())
                .withRowNumber((pageQuery.getPageStartWithOne()-1) * pageQuery.getPageSize() + 1)
                .withRowData(data);
    }


    protected  <T> T safeTo(Class<T> clazz){
        if(!(this.entityCRUDSql instanceof SqlPageRenderer)){
            throw new RuntimeException(this.entityCRUDSql.getClass().getName() + " 不支持 " + clazz.getName() + "接口！");
        }

        return (T) this.entityCRUDSql;
    }
}
