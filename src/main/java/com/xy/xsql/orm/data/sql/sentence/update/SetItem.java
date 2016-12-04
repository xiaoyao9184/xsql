package com.xy.xsql.orm.data.sql.sentence.update;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * SET column_name = expression
 *
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class SetItem implements Expression {
    private Column column;
    private Expression setExpression;

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(GrammarEnum.SET)
                .withItem(column)
                .withItem(setExpression)
                .build(null);
    }

    public SetItem withSetExpression(Expression setExpression) {
        this.setExpression = setExpression;
        return this;
    }

    public SetItem withColumn(Column column) {
        this.column = column;
        return this;
    }

}
