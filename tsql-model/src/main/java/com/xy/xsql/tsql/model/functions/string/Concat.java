package com.xy.xsql.tsql.model.functions.string;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Concat
        implements StringFunction, Function.InternalFunction {

    //expressionToFind , expressionToSearch [ , start_location ]
    private List<Expression> stringValueList;

    public List<Expression> getStringValueList() {
        return stringValueList;
    }

    public void setStringValueList(List<Expression> stringValueList) {
        this.stringValueList = stringValueList;
    }
}
