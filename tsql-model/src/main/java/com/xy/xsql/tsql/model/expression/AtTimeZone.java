package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/mt612795.aspx
 *
 inputdate AT TIME ZONE timezone

 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class AtTimeZone implements Expression {
    //inputdate
    private Expression inputExpression;
    //timezone
    private StringConstant timezone;

    public Expression getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
    }

    public StringConstant getTimezone() {
        return timezone;
    }

    public void setTimezone(StringConstant timezone) {
        this.timezone = timezone;
    }


    public AtTimeZone withInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
        return this;
    }

    public AtTimeZone withTimezone(StringConstant timezone) {
        this.timezone = timezone;
        return this;
    }


    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(inputExpression)
                .append(Keywords.Key.AT)
                .append(Keywords.Key.TIME)
                .append(Keywords.Key.ZONE)
                .append(timezone)
                .build();
    }
}
