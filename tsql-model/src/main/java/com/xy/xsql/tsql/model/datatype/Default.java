package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.KeywordExpression;
import com.xy.xsql.tsql.model.expression.RowValueExpression;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class Default extends KeywordExpression implements RowValueExpression {

    public Default() {
        super(Keywords.DEFAULT);
    }

}
