package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class CharIndex
        implements StringFunction, Function.InternalFunction {

    //expressionToFind , expressionToSearch [ , start_location ]
    private Expression expressionToFind;
    private Expression expressionToSearch;
    private Expression startLocation;

    public Expression getExpressionToFind() {
        return expressionToFind;
    }

    public void setExpressionToFind(Expression expressionToFind) {
        this.expressionToFind = expressionToFind;
    }

    public Expression getExpressionToSearch() {
        return expressionToSearch;
    }

    public void setExpressionToSearch(Expression expressionToSearch) {
        this.expressionToSearch = expressionToSearch;
    }

    public Expression getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Expression startLocation) {
        this.startLocation = startLocation;
    }
}
