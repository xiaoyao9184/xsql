package com.xy.xsql.spring.template.relationship;

import com.xy.xsql.entity.api.dialect.render.jsql.TemplateTableManageRenderer;
import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.entity.model.definition.RelationshipClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public class DefaultRelationshipEntityManageTemplate implements RelationshipEntityManageTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RelationshipEntityManager relationshipEntityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public <LeftEntity, RightEntity> Boolean checkTable(RelationshipClass<LeftEntity, RightEntity> entity) {
        RelationshipEntityManager.RelationshipEntityHolder<LeftEntity, RightEntity> holder = relationshipEntityManager.by(entity);
        EntityInfo entityInfo = holder.getEntityInfo();
        String sql = holder.getRenderer(TemplateTableManageRenderer.class)
                .getTableCountSql(entityInfo);

        logger.debug("SQL create:\n{}", sql);
        Integer cnt = jdbcTemplate.queryForObject(sql,Integer.class);
        return cnt != null && cnt > 0;
    }

    @Override
    public <LeftEntity, RightEntity> void createTable(RelationshipClass<LeftEntity, RightEntity> entity) {
        RelationshipEntityManager.RelationshipEntityHolder<LeftEntity, RightEntity> holder = relationshipEntityManager.by(entity);
        EntityInfo entityInfo = holder.getEntityInfo();
        String sql = holder.getRenderer(TemplateTableManageRenderer.class)
                .getCreateTableSql(entityInfo);

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public <LeftEntity, RightEntity> void dropTable(RelationshipClass<LeftEntity, RightEntity> entity) {
        RelationshipEntityManager.RelationshipEntityHolder<LeftEntity, RightEntity> holder = relationshipEntityManager.by(entity);
        EntityInfo entityInfo = holder.getEntityInfo();
        String sql = holder.getRenderer(TemplateTableManageRenderer.class)
                .getDropTableSql(entityInfo);

        logger.debug("SQL create:\n{}", sql);
        jdbcTemplate.execute(sql);
    }


}
