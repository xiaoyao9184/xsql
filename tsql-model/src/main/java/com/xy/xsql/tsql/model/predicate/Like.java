package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.Expression;

/**
 * string_expression [ NOT ] LIKE string_expression
 [ ESCAPE 'escape_character' ]
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Like implements Predicate, Expression {
    //string_expression [ NOT ] LIKE string_expression
    //[ ESCAPE 'escape_character' ]
    private Expression expression;
    private boolean useNotOperator;
    private Expression likeExpression;
    private StringConstant escapeCharacter;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getLikeExpression() {
        return likeExpression;
    }

    public void setLikeExpression(Expression likeExpression) {
        this.likeExpression = likeExpression;
    }

    public boolean isUseNotOperator() {
        return useNotOperator;
    }

    public void setUseNotOperator(boolean useNotOperator) {
        this.useNotOperator = useNotOperator;
    }

    public StringConstant getEscapeCharacter() {
        return escapeCharacter;
    }

    public void setEscapeCharacter(StringConstant escapeCharacter) {
        this.escapeCharacter = escapeCharacter;
    }

}
