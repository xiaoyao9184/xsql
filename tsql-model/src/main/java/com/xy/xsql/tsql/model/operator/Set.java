package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Set implements Operator {

    EXCEPT("EXCEPT",Type.Set),
    INTERSECT("INTERSECT",Type.Set),
    UNION("UNION",Type.Set),
    UNION_ALL("UNION ALL",Type.Set);

    private java.lang.String keyword;
    private Type type;

    Set(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Set(java.lang.String keyword, Type type){
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
