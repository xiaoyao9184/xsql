package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Logical implements Operator {

    ALL(Keywords.ALL, Type.Logical),
    AND(Keywords.AND, Type.Logical),
    ANY(Keywords.ANY, Type.Logical),
    BETWEEN(Keywords.BETWEEN, Type.Logical),
    EXISTS(Keywords.EXISTS, Type.Logical),
    IN(Keywords.IN, Type.Logical),
    LIKE(Keywords.LIKE, Type.Logical),
    NOT(Keywords.NOT, Type.Logical),
    OR(Keywords.OR, Type.Logical),
    SOME(Keywords.SOME, Type.Logical);

    private java.lang.String keyword;
    private Type type;

    Logical(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Logical(java.lang.String keyword, Type type){
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
