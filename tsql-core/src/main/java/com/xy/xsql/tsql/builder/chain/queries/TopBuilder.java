package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.Top;

/**
 * TopBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings("WeakerAccess")
public class TopBuilder<ParentBuilder>
        extends CodeTreeBuilder<TopBuilder<ParentBuilder>,ParentBuilder,Top> {

    public TopBuilder() {
        super(new Top());
    }

    public TopBuilder(Top top) {
        super(top);
    }


    /**
     * set
     * @param countExpression Count Expression
     * @return THIS
     */
    public TopBuilder<ParentBuilder> withExpression(Expression countExpression){
        target.setExpression(countExpression);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public TopBuilder<ParentBuilder> withPercent(){
        target.setUsePercent(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public TopBuilder<ParentBuilder> withTies(){
        target.setUseTies(true);
        return this;
    }




    /*
    Quick set
     */

    /**
     * Quick set countExpression,percent,ties
     * @param countExpression Count Expression
     * @param percent_ties percent and ties
     * @return THIS
     */
    public TopBuilder<ParentBuilder> $(Expression countExpression, boolean... percent_ties){
        target.setExpression(countExpression);
        if(percent_ties.length >= 1){
            target.setUsePercent(percent_ties[0]);
        }
        if(percent_ties.length == 2){
            target.setUseTies(percent_ties[1]);
        }
        return this;
    }




    /*
    Quick build
     */

    /**
     * Quick build
     * And get-out
     * @param countExpression Count Expression
     * @param percent_ties percent and ties
     * @return PARENT
     */
    public ParentBuilder $_(Expression countExpression, boolean... percent_ties){
        return $(countExpression,percent_ties)
                .and();
    }
}
