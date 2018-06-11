package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.ranking.*;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface RankingFunctions {

    static Dense_Rank f_dense_rank(
            Over over){
        Dense_Rank f =  new Dense_Rank();
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }
    static Ntile f_ntile(
            Expression integerExpression,
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Ntile f =  new Ntile();
        f.setIntegerExpression(integerExpression);
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Ntile f_ntile(
            Expression integerExpression,
            Over over){
        Ntile f =  new Ntile();
        f.setIntegerExpression(integerExpression);
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }
    static Rank f_rank(
            Over over){
        Rank f =  new Rank();
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }
    static Row_Number f_row_number(
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Row_Number f =  new Row_Number();
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Row_Number f_row_number(
            Over over){
        Row_Number f =  new Row_Number();
        f.setPartitionBy(over.getPartitionBy());
        f.setOrderBy(over.getOrderBy());
        return f;
    }

}
