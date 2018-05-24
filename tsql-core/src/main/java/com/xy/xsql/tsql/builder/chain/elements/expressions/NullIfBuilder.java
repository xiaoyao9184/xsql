package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.NullIf;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * NullIfBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings("unused")
public class NullIfBuilder<ParentBuilder>
        extends CodeTreeBuilder<NullIfBuilder<ParentBuilder>,ParentBuilder,NullIf> {

    private boolean indexFirst = true;

    public NullIfBuilder() {
        super(new NullIf());
    }

    public NullIfBuilder(NullIf tar) {
        super(tar);
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
