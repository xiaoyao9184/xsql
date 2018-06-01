package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class FormatMessage
        implements SystemFunction, Function.InternalFunction {

    private Expression msgMumber;
    private StringConstant msgString;
    private List<Expression> paramValueList;

    public Expression getMsgMumber() {
        return msgMumber;
    }

    public void setMsgMumber(Expression msgMumber) {
        this.msgMumber = msgMumber;
    }

    public StringConstant getMsgString() {
        return msgString;
    }

    public void setMsgString(StringConstant msgString) {
        this.msgString = msgString;
    }

    public List<Expression> getParamValueList() {
        return paramValueList;
    }

    public void setParamValueList(List<Expression> paramValueList) {
        this.paramValueList = paramValueList;
    }
}
