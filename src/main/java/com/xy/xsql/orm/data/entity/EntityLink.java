package com.xy.xsql.orm.data.entity;

/**
 * SQL实体
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityLink {
    private EntityColumn entityColumn;
    private EntityTemplate entityTemplate;


    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public EntityTemplate getEntityTemplate() {
        return entityTemplate;
    }

    /**
     * set column
     * @param entityColumn EntityColumn
     * @return This
     */
    public EntityLink withColumn(EntityColumn entityColumn) {
        this.entityColumn = entityColumn;
        return this;
    }

    /**
     * set data
     * @param entityTemplate EntityTemplate
     * @return This
     */
    public EntityLink withTemplate(EntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
        return this;
    }

    /**
     * 是否是主实体
     * @return 是/否
     */
    @Deprecated
    public boolean isCoreBean(){
        return entityColumn == null;
    }
}
