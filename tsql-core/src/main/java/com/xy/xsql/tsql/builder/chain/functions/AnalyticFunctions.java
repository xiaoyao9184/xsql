package com.xy.xsql.tsql.builder.chain.functions;

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
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Cume_Dist f = new Cume_Dist();
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Cume_Dist f_cume_dist(
            Over.OrderBy orderBy){
        Cume_Dist f = new Cume_Dist();
        f.setOrderBy(orderBy);
        return f;
    }
    static First_Value f_first_value(
            ScalarExpression scalarExpression,
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
            ScalarExpression scalarExpression,
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
            ScalarExpression scalarExpression,
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
    static Lead f_Lead(
            ScalarExpression scalarExpression,
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
}
