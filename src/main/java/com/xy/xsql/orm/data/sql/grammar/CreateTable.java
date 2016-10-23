package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class CreateTable implements Element {

    public CreateTable() {

    }

    public String toString(){
        return "CREATE TABLE";
    }

}