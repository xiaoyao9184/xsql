package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

import java.util.List;

/**
 * expression IS [ NOT ] NULL
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class IsNull implements Predicate {
    //expression IS [ NOT ] NULL
    private Expression expression;
    private boolean useNotOperator;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseNotOperator() {
        return useNotOperator;
    }

    public void setUseNotOperator(boolean useNotOperator) {
        this.useNotOperator = useNotOperator;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(GrammarEnum.IS)
                .append(useNotOperator ? GrammarEnum.NOT : null)
                .append(GrammarEnum.NULL);
        return b.build();
    }

}
