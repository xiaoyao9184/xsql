package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * CoalesceBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings("unused")
public class CoalesceBuilder<ParentBuilder>
        extends CodeTreeBuilder<CoalesceBuilder<ParentBuilder>,ParentBuilder,Coalesce> {

    public CoalesceBuilder() {
        super(new Coalesce());
    }

    public CoalesceBuilder(Coalesce tar) {
        super(tar);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    @Deprecated
    public CoalesceBuilder<ParentBuilder> withExpression(Expression expression) {
        initAdd(expression,
                target::getExpressionList,
                target::setExpressionList);
        return this;
    }

    /**
     * set
     * @param expressions Expression
     * @return THIS
     */
    public CoalesceBuilder<ParentBuilder> withExpression(Expression... expressions) {
        if(CheckUtil.isNullOrEmpty(expressions)){
            return this;
        }
        initAdd(Arrays.asList(expressions),
                target::getExpressionList,
                target::setExpressionList);
        return this;
    }

}
