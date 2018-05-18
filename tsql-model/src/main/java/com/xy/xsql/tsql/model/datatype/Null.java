package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.expressions.KeywordExpression;
import com.xy.xsql.tsql.model.elements.expressions.RowValueExpression;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class Null extends KeywordExpression implements RowValueExpression {

    public Null() {
        super(Keywords.NULL);
    }

}
