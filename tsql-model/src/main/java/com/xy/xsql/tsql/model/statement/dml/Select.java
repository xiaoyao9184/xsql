package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.operator.Set;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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
    private QueryExpression2 queryExpression;

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

    public QueryExpression2 getQueryExpression() {
        return queryExpression;
    }

    public void setQueryExpression(QueryExpression2 queryExpression) {
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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();

        b.append(with);
        b.append(queryExpression);
        b.append(orderBy);
        b.append(forClause);
        b.append(option);

        return b.build();
    }

    /**
     *
     */
    public static class QuerySpecification implements Block {
        // [ ALL | DISTINCT ]
        private boolean useAll;
        private boolean useDistinct;

        //<TOP Clause>
        private Top top;

        //< select_list >
        private SelectList selectList;
        //[ INTO new_table ]
        private Unknown newTable;

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

        public SelectList getSelectList() {
            return selectList;
        }

        public void setSelectList(SelectList selectList) {
            this.selectList = selectList;
        }

        public Unknown getNewTable() {
            return newTable;
        }

        public void setNewTable(Unknown newTable) {
            this.newTable = newTable;
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


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(Keywords.SELECT);

            //[ ALL | DISTINCT ]
            b.append(useAll ?
                    Keywords.ALL :
                    useDistinct ?
                            Keywords.DISTINCT :
                            null);

            //[TOP ( expression ) [PERCENT] [ WITH TIES ] ]
            b.append(top);

            //< select_list >
            b.append(selectList);

            //[ INTO new_table ]
            if (newTable != null) {
                b.append(Keywords.INTO)
                        .append(newTable);
            }

            //[ FROM{ <table_source> } [ ,...n ] ]
            b.append(from);

            //[ WHERE <search_condition> ]
            b.append(where);

            //[ <GROUP BY> ]
            b.append(groupBy);

            //[ HAVING < search_condition > ]
            b.append(having);

            return b.build();
        }

    }


    @Deprecated
    public static class QueryExpression implements Block {
        private QuerySpecification querySpecification;
        private QueryExpression queryExpression;

        private List<UnionItem> unitItem;

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

        public List<UnionItem> getUnitItem() {
            return unitItem;
        }

        public void setUnitItem(List<UnionItem> unitItem) {
            this.unitItem = unitItem;
        }
    }

    @Deprecated
    private static class UnionItem {
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
    public interface QueryExpression2 extends Block {
        /**
         * must override
         * @return
         */
        List<Block> toBlockList();
    }

    /**
     *
     *

     { <query_specification> | ( <query_expression> ) }
     { UNION [ ALL ] | EXCEPT | INTERSECT }
     { <query_specification> | ( <query_expression> ) }

     *
     */
    public static class QueryGroupExpression implements QueryExpression2 {
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


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();

            //
            //noinspection Duplicates
            if(left instanceof QuerySpecification){
                //only <query_specification> don't need '(' ')'
                b.append(left);
            }else if(left instanceof QueryGroupExpression &&
                    (operatorSet == Set.UNION ||
                    operatorSet == Set.UNION_ALL)){
                //if left is QueryGroupExpression
                //and inside operatorSet is UNION [ALL]
                //and this operatorSet is UNION [ALL]
                //don't need '(' ')'
                QueryGroupExpression leftGroup = (QueryGroupExpression) left;
                if(leftGroup.getOperatorSet() == Set.UNION ||
                        leftGroup.getOperatorSet() == Set.UNION_ALL){
                    b.append(left);
                }else{
                    b.append(Other.GROUP_START)
                            .append(left)
                            .append(Other.GROUP_END);
                }
            }else{
                b.append(Other.GROUP_START)
                        .append(left)
                        .append(Other.GROUP_END);
            }

            b.append(operatorSet);

            //noinspection Duplicates
            if(right instanceof QuerySpecification){
                //only <query_specification> don't need '(' ')'
                b.append(right);
            }else if(right instanceof QueryGroupExpression &&
                    (operatorSet == Set.UNION ||
                            operatorSet == Set.UNION_ALL)){
                //if right is QueryGroupExpression
                //and inside operatorSet is UNION [ALL]
                //and this operatorSet is UNION [ALL]
                //don't need '(' ')'
                QueryGroupExpression rightGroup = (QueryGroupExpression) right;
                if(rightGroup.getOperatorSet() == Set.UNION ||
                        rightGroup.getOperatorSet() == Set.UNION_ALL){
                    b.append(right);
                }else{
                    b.append(Other.GROUP_START)
                            .append(right)
                            .append(Other.GROUP_END);
                }
            }else{
                b.append(Other.GROUP_START)
                        .append(right)
                        .append(Other.GROUP_END);
            }

            return b.build();
        }


    }

}
