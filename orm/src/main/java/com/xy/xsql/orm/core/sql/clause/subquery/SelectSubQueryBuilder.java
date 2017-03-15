package com.xy.xsql.orm.core.sql.clause.subquery;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.core.sql.clause.FromBuilder;
import com.xy.xsql.orm.core.sql.clause.TopBuilder;
import com.xy.xsql.orm.core.sql.clause.WhereBuilder;
import com.xy.xsql.orm.core.sql.clause.select.*;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.Where;
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
    public SelectSubQueryBuilder<Done> withAll() {
        this.select.setUseAll(true);
        return this;
    }

    /**
     *
     * @return
     */
    public SelectSubQueryBuilder<Done> withDistinct() {
        this.select.setUseDistinct(true);
        return this;
    }

    /**
     *
     * @return
     */
    public TopBuilder<SelectSubQueryBuilder<Done>> withTop(){
        Top top = new Top();
        select.setTop(top);
        return new TopBuilder<SelectSubQueryBuilder<Done>>(top)
                .in(this);
    }

    /**
     *
     * @return
     */
    public SelectListBuilder<SelectSubQueryBuilder<Done>> withSelectList(){
        SelectList selectList = new SelectList();
        select.setSelectList(selectList);
        return new SelectListBuilder<SelectSubQueryBuilder<Done>>(selectList)
                .in(this);
    }

    /**
     *
     * @param newTable
     * @return
     */
    public SelectSubQueryBuilder<Done> withInto(String newTable) {
        this.select.setNewTable(newTable);
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder<SelectSubQueryBuilder<Done>> withFrom() {
        From from = new From();
        select.setFrom(from);
        return new FromBuilder<SelectSubQueryBuilder<Done>>(from)
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<SelectSubQueryBuilder<Done>> withWhere() {
        Where where = new Where();
        select.setWhere(where);
        WhereBuilder<SelectSubQueryBuilder<Done>> whereBuilder = new WhereBuilder<>(where);
        return whereBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public GroupByBuilder<SelectSubQueryBuilder<Done>> withGroupBy() {
        GroupBy groupBy = new GroupBy();
        select.setGroupBy(groupBy);
        GroupByBuilder<SelectSubQueryBuilder<Done>> groupByBuilder = new GroupByBuilder<>(groupBy);
        return groupByBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public HavingBuilder<SelectSubQueryBuilder<Done>> withHaving() {
        Having having = new Having();
        select.setHaving(having);
        HavingBuilder<SelectSubQueryBuilder<Done>> havingBuilder = new HavingBuilder<>(having);
        return havingBuilder.in(this);
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
