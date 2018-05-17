package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * This is a special case
 * Maybe should not be classified as operator
 *
 * @see StringConcatenation#CONCATENATION
 * is same like
 * @see Arithmetic#ADDITION
 *
 * and
 * @see StringConcatenation#CONCATENATION_ASSIGNMENT
 * is same like
 * @see Compound#ADD_ASSIGNMENT
 *
 * and other items are string internal format
 *
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum StringConcatenation implements Operator {

    CONCATENATION("+",Type.String),
    CONCATENATION_ASSIGNMENT("+=",Type.String),
    WILDCARD_MATCH_PERCENT("%",Type.String),
    WILDCARD_MATCH_RANGE("[]",Type.String),
    WILDCARD_MATCH_NOT("^",Type.String),
    WILDCARD_MATCH_ONE("_",Type.String);

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
