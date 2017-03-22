package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.core.clause.select.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.statement.dml.Insert;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilder extends CodeBuilder<com.xy.xsql.tsql.model.statement.dml.Select> {

    public SelectBuilder(){
        super(new com.xy.xsql.tsql.model.statement.dml.Select());
    }


    public WithBuilder<SelectBuilder> withWith(){
        return new WithBuilder<SelectBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    public QuerySpecificationBuilder<SelectBuilder> withQuery() {
        com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification querySpecification = new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification();
        target.setQueryExpression(querySpecification);
        return new QuerySpecificationBuilder<SelectBuilder>
                (querySpecification)
                .in(this);
    }

    public OrderByBuilder<SelectBuilder> withOrderBy() {
        return new OrderByBuilder<SelectBuilder>
                (initSet(OrderBy::new,
                        target::getOrderBy,
                        target::setOrderBy))
                .in(this);
    }

    public ForBuilder<SelectBuilder> withFor() {
        return new ForBuilder<SelectBuilder>
                (initSet(For::new,
                        target::getForClause,
                        target::setForClause))
                .in(this);
    }

    public OptionBuilder<SelectBuilder> withOption() {
        return new OptionBuilder<SelectBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }



    /**
     * QuerySpecificationBuilder
     * @param <ParentBuilder>
     */
    public static class QuerySpecificationBuilder<ParentBuilder>
            extends CodeTreeBuilder<QuerySpecificationBuilder<ParentBuilder>,ParentBuilder, com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification> {


        public QuerySpecificationBuilder() {
            super(new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification());
        }

        public QuerySpecificationBuilder(com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification tar) {
            super(tar);
        }


        /**
         *
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withAll() {
            target.setUseAll(true);
            return this;
        }

        /**
         *
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withDistinct() {
            target.setUseDistinct(true);
            return this;
        }

        /**
         *
         * @return
         */
        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> withTop(){
            Top top = new Top();
            target.setTop(top);
            return new TopBuilder<QuerySpecificationBuilder<ParentBuilder>>(top)
                    .in(this);
        }

        /**
         *
         * @return
         */
        public com.xy.xsql.tsql.core.clause.select.SelectBuilder<QuerySpecificationBuilder<ParentBuilder>> withSelectList(){
            Select selectList = new Select();
            target.setSelectList(selectList);
            return new com.xy.xsql.tsql.core.clause.select.SelectBuilder<QuerySpecificationBuilder<ParentBuilder>>(selectList)
                    .in(this);
        }

        /**
         *
         * @param newTable
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> withInto(String newTable) {
            target.setNewTable(new Unknown(newTable));
            return this;
        }

        /**
         *
         * @return
         */
        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> withFrom() {
            From from = new From();
            target.setFrom(from);
            return new FromBuilder<QuerySpecificationBuilder<ParentBuilder>>(from)
                    .in(this);
        }

        /**
         *
         * @return
         */
        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> withWhere() {
            Where where = new Where();
            target.setWhere(where);
            WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> whereBuilder = new WhereBuilder<>(where);
            return whereBuilder.in(this);
        }

        /**
         *
         * @return
         */
        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> withGroupBy() {
            GroupBy groupBy = new GroupBy();
            target.setGroupBy(groupBy);
            GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> groupByBuilder = new GroupByBuilder<>(groupBy);
            return groupByBuilder.in(this);
        }

        /**
         *
         * @return
         */
        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> withHaving() {
            Having having = new Having();
            target.setHaving(having);
            HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> havingBuilder = new HavingBuilder<>(having);
            return havingBuilder.in(this);
        }
    }

}
