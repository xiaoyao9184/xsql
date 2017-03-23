package com.xy.xsql.entity.model.template;

/**
 * EntityOrder
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityOrder {
    private EntityColumn column;
    private boolean asc;

    /**
     * get EntityColumn
     * @return EntityColumn
     */
    public EntityColumn getColumn() {
        return (EntityColumn) column;
    }

    public boolean isAsc() {
        return asc;
    }

    /**
     * set EntityColumn
     * @param entityColumn EntityColumn
     * @return This
     */
    public EntityOrder withColumn(EntityColumn entityColumn){
        this.column = entityColumn;
        return this;
    }

    /**
     * set Asc flag
     * @param asc Asc flag
     * @return This
     */
    public EntityOrder withAsc(boolean asc){
        this.asc = asc;
        return this;
    }

}
