package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;

/**
 * 排序
 * Created by xiaoyao9184 on 2016/7/29.
 */
public class Order
        implements Element, Cloneable{

    protected Column column;
    protected boolean aes;

    public Order(){

    }

    public Order(Column column, boolean aes){
        this.column = column;
        this.aes = aes;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public boolean isAes() {
        return aes;
    }

    public void setAes(boolean aes) {
        this.aes = aes;
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
     * set Aes flag
     * @param aes Aes flag
     * @return This
     */
    public Order withAes(boolean aes){
        this.aes = aes;
        return this;
    }

    /**
     * 克隆
     * @return Order
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Order clone() {
        return new Order(this.column.clone(), this.aes);
    }

}
