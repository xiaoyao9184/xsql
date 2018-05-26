package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.util.CheckUtil;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * InPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class InPredicateBuilder<ParentBuilder>
        extends ParentHoldBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,In> {

    public InPredicateBuilder() {
        super(new In());
    }

    public InPredicateBuilder(In target) {
        super(target);
    }

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public InPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        target.setExpression(expression);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public InPredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

    /**
     * set
     * @param expressions Expression
     * @return THIS
     */
    public InPredicateBuilder<ParentBuilder> withValueExpression(Expression... expressions) {
        if(CheckUtil.isNullOrEmpty(expressions)){
            return this;
        }
        list(target::getExpressionList, target::setExpressionList)
                .addAll(expressions);
        return this;
    }

    /**
     * set
     * @param subQuery Select
     * @return THIS
     */
    public InPredicateBuilder<ParentBuilder> withSubQuery(Select subQuery) {
        target.setSubquery(subQuery);
        return this;
    }

}
