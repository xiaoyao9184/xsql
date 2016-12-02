package com.xy.xsql.orm.data.entity;

/**
 * SQL实体
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityLink {
    private EntityColumn entityColumn;
    private EntityTemplateData entityTemplateData;


    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public EntityTemplateData getEntityTemplateData() {
        return entityTemplateData;
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
     * @param entityTemplateData EntityTemplateData
     * @return This
     */
    public EntityLink withTemplate(EntityTemplateData entityTemplateData) {
        this.entityTemplateData = entityTemplateData;
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
