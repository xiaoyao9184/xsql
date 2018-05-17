package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Unary implements Operator, BinaryOperator {

    POSITIVE("+",Type.Unary),
    NEGATIVE("-",Type.Unary),
    COMPLEMENT("~",Type.Unary);

    private String keyword;
    private Type type;

    Unary(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Unary(String keyword, Type type){
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
