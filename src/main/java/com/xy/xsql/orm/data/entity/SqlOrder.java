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
public class SqlOrder extends Order {

    private SqlColumn sqlColumn;
    protected EOrder eOrder;


    public SqlOrder(EOrder eOrder, EColumn eColumn, Field field, Table table){
        this.sqlColumn = new SqlColumn(eColumn, field, table);
        this.eOrder = eOrder;

        super.column = this.sqlColumn;
        super.aes = eOrder.aes();
    }


    public EOrder geteOrder() {
        return eOrder;
    }

    public void seteOrder(EOrder eOrder) {
        this.eOrder = eOrder;
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
