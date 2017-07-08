package com.xy.xsql.tsql.core.clause.subquery;

import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * SubQuery Builder
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class SubQueryBuilder {

    /**
     * QuerySpecificationBuilder
     * @return
     */
    public static SelectBuilder.QuerySpecificationBuilder<Void> QUERY(){
        return new SelectBuilder.QuerySpecificationBuilder<>();
    }

    /**
     * QueryExpressionBuilder
     * @return
     */
    public static SelectBuilder.QueryExpressionBuilder<Void> QUERYS(){
        return new SelectBuilder.QueryExpressionBuilder<>();
    }



    public static Select SUB_QUERY(com.xy.xsql.tsql.model.statement.dml.Select.QueryExpression queryExpression){
        return new SelectBuilder()
                .withQuery(queryExpression)
                .build();
    }

    public static Select SUB_QUERY(com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification querySpecification){
        return new SelectBuilder()
                .withQuery(querySpecification)
                .build();
    }
}
