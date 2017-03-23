package com.xy.xsql.spring.dao;

import com.xy.xsql.entity.core.template.EntityColumnsArgsBuilder;
import com.xy.xsql.entity.model.template.EntityTemplate;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 *
 * @param <Entity> 实体
 * @param <EntityId> EntityId
 */
public interface EntitysDao<Entity, EntityId> {

    List<Class<Entity>> initEntityClassList();

    Boolean checkTable(Class<Entity> entityClass);

    void createTable(Class<Entity> entityClass);

    void dropTable(Class<Entity> entityClass);

    Entity getById(Class<Entity> entityClass, EntityId id);

    List<Entity> listByIds(Class<Entity> entityClass, EntityId... id);

    void deleteById(Class<Entity> entityClass, EntityId... id);

    void save(Class<Entity> entityClass, Entity... entity);

    void update(Class<Entity> entityClass, Entity... entity);

    void updateStatus(Class<Entity> entityClass, Enum status, EntityId... id);

    Entity getByArg(Class<Entity> entityClass, Object... arg);

    List<Entity> listByArg(Class<Entity> entityClass, Object... args);

    <T> List<T> searchListByArg(Class<Entity> entityClass, Class<T> resultClass, Object... args);

    <T> PageResult<T> searchPageByArg(Class<Entity> entityClass, PageQuery<T> pageQuery, Object... args);

    <T> List<T> searchListByTreeArg(Class<Entity> entityClass, Class<T> resultClass, EntityTemplateTreeArg entityTemplateTreeArg);

    <T> PageResult<T> searchPageByTreeArg(Class<Entity> entityClass, PageQuery<T> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg);


    public static class CacheData<Entity> {
        private EntityTemplate entityTemplate;
        private RowMapper<Entity> rowMapper;
        private EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder;
        private EntityColumnsArgsBuilder<Entity> entityIdsArgBuilder;

        public EntityTemplate getEntityTemplate() {
            return entityTemplate;
        }

        public void setEntityTemplate(EntityTemplate entityTemplate) {
            this.entityTemplate = entityTemplate;
        }

        public RowMapper<Entity> getRowMapper() {
            return rowMapper;
        }

        public void setRowMapper(RowMapper<Entity> rowMapper) {
            this.rowMapper = rowMapper;
        }

        public EntityColumnsArgsBuilder<Entity> getEntityColumnsArgsBuilder() {
            return entityColumnsArgsBuilder;
        }

        public void setEntityColumnsArgsBuilder(EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder) {
            this.entityColumnsArgsBuilder = entityColumnsArgsBuilder;
        }

        public EntityColumnsArgsBuilder<Entity> getEntityIdArgBuilder() {
            return entityIdsArgBuilder;
        }

        public void setEntityIdArgBuilder(EntityColumnsArgsBuilder<Entity> entityIdsArgBuilder) {
            this.entityIdsArgBuilder = entityIdsArgBuilder;
        }

        public CacheData<Entity> withEntityData(EntityTemplate entityTemplate) {
            this.entityTemplate = entityTemplate;
            return this;
        }

        public CacheData<Entity> withRowMapper(RowMapper<Entity> rowMapper) {
            this.rowMapper = rowMapper;
            return this;
        }

        public CacheData<Entity> withEntityColumnArgsBuilder(EntityColumnsArgsBuilder<Entity> entityColumnsArgsBuilder) {
            this.entityColumnsArgsBuilder = entityColumnsArgsBuilder;
            return this;
        }

        public CacheData<Entity> withEntityIdArgBuilder(EntityColumnsArgsBuilder<Entity> entityIdsArgBuilder) {
            this.entityIdsArgBuilder = entityIdsArgBuilder;
            return this;
        }
    }
}
