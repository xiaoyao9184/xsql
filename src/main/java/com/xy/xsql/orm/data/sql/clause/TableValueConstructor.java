package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.expression.RowValueExpression;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/dd776382.aspx
 *
 VALUES ( <row value expression list> ) [ ,...n ]

 <row value expression list> ::=
 {<row value expression> } [ ,...n ]

 <row value expression> ::=
 { DEFAULT | NULL | expression }

 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class TableValueConstructor implements Expression {

    private List<List<RowValueExpression>> rowValueExpressionListGroup;


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.VALUES);

        for (List<RowValueExpression> rowValueExpressionList: rowValueExpressionListGroup) {
            b.append(OtherEnum.GROUP_START)
                    .appendExpression(rowValueExpressionList,OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END);
        }

        return b.build();
    }
}
