package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.AtTimeZone;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * TODO move
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class AtTimeZoneBuilder<ParentBuilder>
        extends CodeTreeBuilder<AtTimeZoneBuilder<ParentBuilder>,ParentBuilder,AtTimeZone> {

    private boolean indexFirst = true;

    public AtTimeZoneBuilder() {
        super(new AtTimeZone());
    }

    public AtTimeZoneBuilder(AtTimeZone tar) {
        super(tar);
    }

    public AtTimeZoneBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setInputExpression(expression);
        return this;
    }

    public AtTimeZoneBuilder<ParentBuilder> withTimezone(String timezone) {
        target.setTimezone(new StringConstant(timezone).withQuote());
        return this;
    }


}
