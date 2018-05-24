package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.AtTimeZone;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * Querie Factory
 * Created by xiaoyao9184 on 2017/3/10.
 */
@SuppressWarnings("unused")
public interface Queries {


    /**
     * Quick
     * @return WithBuilder
     */
    static WithBuilder<Void> $With(){
        return new WithBuilder<>();
    }

    /**
     * Quick build
     * @param expression Expression
     * @param timezone timezone
     * @return AtTimeZone
     */
    static AtTimeZone $AtTimeZone(Expression expression, String timezone){
        return new AtTimeZoneBuilder<Void>()
                .withExpression(expression)
                .withTimezone(timezone)
                .build();
    }

    /**
     * Quick in
     * @return SelectBuilder
     */
    static SelectBuilder $Select(){
        return new SelectBuilder();
    }


    /**
     * Quick
     * @return QuerySpecificationBuilder
     */
    static SelectBuilder.QuerySpecificationBuilder<Void> $Query(){
        return new SelectBuilder.QuerySpecificationBuilder<>();
    }

    /**
     * Quick
     * @return QueryExpressionBuilder
     */
    static SelectBuilder.QueryExpressionBuilder<Void> $Querys(){
        return new SelectBuilder.QueryExpressionBuilder<>();
    }


    /**
     * Quick
     * @param queryExpression QueryExpression
     * @return Select
     */
    static Select $SubQuery(Select.QueryExpression queryExpression){
        return new SelectBuilder()
                .withQuery(queryExpression)
                .build();
    }

    /**
     * Quick
     * @param querySpecification QuerySpecification
     * @return Select
     */
    static Select $SubQuery(Select.QuerySpecification querySpecification){
        return new SelectBuilder()
                .withQuery(querySpecification)
                .build();
    }

    /**
     * Quick
     * @return TableValueConstructorBuilder
     */
    static TableValueConstructorBuilder<Void> $Values(){
        return new TableValueConstructorBuilder<>();
    }

    /**
     * Quick
     * @return UpdateBuilder
     */
    static UpdateBuilder $Update(){
        return new UpdateBuilder();
    }

}
