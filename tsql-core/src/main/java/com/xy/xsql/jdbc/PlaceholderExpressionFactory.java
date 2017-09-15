package com.xy.xsql.jdbc;

import com.xy.xsql.jdbc.model.JSqlPlaceholderExpression;
import com.xy.xsql.jpql.model.JPqlNamedPlaceholderExpression;
import com.xy.xsql.jpql.model.JPqlPlaceholderExpression;

/**
 * Created by xiaoyao9184 on 2017/9/15.
 */
public class PlaceholderExpressionFactory {

    /**
     * JDBC Sql Placeholder
     * @return ?
     */
    public static JSqlPlaceholderExpression placeholder(){
        return new JSqlPlaceholderExpression();
    }

    /**
     * JPql Placeholder
     * @param index index
     * @return ?index
     */
    public static JPqlPlaceholderExpression placeholder(int index){
        return new JPqlPlaceholderExpression(index);
    }

    /**
     * JPql Named Placeholder
     * @param name name
     * @return :name
     */
    public static JPqlNamedPlaceholderExpression placeholder(String name){
        return new JPqlNamedPlaceholderExpression(name);
    }

}
