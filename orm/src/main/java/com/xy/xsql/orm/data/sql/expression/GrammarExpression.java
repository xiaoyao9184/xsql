package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class GrammarExpression implements Expression {

    private GrammarEnum grammarEnum;

    public GrammarExpression(GrammarEnum grammarEnum){
        this.grammarEnum = grammarEnum;
    }

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(this)
                .build();
    }

}
