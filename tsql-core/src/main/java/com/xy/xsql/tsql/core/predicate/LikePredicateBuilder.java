package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.predicate.Like;
import com.xy.xsql.tsql.model.predicate.Predicate;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 * string_expression [ NOT ] LIKE string_expression [ ESCAPE 'escape_character' ]
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

    public LikePredicateBuilder<ParentBuilder> withNot(boolean useNot) {
        tar.setUseNotOperator(useNot);
        return this;
    }

    public LikePredicateBuilder<ParentBuilder> withEscape(StringConstant escape) {
        tar.setEscapeCharacter(escape);
        return this;
    }


    public static Predicate LIKE(Expression left, Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .build();
    }

    public static Predicate NOT_LIKE(Expression left,Expression right){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot(true)
                .withStringExpression(right)
                .build();
    }


    public static Predicate LIKE(Expression left,Expression right,StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

    public static Predicate NOT_LIKE(Expression left,Expression right,StringConstant escape){
        return new LikePredicateBuilder<Void>()
                .withStringExpression(left)
                .withNot(true)
                .withStringExpression(right)
                .withEscape(escape)
                .build();
    }

}
