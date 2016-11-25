package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EColumn;
import com.xy.xsql.orm.annotation.EOrder;
import com.xy.xsql.orm.data.sql.element.info.Order;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.lang.reflect.Field;

/**
 * SQL排序
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityOrder extends Order {

    private EntityColumn entityColumn;
    protected EOrder eOrder;


    public EntityOrder(EOrder eOrder, EColumn eColumn, Field field, Table table){
        this.entityColumn = new EntityColumn(eColumn, field, table);
        this.eOrder = eOrder;

        super.column = this.entityColumn;
        super.aes = eOrder.aes();
    }


    public EOrder geteOrder() {
        return eOrder;
    }

    public void seteOrder(EOrder eOrder) {
        this.eOrder = eOrder;
    }

    public EntityColumn getEntityColumn() {
        return entityColumn;
    }

    public void setEntityColumn(EntityColumn entityColumn) {
        this.entityColumn = entityColumn;
    }


    /**
     * 变为 Order
     * @return Order
     */
    public Order toOrder(){
        return this;
    }
}
