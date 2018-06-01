package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Logical implements Operator, BinaryOperator {

    ALL(Keywords.ALL),
    AND(Keywords.AND),
    ANY(Keywords.ANY),
    BETWEEN(Keywords.BETWEEN),
    EXISTS(Keywords.EXISTS),
    IN(Keywords.IN),
    LIKE(Keywords.LIKE),
    NOT(Keywords.NOT),
    OR(Keywords.OR),
    SOME(Keywords.SOME);

    private String keyword;

    Logical(Keywords keyword){
        this.keyword = keyword.name();
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
