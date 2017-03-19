package com.xy.xsql.tsql.core.clause.subquery;

import com.xy.xsql.core.builder.SubBuilder;
import com.xy.xsql.tsql.core.clause.select.*;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class SelectSubQueryBuilder<Done>
        extends SubBuilder<SelectSubQueryBuilder<Done>,Void,Done> {

    private Select select;


    public SelectSubQueryBuilder(){
        this.select = new Select();
    }




    /**
     *
     * @return
     */
    public OrderByBuilder<SelectSubQueryBuilder<Done>> withOrderBy() {
        OrderBy orderBy = new OrderBy();
        select.setOrderBy(orderBy);
        OrderByBuilder<SelectSubQueryBuilder<Done>> groupByBuilder = new OrderByBuilder<>(orderBy);
        return groupByBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public ForBuilder<SelectSubQueryBuilder<Done>> withFor() {
        For forClause = new For();
        select.setForClause(forClause);
        ForBuilder<SelectSubQueryBuilder<Done>> forBuilder = new ForBuilder<>(forClause);
        return forBuilder.in(this);
    }


}
