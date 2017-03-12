package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import java.util.List;

/**
 * EXISTS ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class Exists implements Predicate {
    //EXISTS ( subquery )
    private Expression expression;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Select getSubquery() {
        return subquery;
    }

    public void setSubquery(Select subquery) {
        this.subquery = subquery;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(GrammarEnum.EXISTS)
                .append(OtherEnum.GROUP_START)
                .append(subquery)
                .append(OtherEnum.GROUP_END);
        return b.build();
    }
}
