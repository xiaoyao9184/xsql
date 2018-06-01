package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Set implements Operator {

    EXCEPT(Keywords.EXCEPT),
    INTERSECT(Keywords.INTERSECT),
    UNION(Keywords.UNION),
    UNION_ALL("UNION ALL");

    private String keyword;

    Set(Keywords keyword){
        this.keyword = keyword.name();
    }

    Set(String keyword){
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
