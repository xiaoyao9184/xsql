package com.xy.xsql.spring.dao.impl;

import com.xy.xsql.entity.api.dialect.jpql.*;
import com.xy.xsql.entity.core.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityTemplate;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;
import com.xy.xsql.spring.mapping.BaseEntityRowMapper;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;
import com.xy.xsql.spring.mapping.MappingFieldRowNameHandler;
import com.xy.xsql.spring.dao.EntityDao;
import com.xy.xsql.spring.dao.EntitysDao;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import java.util.*;

public abstract class EntitysDaoAbstractImpl<Entity, EntityId>
        extends EntityDaoImpl<Entity, EntityId>
        implements EntitysDao<Entity, EntityId>, EntityDao<Entity, EntityId> {

    protected Map<Class<Entity>,CacheData<Entity>> cache = Collections.synchronizedMap(new HashMap<Class<Entity>,CacheData<Entity>>());

    private List<FieldRowNameHandler> fieldRowNameHandlerList;

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

        this.cache = this.initCache();
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

    public Map<Class<Entity>,CacheData<Entity>> initCache() {
        Map<Class<Entity>,CacheData<Entity>> result = new HashMap<>();
        List<Class<Entity>> classList = this.initEntityClassList();
        for (Class<Entity> clazz: classList) {
            CacheData<Entity> cacheData = new CacheData<>();
            this.entityTemplate = new AnnotationEntityTemplateBuilder()
                    .configStart()
                        .withTypeMapper(this.typeMapper)
                        .out()
                    .build(clazz);

            this.rowMapper = new BaseEntityRowMapper<>(clazz)
                    .withRowNameHandlerList(this.fieldRowNameHandlerList);

            this.entityColumnsArgsBuilder = new EntityColumnsArgsBuilder<Entity>()
                    .withColumns(this.entityTemplate.getColumns());

            this.entityIdsArgBuilder = new EntityColumnsArgsBuilder<Entity>()
                    .withColumns(this.entityTemplate.getKeys());

            cacheData
                    .withEntityData(this.entityTemplate)
                    .withRowMapper(this.rowMapper)
                    .withEntityColumnArgsBuilder(this.entityColumnsArgsBuilder)
                    .withEntityIdArgBuilder(this.entityIdsArgBuilder);
            result.put(clazz,cacheData);
        }
        this.entityTemplate = null;
        this.rowMapper = null;
        this.entityColumnsArgsBuilder = null;
        this.entityIdsArgBuilder = null;
        return result;
    }


    @Override
    public Boolean checkTable() {
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        for(CacheData<Entity> cacheData: this.cache.values()){
            EntityTemplate entityTemplate = cacheData.getEntityTemplate();
            String sql = entityTableManageSql.getTableCountSql(entityTemplate);
            log.debug("SQL create:\n" + sql);
            Integer cnt = jdbcTemplate.queryForObject(sql,Integer.class);
            if(!(cnt != null && cnt > 0)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void createTable() {
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        for(CacheData<Entity> cacheData: this.cache.values()){
            EntityTemplate entityTemplate = cacheData.getEntityTemplate();
            String sql = entityTableManageSql.getCreateTableSql(entityTemplate);
            log.debug("SQL create:\n" + sql);
            jdbcTemplate.execute(sql);
        }
    }


    @Override
    public Boolean checkTable(Class<Entity> entityClass) {
        if(!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        EntityTemplate entityTemplate = this.cache.get(entityClass).getEntityTemplate();
        String sql = entityTableManageSql.getTableCountSql(entityTemplate);
        log.debug("SQL create:\n" + sql);
        Integer cnt = jdbcTemplate.queryForObject(sql,Integer.class);
        return cnt != null && cnt > 0;
    }

    @Override
    public void createTable(Class<Entity> entityClass) {
        if(!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        EntityTemplate entityTemplate = this.cache.get(entityClass).getEntityTemplate();
        String sql = entityTableManageSql.getCreateTableSql(entityTemplate);
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropTable(Class<Entity> entityClass) {
        if(!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateTableManageRenderer entityTableManageSql = safeTo(TemplateTableManageRenderer.class);
        EntityTemplate entityTemplate = this.cache.get(entityClass).getEntityTemplate();
        String sql = entityTableManageSql.getDropTableSql(entityTemplate);
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.execute(sql);
    }


    @Override
    public Entity getById(Class<Entity> entityClass, EntityId id) {
        if(!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        String sql = this.entityCRUDSql.getSelectByIdSql(this.cache.get(entityClass).getEntityTemplate());
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.queryForObject(sql, this.cache.get(entityClass).getRowMapper(), id);
    }

    @Override
    public List<Entity> listByIds(Class<Entity> entityClass, EntityId... id) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        String sql;
        if(id.length == 1){
            sql = this.entityCRUDSql.getSelectByIdSql(this.cache.get(entityClass).getEntityTemplate());
        }else{
            sql = this.entityCRUDSql.getSelectByIdsSql(this.cache.get(entityClass).getEntityTemplate(),id.length);
        }
        log.debug("SQL create:\n" + sql);

        return jdbcTemplate.query(sql, this.cache.get(entityClass).getRowMapper(), (Object[]) id);
    }

    @Override
    public void deleteById(Class<Entity> entityClass, EntityId... id) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        String sql;
        if(id.length == 1){
            sql = this.entityCRUDSql.getDeleteByIdSql(this.cache.get(entityClass).getEntityTemplate());
        }else{
            sql = this.entityCRUDSql.getDeleteByIdsSql(this.cache.get(entityClass).getEntityTemplate(),id.length);
        }
        log.debug("SQL create:\n" + sql);
        jdbcTemplate.update(sql, (Object[]) id);
    }

    @Override
    public void save(Class<Entity> entityClass, Entity... entity) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        String sql;
        if(entity.length == 1){
            sql = this.entityCRUDSql.getInsertSql(this.cache.get(entityClass).getEntityTemplate());
        }else{
            sql = this.entityCRUDSql.getInsertByEntityCountSql(this.cache.get(entityClass).getEntityTemplate(),entity.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();
        for (Entity e: entity) {
            try {
                params.addAll(this.cache.get(entityClass).getEntityColumnsArgsBuilder().build(e));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        jdbcTemplate.update(sql, params.toArray());
    }

    @Override
    public void update(Class<Entity> entityClass, Entity... entity) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        String sql;
        if(entity.length == 1){
            sql = this.entityCRUDSql.getUpdateByIdSql(this.cache.get(entityClass).getEntityTemplate());
        }else{
            sql = this.entityCRUDSql.getUpdateByIdsSql(this.cache.get(entityClass).getEntityTemplate(),entity.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();

        if(entity.length == 1){
            try {
                params.addAll(this.cache.get(entityClass).getEntityColumnsArgsBuilder().build(entity[0]));
                params.add(this.cache.get(entityClass).getEntityIdArgBuilder().build(entity[0]));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else{
            List<Object> paramsId = new ArrayList<>();
            List<List<Object>> paramsEntityColumn = new ArrayList<>();
            for (Entity e: entity) {
                try {
                    Object id = this.cache.get(entityClass).getEntityIdArgBuilder().build(e);
                    paramsId.add(id);
                    List<Object> paramsColumn = this.cache.get(entityClass).getEntityColumnsArgsBuilder().build(e);
                    paramsEntityColumn.add(paramsColumn);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            //WHEN THEN
            for (int indexColumn = 0; indexColumn < this.cache.get(entityClass).getEntityTemplate().getColumns().size(); indexColumn++) {
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
    public void updateStatus(Class<Entity> entityClass, Enum status, EntityId... id) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateUpdateStatusIdRenderer entityUpdateStatusSql = safeTo(TemplateUpdateStatusIdRenderer.class);
        String sql;
        if(id.length == 1){
            sql = entityUpdateStatusSql.getUpdateStatusByIdSql(this.cache.get(entityClass).getEntityTemplate());
        }else{
            sql = entityUpdateStatusSql.getUpdateStatusByIdsSql(this.cache.get(entityClass).getEntityTemplate(),id.length);
        }
        log.debug("SQL create:\n" + sql);

        List<Object> params = new ArrayList<>();
        params.add(status.ordinal());
        params.addAll(Arrays.asList(id));
        jdbcTemplate.update(sql, params.toArray());
    }


    @Override
    public Entity getByArg(Class<Entity> entityClass, Object... arg) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSelectArgRenderer entitySelectArgSql = safeTo(TemplateSelectArgRenderer.class);
        PlaceholderJSql sql = entitySelectArgSql.getSelectByArgsSql(this.cache.get(entityClass).getEntityTemplate(),arg);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.queryForObject(sql.getSql(), this.cache.get(entityClass).getRowMapper(), sql.getArgs());
    }

    @Override
    public List<Entity> listByArg(Class<Entity> entityClass, Object... args) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSelectArgRenderer entitySelectArgSql = safeTo(TemplateSelectArgRenderer.class);
        PlaceholderJSql sql = entitySelectArgSql.getSelectByArgsSql(this.cache.get(entityClass).getEntityTemplate(),args);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(sql.getSql(), this.cache.get(entityClass).getRowMapper(), sql.getArgs());
    }


    @Override
    public <T> List<T> searchListByArg(Class<Entity> entityClass, Class<T> resultClass, Object... args) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.cache.get(entityClass).getEntityTemplate(),args);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
    }

    @Override
    public <T> PageResult<T> searchPageByArg(Class<Entity> entityClass, PageQuery<T> pageQuery, Object... args) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.cache.get(entityClass).getEntityTemplate(),args);
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
    public <T> List<T> searchListByTreeArg(Class<Entity> entityClass, Class<T> resultClass, EntityTemplateTreeArg entityTemplateTreeArg) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByTreeArgSql(this.cache.get(entityClass).getEntityTemplate(), entityTemplateTreeArg);
        log.debug("SQL create:\n" + sql.getSql());

        return jdbcTemplate.query(
                sql.getSql(),
                new BaseEntityRowMapper<>(resultClass)
                        .withRowNameHandlerList(this.fieldRowNameHandlerList),
                sql.getArgs());
    }

    @Override
    public <T> PageResult<T> searchPageByTreeArg(Class<Entity> entityClass, PageQuery<T> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg) {
        if (!this.cache.containsKey(entityClass)){
            throw new RuntimeException(entityClass.getName() + " 不被此" + this.getClass().getName() + "管理！");
        }
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);
        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByTreeArgSql(this.cache.get(entityClass).getEntityTemplate(), entityTemplateTreeArg);
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
}
