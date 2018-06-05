package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.ranking.*;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface RankingFunctions {


    static Dense_Rank f_dense_rank(
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Dense_Rank f =  new Dense_Rank();
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Dense_Rank f_dense_rank(
            Over.OrderBy orderBy){
        Dense_Rank f =  new Dense_Rank();
        f.setOrderBy(orderBy);
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
            Over.OrderBy orderBy){
        Ntile f =  new Ntile();
        f.setIntegerExpression(integerExpression);
        f.setOrderBy(orderBy);
        return f;
    }
    static Rank f_rank(
            Over.PartitionBy partitionBy,
            Over.OrderBy orderBy){
        Rank f =  new Rank();
        f.setPartitionBy(partitionBy);
        f.setOrderBy(orderBy);
        return f;
    }
    static Rank f_rank(
            Over.OrderBy orderBy){
        Rank f =  new Rank();
        f.setOrderBy(orderBy);
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
            Over.OrderBy orderBy){
        Row_Number f =  new Row_Number();
        f.setOrderBy(orderBy);
        return f;
    }

}
