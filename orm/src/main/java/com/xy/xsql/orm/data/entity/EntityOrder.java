package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Order;

/**
 * EntityOrder
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityOrder extends Order {

    /**
     * get EntityColumn
     * @return EntityColumn
     */
    @Override
    public EntityColumn getColumn() {
        return (EntityColumn) column;
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
    @Override
    public EntityOrder withAsc(boolean asc){
        this.asc = asc;
        return this;
    }

}
