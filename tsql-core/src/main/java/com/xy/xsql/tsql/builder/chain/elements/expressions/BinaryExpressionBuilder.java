package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.BinaryOperator;

/**
 * Created by xiaoyao9184 on 2018/5/17.
 */
public class BinaryExpressionBuilder<ParentBuilder>
        extends CodeTreeBuilder<BinaryExpressionBuilder<ParentBuilder>,ParentBuilder,BinaryExpression> {

    public BinaryExpressionBuilder() {
        super(new BinaryExpression());
    }

    public BinaryExpressionBuilder<ParentBuilder> withExpressionLeft(Expression expression){
        target.setExpressionLeft(expression);
        return this;
    }
    public BinaryExpressionBuilder<ParentBuilder> withExpressionRight(Expression expression){
        target.setExpressionRight(expression);
        return this;
    }

    public BinaryExpressionBuilder<ParentBuilder> withUnary(BinaryOperator binaryOperator){
        target.setBinaryOperator(binaryOperator);
        return this;
    }

}
