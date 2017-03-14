package com.xy.xsql.tsql.model.predicate;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * EXISTS ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Exists implements Predicate {
    //EXISTS ( subquery )
    private Expression expression;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Select getSubquery() {
        return subquery;
    }

    public void setSubquery(Select subquery) {
        this.subquery = subquery;
    }

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(Keywords.EXISTS)
                .append(Other.GROUP_START)
                .append(subquery)
                .append(Other.GROUP_END);
        return b.build();
    }
}
