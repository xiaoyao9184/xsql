package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;

/**
 * 排序
 * Created by xiaoyao9184 on 2016/7/29.
 */
public class Order
        implements Element, Cloneable{

    protected Column column;
    protected boolean asc;

    public Order(){

    }

    public Order(Column column, boolean asc){
        this.column = column;
        this.asc = asc;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    /**
     * set Column
     * @param column Column
     * @return This
     */
    public Order withColumn(Column column){
        this.column = column;
        return this;
    }

    /**
     * set Asc flag
     * @param asc Asc flag
     * @return This
     */
    public Order withAsc(boolean asc){
        this.asc = asc;
        return this;
    }

    /**
     * 克隆
     * @return Order
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Order clone() {
        return new Order(this.column.clone(), this.asc);
    }

}
