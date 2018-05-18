package com.xy.xsql.tsql.model.element.collation;

import com.xy.xsql.tsql.model.queries.Clause;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class Collate implements Clause {

    private String name;

    public Collate(){}

    public Collate(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
