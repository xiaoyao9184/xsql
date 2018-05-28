package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_ORDER_BY;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface order_by {

    static b_ORDER_BY ORDER_BY(Expression expression){
        return new b_ORDER_BY(){
            {
                withItem().withExpression(expression);
            }
        };
    }

    static b_ORDER_BY ORDER_BY(Expression expression, b_ORDER_BY.k_ASC ASC){
        return new b_ORDER_BY(){
            {
                withItem().withAsc().withExpression(expression);
            }
        };
    }

    static b_ORDER_BY ORDER_BY(Expression expression, b_ORDER_BY.k_DESC desc){
        return new b_ORDER_BY(){
            {
                withItem().withDesc().withExpression(expression);
            }
        };
    }



    static b_ORDER_BY ORDER_BY(Expression expression, b_ORDER_BY.b_OFFSET offset){
        return new b_ORDER_BY(){
            {
                withItem().withExpression(expression);
                withOffsetFetch(offset.build());
            }
        };
    }

    static b_ORDER_BY ORDER_BY(Expression expression, b_ORDER_BY.k_ASC ASC, b_ORDER_BY.b_OFFSET offset){
        return new b_ORDER_BY(){
            {
                withItem().withAsc().withExpression(expression);
                withOffsetFetch(offset.build());
            }
        };
    }

    static b_ORDER_BY ORDER_BY(Expression expression, b_ORDER_BY.k_DESC desc, b_ORDER_BY.b_OFFSET offset){
        return new b_ORDER_BY(){
            {
                withItem().withDesc().withExpression(expression);
                withOffsetFetch(offset.build());
            }
        };
    }


    static b_ORDER_BY.k_ASC ASC(){
        return null;
    }
    static b_ORDER_BY.k_DESC DESC(){
        return null;
    }
    static b_ORDER_BY.k_ROW ROW(){
        return null;
    }
    static b_ORDER_BY.k_ROWS ROWS(){
        return null;
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH.k_FIRST FIRST(){
        return null;
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH.k_NEXT NEXT(){
        return null;
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY ONLY(){
        return null;
    }


    static b_ORDER_BY.b_OFFSET OFFSET(Integer integerConstant, b_ORDER_BY.k_ROW row){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withIntegerConstant(integerConstant);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Integer integerConstant, b_ORDER_BY.k_ROWS ROWS){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withIntegerConstant(integerConstant)
                        .withUseRows();
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Expression offsetRowCountExpression, b_ORDER_BY.k_ROW row){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withOffsetRowCountExpression(offsetRowCountExpression);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Expression offsetRowCountExpression, b_ORDER_BY.k_ROWS ROWS){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withOffsetRowCountExpression(offsetRowCountExpression)
                        .withUseRows();
            }
        };
    }

    static b_ORDER_BY.b_OFFSET OFFSET(Integer integerConstant, b_ORDER_BY.k_ROW row, b_ORDER_BY.b_OFFSET.b_FETCH fetch){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withIntegerConstant(integerConstant)
                        .withFetch(fetch.build());
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Integer integerConstant, b_ORDER_BY.k_ROWS ROWS, b_ORDER_BY.b_OFFSET.b_FETCH fetch){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withIntegerConstant(integerConstant)
                        .withUseRows()
                        .withFetch(fetch.build());
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Expression offsetRowCountExpression, b_ORDER_BY.k_ROW row, b_ORDER_BY.b_OFFSET.b_FETCH fetch){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withOffsetRowCountExpression(offsetRowCountExpression)
                        .withFetch(fetch.build());
            }
        };
    }
    static b_ORDER_BY.b_OFFSET OFFSET(Expression offsetRowCountExpression, b_ORDER_BY.k_ROWS ROWS, b_ORDER_BY.b_OFFSET.b_FETCH fetch){
        return new b_ORDER_BY.b_OFFSET(){
            {
                withOffsetRowCountExpression(offsetRowCountExpression)
                        .withUseRows()
                        .withFetch(fetch.build());
            }
        };
    }






    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_FIRST first,
            Integer integerConstant,
            b_ORDER_BY.k_ROW row,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchIntegerConstant(integerConstant);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_FIRST first,
            Integer integerConstant,
            b_ORDER_BY.k_ROWS ROWS,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchIntegerConstant(integerConstant)
                        .withUseFetchRows();
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_FIRST first,
            Expression offsetRowCountExpression,
            b_ORDER_BY.k_ROW row,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchOffsetRowCountExpression(offsetRowCountExpression);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_FIRST first,
            Expression offsetRowCountExpression,
            b_ORDER_BY.k_ROWS rows,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchOffsetRowCountExpression(offsetRowCountExpression)
                        .withUseFetchRows();
            }
        };
    }




    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_NEXT next,
            Integer integerConstant,
            b_ORDER_BY.k_ROW row,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchIntegerConstant(integerConstant);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_NEXT next,
            Integer integerConstant,
            b_ORDER_BY.k_ROWS ROWS,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchIntegerConstant(integerConstant)
                        .withUseFetchRows();
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_NEXT next,
            Expression offsetRowCountExpression,
            b_ORDER_BY.k_ROW row,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchOffsetRowCountExpression(offsetRowCountExpression);
            }
        };
    }
    static b_ORDER_BY.b_OFFSET.b_FETCH FETCH(
            b_ORDER_BY.b_OFFSET.b_FETCH.k_NEXT next,
            Expression offsetRowCountExpression,
            b_ORDER_BY.k_ROWS rows,
            b_ORDER_BY.b_OFFSET.b_FETCH.k_ONLY only){
        return new b_ORDER_BY.b_OFFSET.b_FETCH(){
            {
                withFetchOffsetRowCountExpression(offsetRowCountExpression)
                        .withUseFetchRows();
            }
        };
    }

}
