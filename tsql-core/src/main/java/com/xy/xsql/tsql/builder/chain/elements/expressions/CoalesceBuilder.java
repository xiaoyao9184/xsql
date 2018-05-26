package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * CoalesceBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings("unused")
public class CoalesceBuilder<ParentBuilder>
        extends ParentHoldBuilder<CoalesceBuilder<ParentBuilder>,ParentBuilder,Coalesce> {

    public CoalesceBuilder() {
        super(new Coalesce());
    }

    public CoalesceBuilder(Coalesce target) {
        super(target);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    @Deprecated
    public CoalesceBuilder<ParentBuilder> withExpression(Expression expression) {
        list(target::getExpressionList, target::setExpressionList)
                .add(expression);
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
        list(target::getExpressionList, target::setExpressionList)
                .addAll(expressions);
        return this;
    }

}
