package com.xy.xsql.tsql.style.constructor.builder.elements.expressions;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.AtTimeZone;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2AT_TIME_ZONE
        extends SimpleBuilder<AtTimeZone> {

    b2AT_TIME_ZONE AT_TIME_ZONE(
            Expression timezone);

}
