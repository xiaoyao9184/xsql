package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.ConfigInOut;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.expression.NumberString;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class TopBuilder<Done>
        implements BaseBuilder<Void,Done>,ConfigInOut<TopBuilder,Done> {

    private Done done;
    private Top top;

    public TopBuilder(Top top) {
        this.top = top;
    }

    public TopBuilder() {
        this.top = new Top();
    }


    public TopBuilder<Done> withCountExpression(Number number){
        this.top.setCountExpression(new NumberString(number));
        return this;
    }

    public TopBuilder<Done> withCountExpression(Expression countExpression){
        this.top.setCountExpression(countExpression);
        return this;
    }

    public TopBuilder<Done> withTies(){
        this.top.setUseTies(true);
        return this;
    }

    public TopBuilder<Done> withPercent(){
        this.top.setUsePercent(true);
        return this;
    }

    public TopBuilder<Done> withParenthesis(){
        this.top.setUseParenthesis(true);
        return this;
    }



    @Override
    public Done build(Void aVoid) {
        if(this.done == null){
            return (Done) this.top;
        }
        return this.done;
    }

    @Override
    public TopBuilder<Done> in(Done done) {
        this.done = done;
        return this;
    }

    @Override
    public Done out() {
        return this.done;
    }
}
