package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Bitwise implements Operator {

    AND("&",Type.Bitwise),
    AND_EQUALS("&=",Type.Bitwise),
    OR("|",Type.Bitwise),
    OR_EQUALS("|=",Type.Bitwise),
    XOR("^",Type.Bitwise),
    XOR_EQUALS("^=",Type.Bitwise),
    NOT("~",Type.Bitwise);

    private java.lang.String keyword;
    private Type type;

    Bitwise(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Bitwise(java.lang.String keyword, Type type){
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
