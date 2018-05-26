package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.NullIf;

/**
 * NullIfBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings("unused")
public class NullIfBuilder<ParentBuilder>
        extends ParentHoldBuilder<NullIfBuilder<ParentBuilder>,ParentBuilder,NullIf> {

    private boolean indexFirst = true;

    public NullIfBuilder() {
        super(new NullIf());
    }

    public NullIfBuilder(NullIf target) {
        super(target);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public NullIfBuilder<ParentBuilder> withExpression(Expression expression) {
        if(indexFirst){
            target.setExpressionLeft(expression);
            indexFirst = false;
        }else{
            target.setExpressionRight(expression);
            indexFirst = true;
        }
        return this;
    }

}
