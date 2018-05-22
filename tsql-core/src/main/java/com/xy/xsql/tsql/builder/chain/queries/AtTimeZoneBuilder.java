package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.AtTimeZone;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

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
        target.setTimezone(c_string(timezone));
        return this;
    }


}
