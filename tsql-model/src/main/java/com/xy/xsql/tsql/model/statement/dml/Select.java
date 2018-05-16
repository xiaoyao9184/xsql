package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Set;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms189499.aspx
 *

 -- Syntax for SQL Server and Azure SQL Database

 <SELECT statement> ::=
 [ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ]
 <query_expression>
 [ ORDER BY { order_by_expression | column_position [ ASC | DESC ] }
 [ ,...n ] ]
 [ <FOR Clause>]
 [ OPTION ( <query_hint> [ ,...n ] ) ]
 <query_expression> ::=
 { <query_specification> | ( <query_expression> ) }
 [  { UNION [ ALL ] | EXCEPT | INTERSECT }
 <query_specification> | ( <query_expression> ) [...n ] ]
 <query_specification> ::=
 SELECT [ ALL | DISTINCT ]
 [TOP ( expression ) [PERCENT] [ WITH TIES ] ]
 < select_list >
 [ INTO new_table ]
 [ FROM { <table_source> } [ ,...n ] ]
 [ WHERE <search_condition> ]
 [ <GROUP BY> ]
 [ HAVING < search_condition > ]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Select implements Statement {
    //<WITH Clause>
    private With with;

    //<query_expression>
    private QueryExpression queryExpression;

    //<ORDER BY Clause>
    private OrderBy orderBy;
    //<FOR Clause>
    private For forClause;
    //<OPTION Clause>
    private Option option;


    public With getWith() {
        return with;
    }

    public void setWith(With with) {
        this.with = with;
    }

    public QueryExpression getQueryExpression() {
        return queryExpression;
    }

    public void setQueryExpression(QueryExpression queryExpression) {
        this.queryExpression = queryExpression;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public For getForClause() {
        return forClause;
    }

    public void setForClause(For forClause) {
        this.forClause = forClause;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


    /**
     * <query_expression>
     */
    public static class QueryExpression implements Expression {
        //{ <query_specification> | ( <query_expression> ) }
        private QuerySpecification querySpecification;
        private QueryExpression queryExpression;

        //[  { UNION [ ALL ] | EXCEPT | INTERSECT }
        //<query_specification> | ( <query_expression> ) [...n ] ]
        private List<UnionItem> unionItems;

        public QuerySpecification getQuerySpecification() {
            return querySpecification;
        }

        public void setQuerySpecification(QuerySpecification querySpecification) {
            this.querySpecification = querySpecification;
        }

        public QueryExpression getQueryExpression() {
            return queryExpression;
        }

        public void setQueryExpression(QueryExpression queryExpression) {
            this.queryExpression = queryExpression;
        }

        public List<UnionItem> getUnionItems() {
            return unionItems;
        }

        public void setUnionItems(List<UnionItem> unionItems) {
            this.unionItems = unionItems;
        }

    }

    /**
     * { UNION [ ALL ] | EXCEPT | INTERSECT }
     <query_specification> | ( <query_expression> )
     */
    public static class UnionItem {
        private Set operatorSet;

        private QuerySpecification querySpecification;
        private QueryExpression queryExpression;

        public Set getOperatorSet() {
            return operatorSet;
        }

        public void setOperatorSet(Set operatorSet) {
            this.operatorSet = operatorSet;
        }

        public QuerySpecification getQuerySpecification() {
            return querySpecification;
        }

        public void setQuerySpecification(QuerySpecification querySpecification) {
            this.querySpecification = querySpecification;
        }

        public QueryExpression getQueryExpression() {
            return queryExpression;
        }

        public void setQueryExpression(QueryExpression queryExpression) {
            this.queryExpression = queryExpression;
        }

    }


    /**
     *
     */
    public static class QuerySpecification implements QueryExpression2,Statement {
        // [ ALL | DISTINCT ]
        private boolean useAll;
        private boolean useDistinct;
        //<TOP Clause>
        private Top top;
        //< select_list >
        private List<com.xy.xsql.tsql.model.clause.select.Select.SelectItem> selectList;
        //[ INTO new_table ]
        private Into into;
        //<FROM Clause>
        private From from;
        //<WHERE Clause>
        private Where where;
        //<GROUP BY Clause>
        private GroupBy groupBy;
        //<HAVING Clause>
        private Having having;

        public boolean isUseAll() {
            return useAll;
        }

        public void setUseAll(boolean useAll) {
            this.useAll = useAll;
        }

        public boolean isUseDistinct() {
            return useDistinct;
        }

        public void setUseDistinct(boolean useDistinct) {
            this.useDistinct = useDistinct;
        }

        public Top getTop() {
            return top;
        }

        public void setTop(Top top) {
            this.top = top;
        }

        public List<com.xy.xsql.tsql.model.clause.select.Select.SelectItem> getSelectList() {
            return selectList;
        }

        public void setSelectList(List<com.xy.xsql.tsql.model.clause.select.Select.SelectItem> selectList) {
            this.selectList = selectList;
        }

        public Into getInto() {
            return into;
        }

        public void setInto(Into into) {
            this.into = into;
        }

        public From getFrom() {
            return from;
        }

        public void setFrom(From from) {
            this.from = from;
        }

        public Where getWhere() {
            return where;
        }

        public void setWhere(Where where) {
            this.where = where;
        }

        public GroupBy getGroupBy() {
            return groupBy;
        }

        public void setGroupBy(GroupBy groupBy) {
            this.groupBy = groupBy;
        }

        public Having getHaving() {
            return having;
        }

        public void setHaving(Having having) {
            this.having = having;
        }

    }


    /*
    Other model
     */

    /**
     * <query_expression>
     */
    public interface QueryExpression2 extends Expression {

    }

    /**
     *
     *

     { <query_specification> | ( <query_expression> ) }
     { UNION [ ALL ] | EXCEPT | INTERSECT }
     { <query_specification> | ( <query_expression> ) }

     *
     */
    public static class QuerySpecificationGroup implements QueryExpression2 {
        private QueryExpression2 left;
        private Set operatorSet;
        private QueryExpression2 right;

        public QueryExpression2 getLeft() {
            return left;
        }

        public void setLeft(QueryExpression2 left) {
            this.left = left;
        }

        public Set getOperatorSet() {
            return operatorSet;
        }

        public void setOperatorSet(Set operatorSet) {
            this.operatorSet = operatorSet;
        }

        public QueryExpression2 getRight() {
            return right;
        }

        public void setRight(QueryExpression2 right) {
            this.right = right;
        }

    }

}
