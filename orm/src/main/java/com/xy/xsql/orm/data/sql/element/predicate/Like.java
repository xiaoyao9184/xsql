package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.datatype.StringConstant;

import java.util.List;

/**
 * string_expression [ NOT ] LIKE string_expression
 [ ESCAPE 'escape_character' ]
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Like implements Predicate {
    //string_expression [ NOT ] LIKE string_expression
    //[ ESCAPE 'escape_character' ]
    private Expression expression;
    private boolean useNotOperator;
    private Expression operatorExpression;
    private StringConstant escapeCharacter;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getOperatorExpression() {
        return operatorExpression;
    }

    public void setOperatorExpression(Expression operatorExpression) {
        this.operatorExpression = operatorExpression;
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

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(useNotOperator ? GrammarEnum.NOT : null)
                .append(OperatorEnum.LIKE)
                .append(operatorExpression);
        if(escapeCharacter != null){
            b.append(GrammarEnum.ESCAPE)
                    .append(escapeCharacter);
        }
        return b.build();
    }

}
