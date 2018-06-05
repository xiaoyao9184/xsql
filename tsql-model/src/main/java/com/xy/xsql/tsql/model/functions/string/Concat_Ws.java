package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Concat_Ws
        implements StringFunction, Function.InternalFunction {

    //separator
    private Expression separator;
    //argument1, argument1 [, argumentN]…
    private List<Expression> argumentList;

    public Expression getSeparator() {
        return separator;
    }

    public void setSeparator(Expression separator) {
        this.separator = separator;
    }

    public List<Expression> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<Expression> argumentList) {
        this.argumentList = argumentList;
    }
}
