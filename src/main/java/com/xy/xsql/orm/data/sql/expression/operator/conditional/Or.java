package com.xy.xsql.orm.data.sql.expression.operator.conditional;


import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.build.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.expression.BaseBinary;

import java.util.List;

/**
 * expression OR expression
 *
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Or extends BaseBinary<OperatorEnum> {

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(super.leftExpression)
                .append(OperatorEnum.OR)
                .append(super.rightExpression)
                .build();
    }
}
