package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Like extends BaseBinary<OperatorEnum> {

    private boolean not = false;
    private String escape = null;
    private boolean caseInsensitive = false;

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(not ? OperatorEnum.NOT : null)
                .append(super.leftExpression)
                .append(caseInsensitive ? OperatorEnum.ILIKE : OperatorEnum.LIKE)
                .append(super.rightExpression)
                .append(escape != null ? GrammarEnum.ESCAPE : null)
                .append(escape != null ? escape : null)
                .build();
    }
}
