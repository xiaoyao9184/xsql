package com.xy.xsql.orm.core.statements;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.statements.clause.FromBuilder;
import com.xy.xsql.orm.core.statements.clause.TopBuilder;
import com.xy.xsql.orm.core.statements.clause.WhereBuilder;
import com.xy.xsql.orm.core.statements.clause.select.*;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.clause.select.*;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilder implements BaseBuilder<Void,Select> {


    private Select select;
    private TopBuilder<SelectBuilder> topBuilder;
    private SelectListBuilder<SelectBuilder> selectListBuilder;
    private FromBuilder<SelectBuilder> fromBuilder;



    public SelectBuilder(){
        this.select = new Select();
    }


    @Override
    public Select build(Void aVoid) {
        return select;
    }


    /**
     *
     * @return
     */
    public SelectBuilder withAll() {
        this.select.setUseAll(true);
        return this;
    }

    /**
     *
     * @return
     */
    public SelectBuilder withDistinct() {
        this.select.setUseDistinct(true);
        return this;
    }

    /**
     *
     * @return
     */
    public TopBuilder<SelectBuilder> withTop(){
        Top top = new Top();
        select.setTop(top);
        topBuilder = new TopBuilder<>(top);
        return topBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public SelectListBuilder<SelectBuilder> withSelectList(){
        SelectList selectList = new SelectList();
        select.setSelectList(selectList);
        selectListBuilder = new SelectListBuilder<>(selectList);
        return selectListBuilder.in(this);
    }

    /**
     *
     * @param newTable
     * @return
     */
    public SelectBuilder withInto(String newTable) {
        this.select.setNewTable(newTable);
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder<SelectBuilder> withFrom() {
        From from = new From();
        select.setFrom(from);
        fromBuilder = new FromBuilder<>(from);
        return fromBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public WhereBuilder<SelectBuilder> withWhere() {
        Where where = new Where();
        select.setWhere(where);
        WhereBuilder<SelectBuilder> whereBuilder = new WhereBuilder<>(where);
        return whereBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public GroupByBuilder<SelectBuilder> withGroupBy() {
        GroupBy groupBy = new GroupBy();
        select.setGroupBy(groupBy);
        GroupByBuilder<SelectBuilder> groupByBuilder = new GroupByBuilder<>(groupBy);
        return groupByBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public HavingBuilder<SelectBuilder> withHaving() {
        Having having = new Having();
        select.setHaving(having);
        HavingBuilder<SelectBuilder> havingBuilder = new HavingBuilder<>(having);
        return havingBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public OrderByBuilder<SelectBuilder> withOrderBy() {
        OrderBy orderBy = new OrderBy();
        select.setOrderBy(orderBy);
        OrderByBuilder<SelectBuilder> groupByBuilder = new OrderByBuilder<>(orderBy);
        return groupByBuilder.in(this);
    }

    /**
     *
     * @return
     */
    public ForBuilder<SelectBuilder> withFor() {
        For forClause = new For();
        select.setForClause(forClause);
        ForBuilder<SelectBuilder> forBuilder = new ForBuilder<>(forClause);
        return forBuilder.in(this);
    }


}
