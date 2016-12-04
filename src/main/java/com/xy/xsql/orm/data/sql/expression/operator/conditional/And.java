package com.xy.xsql.orm.data.sql.expression.operator.conditional;


import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

import java.util.List;

/**
 * expression AND expression
 *
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class And extends BaseBinary<OperatorEnum> {

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(super.leftExpression)
                .append(OperatorEnum.AND)
                .append(super.rightExpression)
                .build();
    }
}
