package com.xy.xsql.tsql.model.element;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * TODO
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class ColumnName
        implements Block, Expression, Cloneable {

    protected TableName table;
    private String name;

    public ColumnName() {
        this.name = "*";
    }

    public ColumnName(String name) {
        this.name = name;
    }

    public ColumnName(TableName table, String name) {
        this.name = name;
        this.table = table;
    }

    public TableName getTable(){
        return table;
    }

    public void setTable(TableName table){
        this.table = table;
    }


    /**
     * set Table
     * @param table Table
     * @return This
     */
    private ColumnName withTable(TableName table) {
        this.table = table;
        return this;
    }



    /**
     * 变为 表字段名
     * @return table.column
     */
    public String getFullName(){
        if(table == null){
            return this.toString();
        }
        return table.getFullName() + "." + this.name;
    }


    /**
     * 克隆
     * @return Column
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public ColumnName clone() {
        return new ColumnName(this.name)
                .withTable(this.table.clone());
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public List<Block> toBlockList() {
        return null;
    }

}
