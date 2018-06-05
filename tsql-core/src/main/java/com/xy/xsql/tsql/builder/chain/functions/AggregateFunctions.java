package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.aggregate.*;
import com.xy.xsql.tsql.model.queries.select.Over;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface AggregateFunctions {

    static Avg f_avg_all(Expression expression){
        Avg f = new Avg();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Avg f_avg_distinct(Expression expression){
        Avg f = new Avg();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Avg f_avg(Expression expression){
        Avg f = new Avg();
        f.setExpression(expression);
        return f;
    }
    static Avg f_avg(Expression expression, Over over){
        Avg f = new Avg();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static CheckSum_Agg f_checksum_agg_all(
            Expression expression){
        CheckSum_Agg f = new CheckSum_Agg();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static CheckSum_Agg f_checksum_agg_distinct(
            Expression expression){
        CheckSum_Agg f = new CheckSum_Agg();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static CheckSum_Agg f_checksum_agg(
            Expression expression){
        CheckSum_Agg f = new CheckSum_Agg();
        f.setExpression(expression);
        return f;
    }

    static Count f_count_all(
            Expression expression){
        Count f = new Count();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Count f_count_distinct(
            Expression expression){
        Count f = new Count();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Count f_count(
            Expression expression){
        Count f = new Count();
        f.setExpression(expression);
        return f;
    }
    static Count f_count(){
        Count f = new Count();
        f.setUseAll(true);
        return f;
    }
    static Count f_count_all(
            Expression expression,
            Over over){
        Count f = new Count();
        f.setUseAll(true);
        f.setExpression(expression);
        f.setOver(over);
        return f;
    }
    static Count f_count_distinct(
            Expression expression,
            Over over){
        Count f = new Count();
        f.setUseDistinct(true);
        f.setExpression(expression);
        f.setOver(over);
        return f;
    }
    static Count f_count(
            Expression expression,
            Over over){
        Count f = new Count();
        f.setExpression(expression);
        f.setOver(over);
        return f;
    }
    static Count f_count(
            Over over){
        Count f = new Count();
        f.setUseAll(true);
        f.setOver(over);
        return f;
    }

    static Count_Big f_count_big_all(
            Expression expression){
        Count_Big f = new Count_Big();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Count_Big f_count_big_distinct(
            Expression expression){
        Count_Big f = new Count_Big();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Count_Big f_count_big(
            Expression expression){
        Count_Big f = new Count_Big();
        f.setExpression(expression);
        return f;
    }
    static Count_Big f_count_big(){
        Count_Big f = new Count_Big();
        f.setUseAll(true);
        return f;
    }
    static Count_Big f_count_big_all(
            Expression expression,
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Count_Big f = new Count_Big();
        f.setUseAll(true);
        f.setExpression(expression);
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Count_Big f_count_big_distinct(
            Expression expression,
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Count_Big f = new Count_Big();
        f.setUseDistinct(true);
        f.setExpression(expression);
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Count_Big f_count_big(
            Expression expression,
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Count_Big f = new Count_Big();
        f.setExpression(expression);
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Count_Big f_count_big(
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Count_Big f = new Count_Big();
        f.setUseAll(true);
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }

    static Grouping f_grouping(
            Expression columnExpression){
        Grouping f = new Grouping();
        f.setColumnExpression(columnExpression);
        return f;
    }

    static Grouping_Id f_grouping(
            Expression... columnExpression){
        Grouping_Id f = new Grouping_Id();
        f.setColumnExpressionList(Arrays.asList(columnExpression));
        return f;
    }
    
    static Max f_max_all(Expression expression){
        Max f = new Max();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Max f_max_distinct(Expression expression){
        Max f = new Max();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Max f_max(Expression expression){
        Max f = new Max();
        f.setExpression(expression);
        return f;
    }
    static Max f_max(Expression expression, Over over){
        Max f = new Max();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static Min f_min_all(Expression expression){
        Min f = new Min();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Min f_min_distinct(Expression expression){
        Min f = new Min();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Min f_min(Expression expression){
        Min f = new Min();
        f.setExpression(expression);
        return f;
    }
    static Min f_min(Expression expression, Over over){
        Min f = new Min();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static StdDev f_dtddev_all(Expression expression){
        StdDev f = new StdDev();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static StdDev f_dtddev_distinct(Expression expression){
        StdDev f = new StdDev();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static StdDev f_dtddev(Expression expression){
        StdDev f = new StdDev();
        f.setExpression(expression);
        return f;
    }
    static StdDev f_dtddev(Expression expression, Over over){
        StdDev f = new StdDev();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static StdDevp f_stddevp_all(Expression expression){
        StdDevp f = new StdDevp();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static StdDevp f_stddevp_distinct(Expression expression){
        StdDevp f = new StdDevp();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static StdDevp f_stddevp(Expression expression){
        StdDevp f = new StdDevp();
        f.setExpression(expression);
        return f;
    }
    static StdDevp f_stddevp(Expression expression, Over over){
        StdDevp f = new StdDevp();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static Sum f_sum_all(Expression expression){
        Sum f = new Sum();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Sum f_sum_distinct(Expression expression){
        Sum f = new Sum();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Sum f_sum(Expression expression){
        Sum f = new Sum();
        f.setExpression(expression);
        return f;
    }
    static Sum f_sum(Expression expression, Over over){
        Sum f = new Sum();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static Var f_var_all(Expression expression){
        Var f = new Var();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Var f_var_distinct(Expression expression){
        Var f = new Var();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Var f_var(Expression expression){
        Var f = new Var();
        f.setExpression(expression);
        return f;
    }
    static Var f_var(Expression expression, Over over){
        Var f = new Var();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

    static Varp f_varp_all(Expression expression){
        Varp f = new Varp();
        f.setUseAll(true);
        f.setExpression(expression);
        return f;
    }
    static Varp f_varp_distinct(Expression expression){
        Varp f = new Varp();
        f.setUseDistinct(true);
        f.setExpression(expression);
        return f;
    }
    static Varp f_varp(Expression expression){
        Varp f = new Varp();
        f.setExpression(expression);
        return f;
    }
    static Varp f_varp(Expression expression, Over over){
        Varp f = new Varp();
        f.setExpression(expression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }
}
