package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Bitwise implements Operator, BinaryOperator {

    AND("&"),
    AND_EQUALS("&="),
    OR("|"),
    OR_EQUALS("|="),
    XOR("^"),
    XOR_EQUALS("^="),
    NOT("~");

    private String keyword;

    Bitwise(String keyword){
        this.keyword = keyword;
    }


    @Override
    public String toString(){
        return this.keyword;
    }

    @Override
    public String getKeyword(){
        return this.keyword;
    }

}
