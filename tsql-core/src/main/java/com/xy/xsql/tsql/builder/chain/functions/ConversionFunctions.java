package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.conversion.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface ConversionFunctions {

    static Cast f_cast(
            Expression expression,
            DataType dataType,
            Number length){
        Cast f = new Cast();
        f.setExpression(expression);
        f.setDataType(dataType);
        f.setLength(length);
        return f;
    }
    static Cast f_cast(
            Expression expression,
            DataType dataType){
        Cast f = new Cast();
        f.setExpression(expression);
        f.setDataType(dataType);
        return f;
    }
    static Convert f_convert(
            DataType dataType,
            Number length,
            Expression expression,
            Expression style){
        Convert f = new Convert();
        f.setDataType(dataType);
        f.setLength(length);
        f.setExpression(expression);
        f.setStyle(style);
        return f;
    }
    static Convert f_convert(
            DataType dataType,
            Number length,
            Expression expression){
        Convert f = new Convert();
        f.setDataType(dataType);
        f.setLength(length);
        f.setExpression(expression);
        return f;
    }
    static Convert f_convert(
            DataType dataType,
            Expression expression,
            Expression style){
        Convert f = new Convert();
        f.setDataType(dataType);
        f.setExpression(expression);
        f.setStyle(style);
        return f;
    }
    static Convert f_convert(
            DataType dataType,
            Expression expression){
        Convert f = new Convert();
        f.setDataType(dataType);
        f.setExpression(expression);
        return f;
    }
    static Parse f_parse(
            Expression stringValue,
            DataType dataType,
            StringConstant culture){
        Parse f = new Parse();
        f.setStringValue(stringValue);
        f.setDataType(dataType);
        f.setCulture(culture);
        return f;
    }
    static Parse f_parse(
            Expression stringValue,
            DataType dataType){
        Parse f = new Parse();
        f.setStringValue(stringValue);
        f.setDataType(dataType);
        return f;
    }

    static Try_Cast f_try_cast(
            Expression expression,
            DataType dataType,
            Number length){
        Try_Cast f = new Try_Cast();
        f.setExpression(expression);
        f.setDataType(dataType);
        f.setLength(length);
        return f;
    }
    static Convert f_try_convert(
            DataType dataType,
            Number length,
            Expression expression,
            Expression style){
        Convert f = new Convert();
        f.setDataType(dataType);
        f.setLength(length);
        f.setExpression(expression);
        f.setStyle(style);
        return f;
    }
    static Try_Convert f_try_convert(
            DataType dataType,
            Number length,
            Expression expression){
        Try_Convert f = new Try_Convert();
        f.setDataType(dataType);
        f.setLength(length);
        f.setExpression(expression);
        return f;
    }
    static Try_Convert f_try_convert(
            DataType dataType,
            Expression expression,
            Expression style){
        Try_Convert f = new Try_Convert();
        f.setDataType(dataType);
        f.setExpression(expression);
        f.setStyle(style);
        return f;
    }
    static Try_Convert f_try_convert(
            DataType dataType,
            Expression expression){
        Try_Convert f = new Try_Convert();
        f.setDataType(dataType);
        f.setExpression(expression);
        return f;
    }
}
