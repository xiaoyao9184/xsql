package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Arithmetic implements Operator, BinaryOperator {
    ADDITION("+", Type.Arithmetic),
    SUBTRACTION("-", Type.Arithmetic),
    MULTIPLICATION("*", Type.Arithmetic),
    DIVISION("/", Type.Arithmetic),
    MODULO("%", Type.Arithmetic);

    private String keyword;
    private Type type;

    Arithmetic(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Arithmetic(String keyword, Type type){
        this.keyword = keyword;
        this.type = type;
    }


    @Override
    public String toString(){
        return this.keyword;
    }

    @Override
    public String getKeyword(){
        return this.keyword;
    }

    @Override
    public Type getType(){
        return this.type;
    }

}
