package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Coalesce;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.NullIf;

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
                tar::getExpressionList,
                tar::setExpressionList);
        return this;
    }

    public CoalesceBuilder<ParentBuilder> withExpression(Expression... expressions) {
        initAdd(Arrays.asList(expressions),
                tar::getExpressionList,
                tar::setExpressionList);
        return this;
    }

}
