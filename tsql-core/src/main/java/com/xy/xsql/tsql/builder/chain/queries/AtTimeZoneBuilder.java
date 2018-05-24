package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.AtTimeZone;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * AtTimeZoneBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AtTimeZoneBuilder<ParentBuilder>
        extends CodeTreeBuilder<AtTimeZoneBuilder<ParentBuilder>,ParentBuilder,AtTimeZone> {

    private boolean indexFirst = true;

    public AtTimeZoneBuilder() {
        super(new AtTimeZone());
    }

    public AtTimeZoneBuilder(AtTimeZone tar) {
        super(tar);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public AtTimeZoneBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setInputExpression(expression);
        return this;
    }

    /**
     * set
     * @param timezone timezone
     * @return THIS
     */
    public AtTimeZoneBuilder<ParentBuilder> withTimezone(String timezone) {
        target.setTimezone(c_string(timezone));
        return this;
    }

}
