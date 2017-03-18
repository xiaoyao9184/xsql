package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.Top;

/**
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
        tar.setExpression(countExpression);
        return this;
    }

    public TopBuilder<ParentBuilder> withPercent(){
        tar.setUsePercent(true);
        return this;
    }

    public TopBuilder<ParentBuilder> withTies(){
        tar.setUseTies(true);
        return this;
    }

    /**
     * Quick set
     * @param countExpression
     * @param percent_ties
     * @return
     */
    public TopBuilder<ParentBuilder> $(Expression countExpression, boolean... percent_ties){
        tar.setExpression(countExpression);
        if(percent_ties.length >= 1){
            tar.setUsePercent(percent_ties[0]);
        }
        if(percent_ties.length == 2){
            tar.setUseTies(percent_ties[1]);
        }
        return this;
    }

    /**
     * Quick set out
     * @param countExpression
     * @param percent_ties
     * @return
     */
    public ParentBuilder $_(Expression countExpression, boolean... percent_ties){
        return $(countExpression,percent_ties)
                .and();
    }
}
