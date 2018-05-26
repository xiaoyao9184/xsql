package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.BinaryOperator;

/**
 * BinaryExpressionBuilder
 * Created by xiaoyao9184 on 2018/5/17.
 */
@SuppressWarnings("unused")
public class BinaryExpressionBuilder<ParentBuilder>
        extends ParentHoldBuilder<BinaryExpressionBuilder<ParentBuilder>,ParentBuilder,BinaryExpression> {

    public BinaryExpressionBuilder() {
        super(new BinaryExpression());
    }

    public BinaryExpressionBuilder(BinaryExpression target) {
        super(target);
    }


    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public BinaryExpressionBuilder<ParentBuilder> withExpressionLeft(Expression expression){
        target.setExpressionLeft(expression);
        return this;
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public BinaryExpressionBuilder<ParentBuilder> withExpressionRight(Expression expression){
        target.setExpressionRight(expression);
        return this;
    }

    /**
     * set
     * @param binaryOperator binary operator
     * @return THIS
     */
    public BinaryExpressionBuilder<ParentBuilder> withBinaryOperator(BinaryOperator binaryOperator){
        target.setBinaryOperator(binaryOperator);
        return this;
    }

}
