package com.xy.xsql.tsql.model.elements.expressions.keyword;

import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.expressions.RowValueExpression;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class Default extends KeywordExpression implements RowValueExpression {

    public Default() {
        super(Keywords.DEFAULT);
    }

}
