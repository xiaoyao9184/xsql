package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.In;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * InPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class InPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,In> {

    public InPredicateBuilder() {
        super(new In());
    }

    public InPredicateBuilder(In predicate) {
        super(predicate);
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
        initAdd(Arrays.asList(expressions),
                target::getExpressionList,
                target::setExpressionList);
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
