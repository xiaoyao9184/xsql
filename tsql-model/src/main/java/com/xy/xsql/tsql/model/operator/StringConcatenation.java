package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum StringConcatenation implements Operator {

    CONCATENATION("+",Type.String),
    CONCATENATION_SET("+=",Type.String);

    private String keyword;
    private Type type;

    StringConcatenation(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    StringConcatenation(String keyword, Type type){
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
