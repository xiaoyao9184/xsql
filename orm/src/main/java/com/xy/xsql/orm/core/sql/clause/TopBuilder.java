package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.core.TreeBuilder;
import com.xy.xsql.orm.data.sql.Expression;
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
        this.tar.setExpression(countExpression);
        return this;
    }

    public TopBuilder<ParentBuilder> withPercent(){
        this.tar.setUsePercent(true);
        return this;
    }

    public TopBuilder<ParentBuilder> withTies(){
        this.tar.setUseTies(true);
        return this;
    }
}
