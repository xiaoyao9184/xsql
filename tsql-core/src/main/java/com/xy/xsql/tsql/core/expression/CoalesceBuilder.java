package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Coalesce;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class CoalesceBuilder<ParentBuilder>
        extends CodeTreeBuilder<CoalesceBuilder<ParentBuilder>,ParentBuilder,Coalesce> {

    public CoalesceBuilder() {
        super(new Coalesce());
    }

    public CoalesceBuilder(Coalesce tar) {
        super(tar);
    }

    @Deprecated
    public CoalesceBuilder<ParentBuilder> withExpression(Expression expression) {
        initAdd(expression,
                target::getExpressionList,
                target::setExpressionList);
        return this;
    }

    public CoalesceBuilder<ParentBuilder> withExpression(Expression... expressions) {
        initAdd(Arrays.asList(expressions),
                target::getExpressionList,
                target::setExpressionList);
        return this;
    }

}
