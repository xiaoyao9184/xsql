package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Predicate;
import com.xy.xsql.tsql.model.statement.dml.Select;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 * expression [ NOT ] IN ( subquery | expression [ ,...n ] )
 * @param <ParentBuilder>
 */
public class InPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<InPredicateBuilder<ParentBuilder>,ParentBuilder,In> {

    public InPredicateBuilder() {
        super(new In());
    }

    public InPredicateBuilder(In predicate) {
        super(predicate);
    }

    public InPredicateBuilder<ParentBuilder> withExpression(Expression expression) {
        tar.setExpression(expression);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withNot(boolean useNot) {
        tar.setUseNotOperator(useNot);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withValueExpression(Expression... expression) {
        initAdd(Arrays.asList(expression),
                tar::getExpressionList,
                tar::setExpressionList);
        return this;
    }

    public InPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        tar.setSubquery(subquery);
        return this;
    }


    public static Predicate IN(Expression left, Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withValueExpression(in)
                .build();
    }

    public static Predicate NOT_IN(Expression left,Expression... in){
        return new InPredicateBuilder<Void>()
                .withExpression(left)
                .withNot(true)
                .withValueExpression(in)
                .build();
    }

}
