package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.data.sql.element.info.Order;

/**
 * SQL排序
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
     * set Aes flag
     * @param aes Aes flag
     * @return This
     */
    @Override
    public EntityOrder withAes(boolean aes){
        this.aes = aes;
        return this;
    }


    /**
     * 变为 Order
     * @return Order
     */
    public Order toOrder(){
        return this;
    }


}
