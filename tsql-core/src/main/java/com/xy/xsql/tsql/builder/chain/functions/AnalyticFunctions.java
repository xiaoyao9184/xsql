package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.functions.analytic.*;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface AnalyticFunctions {

    static Cume_Dist f_cume_dist(
            Over over){
        Cume_Dist f = new Cume_Dist();
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }
    static First_Value f_first_value(
            Expression scalarExpression,
            Over over){
        First_Value f = new First_Value();
        f.setScalarExpression(scalarExpression);
        f.setOver(over);
        return f;
    }
    static First_Value f_first_value(
            Over over){
        First_Value f = new First_Value();
        f.setOver(over);
        return f;
    }
    static Lag f_lag(
            Expression scalarExpression,
            Expression offset,
            Expression defaultValue,
            Over over){
        Lag f = new Lag();
        f.setScalarExpression(scalarExpression);
        f.setOffset(offset);
        f.setDefaultValue(defaultValue);
        f.setOver(over);
        return f;
    }
    static Lag f_lag(
            ScalarExpression scalarExpression,
            Over over){
        Lag f = new Lag();
        f.setScalarExpression(scalarExpression);
        f.setOver(over);
        return f;
    }
    static Last_Value f_last_value(
            Expression scalarExpression,
            Over over){
        Last_Value f = new Last_Value();
        f.setScalarExpression(scalarExpression);
        f.setOver(over);
        return f;
    }
    static Last_Value f_last_value(
            Over over){
        Last_Value f = new Last_Value();
        f.setOver(over);
        return f;
    }
    static Lead f_lead(
            Expression scalarExpression,
            Expression offset,
            Expression defaultValue,
            Over over){
        Lead f = new Lead();
        f.setScalarExpression(scalarExpression);
        f.setOffset(offset);
        f.setDefaultValue(defaultValue);
        f.setOver(over);
        return f;
    }
    static Lead f_lead(
            ScalarExpression scalarExpression,
            Over over){
        Lead f = new Lead();
        f.setScalarExpression(scalarExpression);
        f.setOver(over);
        return f;
    }
    static Percent_Rank f_percent_rank(
            Over over){
        Percent_Rank f = new Percent_Rank();
        f.setOver(over);
        return f;
    }
    static Percentile_Cont f_percentile_cont(
            ScalarExpression numericLiteral,
            OrderBy.Item orderBy,
            Over over){
        Percentile_Cont f = new Percentile_Cont();
        f.setNumericLiteral(numericLiteral);
        f.setOrderBy(orderBy);
        f.setOver(over);
        return f;
    }
    static Percentile_Disc f_percentile_disc(
            ScalarExpression numericLiteral,
            OrderBy.Item orderBy,
            Over over){
        Percentile_Disc f = new Percentile_Disc();
        f.setNumericLiteral(numericLiteral);
        f.setOrderBy(orderBy);
        f.setOver(over);
        return f;
    }


    static OrderBy.Item $Within_Group_Order_By(Expression expression){
        return new OrderByBuilder.OrderByItemBuilder<Void>()
                .withExpression(expression)
                .build();
    }

    static OrderBy.Item $Within_Group_Order_By_Asc(Expression expression){
        return new OrderByBuilder.OrderByItemBuilder<Void>()
                .withExpression(expression)
                .withAsc()
                .build();
    }

    static OrderBy.Item $Within_Group_Order_By_Desc(Expression expression){
        return new OrderByBuilder.OrderByItemBuilder<Void>()
                .withExpression(expression)
                .withDesc()
                .build();
    }
}
