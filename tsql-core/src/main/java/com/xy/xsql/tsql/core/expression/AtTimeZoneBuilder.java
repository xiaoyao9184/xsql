package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.AtTimeZone;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.NullIf;

/**
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
        tar.setInputExpression(expression);
        return this;
    }

    public AtTimeZoneBuilder<ParentBuilder> withTimezone(String timezone) {
        tar.setTimezone(new StringConstant(timezone).withQuote());
        return this;
    }


}
