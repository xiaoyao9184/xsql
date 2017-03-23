package com.xy.xsql.spring.dao.impl;



import com.xy.xsql.entity.annotation.Relationships;
import com.xy.xsql.orm.core.entity.sql.agreement.SqlEntityDeleteArg;
import com.xy.xsql.orm.core.entity.sql.agreement.SqlEntityDistinctSearchArg;
import com.xy.xsql.orm.core.entity.sql.agreement.SqlEntitySearchArg;
import com.xy.xsql.orm.data.entity.EntityColumn;
import com.xy.xsql.orm.data.entity.EntityLink;
import com.xy.xsql.orm.data.entity.EntityParam;
import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;
import com.xy.xsql.orm.mapping.row.BaseEntityRowMapper;
import com.xy.xsql.orm.mapping.row.FieldRowNameHandler;
import com.xy.xsql.spring.dao.EntityDao;
import com.xy.xsql.spring.dao.EntityLinkDao;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityLinkDaoImpl<Entity, ID, LinkID>
        extends EntityDaoImpl<Entity, ID>
        implements EntityDao.EntityDaoInit, EntityLinkDao<Entity, ID, LinkID> {

    private List<EntityParam> autoLinkParams;

    @Override
    public void beforeInit() {

    }

    @Override
    public void afterInit() {

    }

    @Override
    public void afterDbDialectInit() {

    }

    @Override
    public void afterEntityDataInit() {
        this.autoLinkParams = new ArrayList<>();
        for (EntityLink link: this.entityTemplate.getLinks()) {
            autoLinkParams.add(new EntityParam()
                    .withColumn(link.getColumn())
                    .withRelationship(Relationships.EQUAL));
        }

        //
        List<EntityParam> params = new ArrayList<>();
        params.addAll(this.autoLinkParams);
        params.addAll(this.entityTemplate.getParams());
        this.entityTemplate.withParams(params);
    }


    private List<Object> getArgsByLinkEntity(Class entity, Object id){
        List<Object> argList = new ArrayList<>();
        for (EntityLink link: this.entityTemplate.getLinks()) {
            if(link.getTemplate().getClazz().equals(entity)){
                argList.add(id);
                break;
            }else{
                argList.add(null);
            }
        }
        return argList;
    }

    private EntityColumn getColumnByLinkEntity(Class entity){
        for (EntityLink link: this.entityTemplate.getLinks()) {
            if(link.getTemplate().getClazz().equals(entity)){
                return link.getColumn();
            }
        }
        return null;
    }

    private EntityLink getLinkByLinkEntity(Class entity) {
        for (EntityLink link: this.entityTemplate.getLinks()) {
            if(link.getTemplate().getClazz().equals(entity)){
                return link;
            }
        }
        return null;
    }

    @Override
    public Entity getByLinkId(LinkID... linkID) {
        return getByArg((Object[]) linkID);
    }


    @Override
    public List<Entity> listByLinkId(Class linkEntity, LinkID linkID) {
        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);

        return listByArg(params.toArray());
    }


    @Override
    public void deleteByLinkId(LinkID... linkID) {
        SqlEntityDeleteArg entityDeleteArgSql = safeTo(SqlEntityDeleteArg.class);
        ArgSql sql = entityDeleteArgSql.getDeleteByArgsSql(this.entityTemplate,(Object[]) linkID);
        log.debug("SQL create:\n" + sql.getSql());

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public void deleteByLinkId(Class linkEntity, LinkID linkID) {
        SqlEntityDeleteArg entityDeleteArgSql = safeTo(SqlEntityDeleteArg.class);
        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);

        ArgSql sql = entityDeleteArgSql.getDeleteByArgsSql(this.entityTemplate,params.toArray());
        log.debug("SQL create:\n" + sql.getSql());

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }


    @Override
    public <ResultEntity> List<ResultEntity> listEntityByLinkId(Class<ResultEntity> resultEntity, LinkID... linkID) {
        SqlEntitySearchArg entitySearchArgSql = safeTo(SqlEntitySearchArg.class);

        EntityColumn column = getColumnByLinkEntity(resultEntity);
        String aliasNamePrefix = "";
        if(column != null){
            aliasNamePrefix = column.getAliasName() + "_";
        }

        ArgSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityTemplate,(Object[]) linkID);
        log.debug("SQL create:\n" + sql.getSql());

        final String finalAliasNamePrefix = aliasNamePrefix;
        return jdbcTemplate.query(sql.getSql(), new BaseEntityRowMapper<>(resultEntity)
                .withDefaultRowNameHandler(false)
                .withRowNameHandler(new FieldRowNameHandler() {
                    @Override
                    public String handlerField(Field field) {
                        Column column = field.getAnnotation(Column.class);
                        return finalAliasNamePrefix + column.name();
                    }
                }), sql.getArgs());
    }

    @Override
    public <ResultEntity> List<ResultEntity> listEntityByLinkId(Class<ResultEntity> resultEntity, Class linkEntity, LinkID linkID){
        SqlEntitySearchArg entitySearchArgSql = safeTo(SqlEntitySearchArg.class);

        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);
        EntityColumn column = getColumnByLinkEntity(resultEntity);
        String aliasNamePrefix = "";
        if(column != null){
            aliasNamePrefix = column.getAliasName() + "_";
        }

        ArgSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityTemplate,params.toArray());
        log.debug("SQL create:\n" + sql.getSql());

        final String finalAliasNamePrefix = aliasNamePrefix;
        return jdbcTemplate.query(sql.getSql(), new BaseEntityRowMapper<>(resultEntity)
                .withDefaultRowNameHandler(false)
                .withRowNameHandler(new FieldRowNameHandler() {
                    @Override
                    public String handlerField(Field field) {
                        Column column = field.getAnnotation(Column.class);
                        return finalAliasNamePrefix + column.name();
                    }
                }), sql.getArgs());
    }


    @Override
    public <ResultEntity> List<ResultEntity> distinctEntityByLinkId(Class<ResultEntity> resultEntity, Class linkEntity, LinkID... linkID) {
        SqlEntityDistinctSearchArg entityDistinctSearchArgSql = safeTo(SqlEntityDistinctSearchArg.class);

        EntityLink link = getLinkByLinkEntity(resultEntity);
        if(link == null){
            throw new RuntimeException(this.entityTemplate.getClazz().getName() + " 关系实体不包含 " + resultEntity.getName() + "！");
        }
        List<EntityParam> linkParams = new ArrayList<>();
        for (EntityLink eLink: this.entityTemplate.getLinks()) {
            linkParams.add(new EntityParam()
                    .withColumn(eLink.getColumn())
                    .withRelationship(Relationships.IN));
        }
        linkParams.addAll(this.entityTemplate.getParams().subList(
                linkParams.size() - 1,
                this.entityTemplate.getParams().size() - 1));

        String aliasNamePrefix = link.getColumn().getAliasName() + "_";
        EntityTemplate entityTemplate = new EntityTemplate()
                .withColumns(link.getTemplate().getColumns())
                .withTable(this.entityTemplate.getTable())
                .withLinks(this.entityTemplate.getLinks())
                .withParams(linkParams)
                .withOrders(this.entityTemplate.getOrders());
        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);

        ArgSql sql = entityDistinctSearchArgSql.getDistinctJoinByArgsSql(entityTemplate,params.toArray());
        log.debug("SQL create:\n" + sql.getSql());

        final String finalAliasNamePrefix = aliasNamePrefix;
        return jdbcTemplate.query(sql.getSql(), new BaseEntityRowMapper<>(resultEntity)
                .withDefaultRowNameHandler(false)
                .withRowNameHandler(new FieldRowNameHandler() {
                    @Override
                    public String handlerField(Field field) {
                        Column column = field.getAnnotation(Column.class);
                        return finalAliasNamePrefix + column.name();
                    }
                }), sql.getArgs());
    }


}
