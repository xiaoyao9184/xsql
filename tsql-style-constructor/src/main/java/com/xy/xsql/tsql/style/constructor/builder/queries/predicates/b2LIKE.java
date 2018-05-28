package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Like;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2LIKE
        extends SimpleBuilder<Like> {

    b2LIKE LIKE(
            Expression string_expression,
            ESCAPE escape);

    b2LIKE LIKE(
            Expression string_expression);

    b2LIKE NOT_LIKE(
            Expression string_expression,
            ESCAPE escape);

    b2LIKE NOT_LIKE(
            Expression string_expression);


    public static class ESCAPE extends CodeBuilder<StringConstant> {

        public ESCAPE(StringConstant stringConstant) {
            super(stringConstant);
        }
    }

}
