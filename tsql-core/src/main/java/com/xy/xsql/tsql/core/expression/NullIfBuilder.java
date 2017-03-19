package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.NullIf;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class NullIfBuilder<ParentBuilder>
        extends CodeTreeBuilder<NullIfBuilder<ParentBuilder>,ParentBuilder,NullIf> {

    private boolean indexFirst = true;

    public NullIfBuilder() {
        super(new NullIf());
    }

    public NullIfBuilder(NullIf tar) {
        super(tar);
    }

    public NullIfBuilder<ParentBuilder> withExpression(Expression expression) {
        if(indexFirst){
            tar.setExpressionLeft(expression);
            indexFirst = false;
        }else{
            tar.setExpressionRight(expression);
            indexFirst = true;
        }
        return this;
    }


}
