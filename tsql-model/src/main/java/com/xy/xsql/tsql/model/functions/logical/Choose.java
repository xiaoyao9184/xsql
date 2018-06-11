package com.xy.xsql.tsql.model.functions.logical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.json.JsonFunction;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Choose
        implements LogicalFunction, Function.InternalFunction {

    private Expression index;
    private List<Expression> valList;

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = index;
    }

    public List<Expression> getValList() {
        return valList;
    }

    public void setValList(List<Expression> valList) {
        this.valList = valList;
    }
}
