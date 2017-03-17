package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.BinaryConstant;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.*;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.VariableString;

import java.util.UUID;

/**
 * https://msdn.microsoft.com/en-us/library/ms190286.aspx
 *
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class ExpressionBuilder {

    public static Expression e(String expressionString){
        return new UnknownExpression(expressionString);
    }

    @Deprecated
    public static Expression e(Number numberExpression){
        return new NumberConstant(numberExpression);
    }

    public static Expression e_null(){
        return new KeywordExpression(Keywords.NULL);
    }

    public static Expression e_default(){
        return new KeywordExpression(Keywords.DEFAULT);
    }

    /**
     * constant
     * @param stringExpression
     * @return
     */
    public static StringConstant e_string(String stringExpression){
        return new StringConstant(stringExpression).withQuote();
    }

    /**
     * constant
     * @param stringExpression
     * @return
     */
    public static StringConstant e_n_string(String stringExpression){
        return new StringConstant(stringExpression).withNQuote();
    }

    /**
     * constant
     * @param uuid
     * @return
     */
    public static StringConstant e_string_uuid(String uuid){
        return new StringConstant(UUID.fromString(uuid));
    }
    /**
     * constant
     * @param uuid
     * @return
     */
    public static StringConstant e_string_uuid(UUID uuid){
        return new StringConstant(uuid);
    }

    /**
     * constant
     * @param bin
     * @return
     */
    public static BinaryConstant e_bin(byte[] bin){
        return new BinaryConstant(bin);
    }

    /**
     * constant
     * @param uuid
     * @return
     */
    public static BinaryConstant e_bin_uuid(UUID uuid){
        return new BinaryConstant(uuid);
    }

    /**
     * constant
     * @param uuid
     * @return
     */
    public static BinaryConstant e_bin_uuid(String uuid){
        return new BinaryConstant(UUID.fromString(uuid));
    }

    /**
     * constant
     * @param numberExpression
     * @return
     */
    public static NumberConstant e_number(Number numberExpression){
        return new NumberConstant(numberExpression);
    }

    /**
     * constant
     * @param numberExpression
     * @return
     */
    public static NumberConstant e_money(Number numberExpression){
        return new NumberConstant(numberExpression).withMoney();
    }

    /**
     * Is the name of a variable, or parameter
     * @param variableExpression
     * @return
     */
    public static VariableString e_variable(String variableExpression){
        return new VariableString(variableExpression);
    }

    /**
     * TODO maybe not use GroupExpression
     * Is a subquery that returns one value
     * @param select
     * @return
     */
    public static Expression e(Select select){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(select);
        return groupExpression;
    }

//    public static Expression e(ElementList elementList){
//        return new ElementExpression(elementList);
//    }
    public static Expression e_subquery(Select.QuerySpecification query){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(query);
        return groupExpression;
    }


    /**
     * AtTimeZone
     * @param expression
     * @return
     */
    public static Expression e_at_time_zone(Expression expression,String timeZone){
        return new AtTimeZoneBuilder<Void>()
                .withExpression(expression)
                .withTimezone(timeZone)
                .build();
    }

    /**
     * Case
     * @return
     */
    public static CaseBuilder<Void> e_case(){
        return new CaseBuilder<>();
    }

    /**
     * Coalesce
     * @param expression
     * @return
     */
    public static Expression e_coalesce(Expression... expression){
        return new CoalesceBuilder<Void>()
                .withExpression(expression)
                .build();
    }

    /**
     * NullIf
     * @param expressionLeft
     * @param expressionRight
     * @return
     */
    public static Expression e_nullif(Expression expressionLeft, Expression expressionRight){
        return new NullIfBuilder<Void>()
                .withExpression(expressionLeft)
                .withExpression(expressionRight)
                .build();
    }
}
