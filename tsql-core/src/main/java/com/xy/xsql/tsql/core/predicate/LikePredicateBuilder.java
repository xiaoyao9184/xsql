package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.Like;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * LikePredicateBuilder
 *
 * @see Like
 * @param <ParentBuilder>
 */
@SuppressWarnings("Duplicates")
public class LikePredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<LikePredicateBuilder<ParentBuilder>,ParentBuilder,Like> {

    public LikePredicateBuilder() {
        super(new Like());
    }

    public LikePredicateBuilder(Like predicate) {
        super(predicate);
    }

    private int index = 0;

    public LikePredicateBuilder<ParentBuilder> withStringExpression(Expression expression) {
        if(index == 0){
            tar.setExpression(expression);
            index = 1;
        } else {
            tar.setLikeExpression(expression);
            index = 0;
        }
        return this;
    }

    public LikePredicateBuilder<ParentBuilder> withNot() {
        tar.setUseNotOperator(true);
        return this;
    }

    public LikePredicateBuilder<ParentBuilder> withEscape(StringConstant escape) {
        tar.setEscapeCharacter(escape);
        return this;
    }

}
