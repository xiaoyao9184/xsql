package com.xy.xsql.spring.template.simple;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface ClassEntityCRUDTemplate {

    /**
     * Query Entity by entity's id
     * TODO Maybe the id parameters are folded
     * @param entityClass Entity class
     * @param id Entity id
     * @param <Entity> Entity
     * @return Entity
     */
    <Entity> Entity getById(Class<Entity> entityClass, Object... id);

    /**
     * Query Entity List by entity's id
     * @param entityClass Entity class
     * @param id Entity id, When using a multiple primary key, the number of parameters must be an integral multiple of the number of primary keys
     * @param <Entity> Entity
     * @return Entity list
     */
    <Entity> List<Entity> listByIds(Class<Entity> entityClass, Object... id);

    <Entity> void deleteById(Class<Entity> entityClass, Object... id);

    <Entity> void saveByEntity(Class<Entity> entityClass, Entity... entity);

    <Entity> void updateByEntity(Class<Entity> entityClass, Entity... entity);

    <Entity> Entity getByEntity(Class<Entity> entityClass, Entity entity);

    <Entity> List<Entity> listByEntity(Class<Entity> entityClass, Entity entity);

    <Entity> void deleteByEntity(Class<Entity> entityClass, Entity entity);

    /**
     * Query Entity by entity's column
     * @param entityClass
     * @param column
     * @param <Entity> Entity
     * @return Entity
     */
    @Deprecated
    <Entity> Entity getByColumn(Class<Entity> entityClass, Object... column);

    /**
     *
     * @param entityClass
     * @param column NOT support 'OR' condition to query
     * @param <Entity>
     * @return
     */
    @Deprecated
    <Entity> List<Entity> listByColumn(Class<Entity> entityClass, Object... column);

    /**
     *
     * @param entityClass
     * @param column NOT support 'OR' condition to delete
     * @param <Entity>
     */
    @Deprecated
    <Entity> void deleteByColumn(Class<Entity> entityClass, Object... column);
}
