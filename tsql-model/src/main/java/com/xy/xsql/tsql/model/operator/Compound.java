package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Compound implements Operator {

    ADD_EQUALS("+=",Type.Compound),
    SUBTRACT_EQUALS("-=",Type.Compound),
    MULTIPLY_EQUALS("*=",Type.Compound),
    DIVIDE_EQUALS("/=",Type.Compound),
    MODULO_EQUALS("%=",Type.Compound),
    BITWISE_AND_EQUALS("&=",Type.Compound),
    BITWISE_EXCLUSIVE_OR_EQUALS("^=",Type.Compound),
    BITWISE_OR_EQUALS("|=",Type.Compound);

    private String keyword;
    private Type type;

    Compound(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Compound(String keyword, Type type){
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
