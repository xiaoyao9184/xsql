package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.orm.data.sql.element.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Arithmetic implements Operator {
    ADDITION("+", Type.Arithmetic),
    SUBTRACTION("-", Type.Arithmetic),
    MULTIPLICATION("*", Type.Arithmetic),
    DIVISION("/", Type.Arithmetic),
    MODULO("%", Type.Arithmetic);

    private java.lang.String keyword;
    private Type type;

    Arithmetic(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Arithmetic(java.lang.String keyword, Type type){
        this.keyword = keyword;
        this.type = type;
    }

    public java.lang.String getKeyword(){
        return this.keyword;
    }

    public Type getType(){
        return this.type;
    }

}
