package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.model.queries.Select;

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



    public static Select SUB_QUERY(Select.QueryExpression queryExpression){
        return new SelectBuilder()
                .withQuery(queryExpression)
                .build();
    }

    public static Select SUB_QUERY(Select.QuerySpecification querySpecification){
        return new SelectBuilder()
                .withQuery(querySpecification)
                .build();
    }
}
