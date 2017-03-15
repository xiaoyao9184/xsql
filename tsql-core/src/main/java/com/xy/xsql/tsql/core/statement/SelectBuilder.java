package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.BaseBuilder;
import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.FromBuilder;
import com.xy.xsql.tsql.core.clause.TopBuilder;
import com.xy.xsql.tsql.core.clause.WhereBuilder;
import com.xy.xsql.tsql.core.clause.WithBuilder;
import com.xy.xsql.tsql.core.clause.select.*;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilder implements BaseBuilder<Void,Select> {


    private Select select;


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



    public static class QuerySpecificationBuilder<ParentBuilder>
            extends CodeTreeBuilder<QuerySpecificationBuilder<ParentBuilder>,ParentBuilder,Select.QuerySpecification> {

        public QuerySpecificationBuilder(Select.QuerySpecification tar) {
            super(tar);
        }


        /**
         *
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withAll() {
            tar.setUseAll(true);
            return this;
        }

        /**
         *
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withDistinct() {
            tar.setUseDistinct(true);
            return this;
        }

        /**
         *
         * @return
         */
        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> withTop(){
            Top top = new Top();
            tar.setTop(top);
            return new TopBuilder<QuerySpecificationBuilder<ParentBuilder>>(top)
                    .in(this);
        }

        /**
         *
         * @return
         */
        public SelectListBuilder<QuerySpecificationBuilder<ParentBuilder>> withSelectList(){
            SelectList selectList = new SelectList();
            tar.setSelectList(selectList);
            return new SelectListBuilder<QuerySpecificationBuilder<ParentBuilder>>(selectList)
                    .in(this);
        }

        /**
         *
         * @param newTable
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withInto(String newTable) {
            tar.setNewTable(new Unknown(newTable));
            return this;
        }

        /**
         *
         * @return
         */
        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> withFrom() {
            From from = new From();
            tar.setFrom(from);
            return new FromBuilder<QuerySpecificationBuilder<ParentBuilder>>(from)
                    .in(this);
        }

        /**
         *
         * @return
         */
        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> withWhere() {
            Where where = new Where();
            tar.setWhere(where);
            WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> whereBuilder = new WhereBuilder<>(where);
            return whereBuilder.in(this);
        }

        /**
         *
         * @return
         */
        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> withGroupBy() {
            GroupBy groupBy = new GroupBy();
            tar.setGroupBy(groupBy);
            GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> groupByBuilder = new GroupByBuilder<>(groupBy);
            return groupByBuilder.in(this);
        }

        /**
         *
         * @return
         */
        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> withHaving() {
            Having having = new Having();
            tar.setHaving(having);
            HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> havingBuilder = new HavingBuilder<>(having);
            return havingBuilder.in(this);
        }
    }

}
