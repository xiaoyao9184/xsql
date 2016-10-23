package com.xy.xsql.orm.data.sql.relationship;

import com.xy.xsql.orm.data.sql.Relationship;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Unknow implements Relationship {


    public Unknow(String relationship) {

    }

    public String toString(){
        return "=";
    }
}
