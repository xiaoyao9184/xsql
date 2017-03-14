package com.xy.xsql.tsql.model.predicate;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * expression IS [ NOT ] NULL
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class IsNull implements Predicate {
    //expression IS [ NOT ] NULL
    private Expression expression;
    private boolean useNotOperator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseNotOperator() {
        return useNotOperator;
    }

    public void setUseNotOperator(boolean useNotOperator) {
        this.useNotOperator = useNotOperator;
    }

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(expression)
                .append(Keywords.IS)
                .append(useNotOperator ? Keywords.NOT : null)
                .append(Keywords.NULL);
        return b.build();
    }

}
