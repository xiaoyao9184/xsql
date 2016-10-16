package com.xy.xsql.orm.data.sql.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Value;

/**
 * 参数
 * 符合元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Param
        implements Element, Cloneable{

    protected boolean and;
    protected Column column;
    protected String relationship;
    protected Value value;

    public Param(){

    }

    public Param(boolean and, Column column, String relationship, Value value){
        this.and = and;
        this.column = column;
        this.relationship = relationship;
        this.value = value;
    }

    public boolean isAnd() {
        return and;
    }

    public void setAnd(boolean and) {
        this.and = and;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }


    /**
     * 克隆
     * @return Param
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Param clone() {
        return new Param(this.and, this.column.clone(), this.relationship, this.value);
    }
}
