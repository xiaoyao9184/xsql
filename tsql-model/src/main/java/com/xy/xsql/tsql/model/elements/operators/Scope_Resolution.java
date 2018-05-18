package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Scope_Resolution implements Operator {

    SCOPE_RESOLUTION("::",Type.Scope_Resolution);

    private String keyword;
    private Type type;

    Scope_Resolution(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Scope_Resolution(String keyword, Type type){
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
