package com.xy.xsql.orm.data.entity;

/**
 * EntityLink
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityLink {
    private EntityColumn column;
    private EntityTemplate template;


    public EntityColumn getColumn() {
        return column;
    }

    public EntityTemplate getTemplate() {
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
     * set data
     * @param entityTemplate EntityTemplate
     * @return This
     */
    public EntityLink withTemplate(EntityTemplate entityTemplate) {
        this.template = entityTemplate;
        return this;
    }

    @Deprecated
    public boolean isCoreBean(){
        return column == null;
    }
}
