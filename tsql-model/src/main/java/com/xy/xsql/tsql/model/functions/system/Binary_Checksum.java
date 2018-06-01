package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Binary_Checksum
        implements SystemFunction, Function.InternalFunction {

    private List<Expression> expressionList;

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }
}
