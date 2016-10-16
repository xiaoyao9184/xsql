package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntityColumnOrder;
import com.xy.xsql.orm.data.sql.info.Order;
import com.xy.xsql.orm.data.sql.info.Table;

import java.lang.reflect.Field;

/**
 * SQL排序
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class SqlOrder extends Order {

    private SqlColumn sqlColumn;
    protected EntityColumnOrder entityColumnOrder;


    public SqlOrder(EntityColumnOrder entityColumnOrder, EntityColumn entityColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(entityColumn, field, table);
        this.entityColumnOrder = entityColumnOrder;

        super.column = this.sqlColumn;
        super.aes = entityColumnOrder.aes();
    }


    public EntityColumnOrder getEntityColumnOrder() {
        return entityColumnOrder;
    }

    public void setEntityColumnOrder(EntityColumnOrder entityColumnOrder) {
        this.entityColumnOrder = entityColumnOrder;
    }

    public SqlColumn getSqlColumn() {
        return sqlColumn;
    }

    public void setSqlColumn(SqlColumn sqlColumn) {
        this.sqlColumn = sqlColumn;
    }


    /**
     * 变为 Order
     * @return Order
     */
    public Order toOrder(){
        return this;
    }
}
