package com.xy.xsql.tsql.core.clause.subquery;

import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class SubQueryBuilder {

    public static SelectBuilder.QuerySpecificationBuilder<Void> QUERY(){
        return new SelectBuilder.QuerySpecificationBuilder<>();
    }
}
