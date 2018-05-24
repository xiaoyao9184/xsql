package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Like;

/**
 * LikePredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"Duplicates", "WeakerAccess"})
public class LikePredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<LikePredicateBuilder<ParentBuilder>,ParentBuilder,Like> {

    public LikePredicateBuilder() {
        super(new Like());
    }

    public LikePredicateBuilder(Like predicate) {
        super(predicate);
    }

    private int index = 0;

    /**
     * set
     * @param expression Expression
     * @return THIS
     */
    public LikePredicateBuilder<ParentBuilder> withStringExpression(Expression expression) {
        if(index == 0){
            target.setExpression(expression);
            index = 1;
        } else {
            target.setLikeExpression(expression);
            index = 0;
        }
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public LikePredicateBuilder<ParentBuilder> withNot() {
        target.setUseNotOperator(true);
        return this;
    }

    /**
     * set
     * @param escape escape
     * @return THIS
     */
    public LikePredicateBuilder<ParentBuilder> withEscape(StringConstant escape) {
        target.setEscapeCharacter(escape);
        return this;
    }

}
