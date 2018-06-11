package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.json.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface JsonFunctions {


    static IsJson f_isjson(
            Expression expression){
        IsJson f = new IsJson();
        f.setExpression(expression);
        return f;
    }
    static Json_Query f_json_query(
            Expression expression,
            String path){
        Json_Query f = new Json_Query();
        f.setExpression(expression);
        f.setPath(path);
        return f;
    }
    static Json_Query f_json_query(
            Expression expression){
        Json_Query f = new Json_Query();
        f.setExpression(expression);
        return f;
    }
    static Json_Modify f_json_modify(
            Expression expression,
            String path,
            Expression newValue){
        Json_Modify f = new Json_Modify();
        f.setExpression(expression);
        f.setPath(path);
        f.setNewValue(newValue);
        return f;
    }
    static Json_Value f_json_value(
            Expression expression,
            String path){
        Json_Value f = new Json_Value();
        f.setExpression(expression);
        f.setPath(path);
        return f;
    }

}
