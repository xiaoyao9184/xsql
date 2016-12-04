package com.xy.xsql.orm.data.sql.sentence.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class TopItem implements Expression {

    private Expression countExpression;
    private boolean usePercent;

    public TopItem withCountExpression(Expression countExpression){
        this.countExpression = countExpression;
        return this;
    }

    public TopItem withPercent(boolean usePercent){
        this.usePercent = usePercent;
        return this;
    }

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(GrammarEnum.TOP)
                .append(countExpression)
                .append(usePercent ? GrammarEnum.PERCENT : null)
                .build();
    }
}
