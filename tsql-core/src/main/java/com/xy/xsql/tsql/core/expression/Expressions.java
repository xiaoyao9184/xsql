package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.datatype.*;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.expression.KeywordExpression;
import com.xy.xsql.tsql.model.expression.UnknownExpression;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import java.util.UUID;

/**
 * Expression Factory
 *
 * https://msdn.microsoft.com/en-us/library/ms190286.aspx
 *
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class Expressions {

    /**
     * unknown
     * @param expressionString
     * @return
     */
    public static UnknownExpression e(String expressionString){
        return new UnknownExpression(expressionString);
    }

    /**
     * null
     * @return
     */
    public static KeywordExpression e_null(){
        return new Null();
    }

    /**
     * default
     * @return
     */
    public static KeywordExpression e_default(){
        return new Default();
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
    public static LocalVariable e_variable(String variableExpression){
        return new LocalVariable(variableExpression);
    }

    /**
     *
     * @param expression
     * @return
     */
    public static Expression e(Expression expression){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setExpression(expression);
        return groupExpression;
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

    /**
     * TODO maybe not use GroupExpression
     * Is a subquery that returns one value
     * @param query
     * @return
     */
    public static Expression e_subquery(Select.QuerySpecification query){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(query);
        return groupExpression;
    }

    public static Expression e_subquery(Select select){
        GroupExpression groupExpression = new GroupExpression();
        groupExpression.setStatement(select);
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

    public static CaseBuilder<Void> e_case(Expression expression){
        return new CaseBuilder<Void>()
                .withInput(expression);
    }

    /**
     * Coalesce
     * @param expressions
     * @return
     */
    public static Expression e_coalesce(Expression... expressions){
        return new CoalesceBuilder<Void>()
                .withExpression(expressions)
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
