package com.xy.xsql.spring.dao.impl;

import com.xy.xsql.entity.api.annotation.Relationships;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateDeleteArgRenderer;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateDistinctSearchArgRenderer;
import com.xy.xsql.entity.api.dialect.render.jsql.TemplateSearchArgRenderer;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityColumn;
import com.xy.xsql.entity.model.template.EntityLink;
import com.xy.xsql.entity.model.template.EntityParam;
import com.xy.xsql.spring.dao.EntityDao;
import com.xy.xsql.spring.dao.EntityLinkDao;
import com.xy.xsql.spring.mapping.BaseEntityRowMapper;
import com.xy.xsql.spring.mapping.FieldRowNameHandler;

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
        for (EntityLink link: this.entityInfo.getLinks()) {
            autoLinkParams.add(new EntityParam()
                    .withColumn(link.getColumn())
                    .withRelationship(Relationships.EQUAL));
        }

        //
        List<EntityParam> params = new ArrayList<>();
        params.addAll(this.autoLinkParams);
        params.addAll(this.entityInfo.getParams());
        this.entityInfo.withParams(params);
    }


    private List<Object> getArgsByLinkEntity(Class entity, Object id){
        List<Object> argList = new ArrayList<>();
        for (EntityLink link: this.entityInfo.getLinks()) {
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
        for (EntityLink link: this.entityInfo.getLinks()) {
            if(link.getTemplate().getClazz().equals(entity)){
                return link.getColumn();
            }
        }
        return null;
    }

    private EntityLink getLinkByLinkEntity(Class entity) {
        for (EntityLink link: this.entityInfo.getLinks()) {
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
        TemplateDeleteArgRenderer entityDeleteArgSql = safeTo(TemplateDeleteArgRenderer.class);
        PlaceholderJSql sql = entityDeleteArgSql.getDeleteByArgsSql(this.entityInfo,(Object[]) linkID);
        log.debug("SQL create:\n" + sql.getSql());

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }

    @Override
    public void deleteByLinkId(Class linkEntity, LinkID linkID) {
        TemplateDeleteArgRenderer entityDeleteArgSql = safeTo(TemplateDeleteArgRenderer.class);
        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);

        PlaceholderJSql sql = entityDeleteArgSql.getDeleteByArgsSql(this.entityInfo,params.toArray());
        log.debug("SQL create:\n" + sql.getSql());

        jdbcTemplate.update(sql.getSql(), sql.getArgs());
    }


    @Override
    public <ResultEntity> List<ResultEntity> listEntityByLinkId(Class<ResultEntity> resultEntity, LinkID... linkID) {
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);

        EntityColumn column = getColumnByLinkEntity(resultEntity);
        String aliasNamePrefix = "";
        if(column != null){
            aliasNamePrefix = column.getAliasName() + "_";
        }

        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityInfo,(Object[]) linkID);
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
        TemplateSearchArgRenderer entitySearchArgSql = safeTo(TemplateSearchArgRenderer.class);

        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);
        EntityColumn column = getColumnByLinkEntity(resultEntity);
        String aliasNamePrefix = "";
        if(column != null){
            aliasNamePrefix = column.getAliasName() + "_";
        }

        PlaceholderJSql sql = entitySearchArgSql.getSelectJoinByArgsSql(this.entityInfo,params.toArray());
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
        TemplateDistinctSearchArgRenderer entityDistinctSearchArgSql = safeTo(TemplateDistinctSearchArgRenderer.class);

        EntityLink link = getLinkByLinkEntity(resultEntity);
        if(link == null){
            throw new RuntimeException(this.entityInfo.getClazz().getName() + " 关系实体不包含 " + resultEntity.getName() + "！");
        }
        List<EntityParam> linkParams = new ArrayList<>();
        for (EntityLink eLink: this.entityInfo.getLinks()) {
            linkParams.add(new EntityParam()
                    .withColumn(eLink.getColumn())
                    .withRelationship(Relationships.IN));
        }
        linkParams.addAll(this.entityInfo.getParams().subList(
                linkParams.size() - 1,
                this.entityInfo.getParams().size() - 1));

        String aliasNamePrefix = link.getColumn().getAliasName() + "_";
        EntityInfo entityInfo = new EntityInfo()
                .withColumns(link.getTemplate().getColumns())
                .withTable(this.entityInfo.getTable())
                .withLinks(this.entityInfo.getLinks())
                .withParams(linkParams)
                .withOrders(this.entityInfo.getOrders());
        List<Object> params = getArgsByLinkEntity(linkEntity, linkID);

        PlaceholderJSql sql = entityDistinctSearchArgSql.getDistinctJoinByArgsSql(entityInfo,params.toArray());
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
