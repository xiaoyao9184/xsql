package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.Top;

/**
 * TopBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class TopBuilder<ParentBuilder>
        extends CodeTreeBuilder<TopBuilder<ParentBuilder>,ParentBuilder,Top> {

    public TopBuilder() {
        super(new Top());
    }

    public TopBuilder(Top top) {
        super(top);
    }


    public TopBuilder<ParentBuilder> withExpression(Expression countExpression){
        target.setExpression(countExpression);
        return this;
    }

    public TopBuilder<ParentBuilder> withPercent(){
        target.setUsePercent(true);
        return this;
    }

    public TopBuilder<ParentBuilder> withTies(){
        target.setUseTies(true);
        return this;
    }




    /*
    Quick set
     */

    /**
     * Quick set countExpression,percent,ties
     * @param countExpression
     * @param percent_ties
     * @return
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
     * @param countExpression
     * @param percent_ties
     * @return
     */
    public ParentBuilder $_(Expression countExpression, boolean... percent_ties){
        return $(countExpression,percent_ties)
                .and();
    }
}
