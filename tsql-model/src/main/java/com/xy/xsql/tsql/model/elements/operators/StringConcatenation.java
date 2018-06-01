package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

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

    CONCATENATION("+"),
    CONCATENATION_ASSIGNMENT("+="),
    WILDCARD_MATCH_PERCENT("%"),
    WILDCARD_MATCH_RANGE("[]"),
    WILDCARD_MATCH_NOT("^"),
    WILDCARD_MATCH_ONE("_");

    private String keyword;

    StringConcatenation(String keyword){
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
