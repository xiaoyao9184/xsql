package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

import java.util.List;

/**
 *

 <group_by_expression> ::=
 column-expression
 | ( column-expression [ ,...n ] )

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
@Deprecated
public class GroupByExpression implements Expression {

    private List<Expression> items;


    public List<Expression> getItems() {
        return items;
    }

    public void setItems(List<Expression> items) {
        this.items = items;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        if(items.size() == 0){
            b.append(items.get(0));
        }else {
            b.append(OtherEnum.GROUP_START)
                    .append(items, OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END);
        }
        return b.build();
    }
}
