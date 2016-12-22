package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class RowValueExpression implements Expression {

    private boolean useNull = false;
    private boolean useDefault = false;
    private Expression expression;

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        if(useNull){
            b.append(GrammarEnum.NULL);
        }else if(useDefault) {
            b.append(GrammarEnum.DEFAULT);
        }else {
            b.append(expression);
        }
        return b.build();
    }


}
