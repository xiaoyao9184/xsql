package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.KeywordExpression;
import com.xy.xsql.tsql.model.expression.RowValueExpression;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class Null extends KeywordExpression implements RowValueExpression {

    public Null() {
        super(Keywords.NULL);
    }

}
