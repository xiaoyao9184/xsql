package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
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
     * @param percentTies
     * @return
     */
    public TopBuilder<ParentBuilder> $(Expression countExpression, boolean... percentTies){
        tar.setExpression(countExpression);
        if(percentTies.length >= 1){
            tar.setUsePercent(percentTies[0]);
        }
        if(percentTies.length == 2){
            tar.setUseTies(percentTies[1]);
        }
        return this;
    }

    /**
     * Quick set out
     * @param countExpression
     * @param percentTies
     * @return
     */
    public ParentBuilder $_(Expression countExpression, boolean... percentTies){
        return $(countExpression,percentTies)
                .and();
    }
}
