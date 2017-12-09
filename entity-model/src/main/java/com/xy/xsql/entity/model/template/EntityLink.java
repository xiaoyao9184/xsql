package com.xy.xsql.entity.model.template;

/**
 * EntityLink
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityLink {
    private EntityColumn column;
    private EntityInfo template;


    public EntityColumn getColumn() {
        return column;
    }

    public EntityInfo getTemplate() {
        return template;
    }

    /**
     * set column
     * @param entityColumn EntityColumn
     * @return This
     */
    public EntityLink withColumn(EntityColumn entityColumn) {
        this.column = entityColumn;
        return this;
    }

    /**
     * set annotation
     * @param entityInfo EntityInfo
     * @return This
     */
    public EntityLink withTemplate(EntityInfo entityInfo) {
        this.template = entityInfo;
        return this;
    }

    @Deprecated
    public boolean isCoreBean(){
        return column == null;
    }
}
