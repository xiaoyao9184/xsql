package com.xy.xsql.tsql.model.clause.select;

import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 *

 -- Syntax for SQL Server and Azure SQL Database
 -- ISO-Compliant Syntax

 GROUP BY {
 column-expression
 | ROLLUP ( <group_by_expression> [ ,...n ] )
 | CUBE ( <cgroup_by_expression> [ ,...n ] )
 | GROUPING SETS ( <grouping set> [ ,...n ]  )
 | () --calculates the grand total
 } [ ,...n ]

 <group_by_expression> ::=
 column-expression
 | ( column-expression [ ,...n ] )

 <grouping_set> ::=
 () --calculates the grand total
 | <grouping_set_item>
 | ( <grouping_set_item> [ ,...n ] )

 <grouping_set_item> ::=
 <group_by_expression>
 | ROLLUP ( <group_by_expression> [ ,...n ] )
 | CUBE ( <group_by_expression> [ ,...n ] )


 -- For backward compatibility only.
 -- Non-ISO-Compliant Syntax for SQL Server and Azure SQL Database

 GROUP BY
 [ ALL ] column-expression [ ,...n ]
 | column-expression [ ,...n ] [ WITH { CUBE | ROLLUP } ]

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 GROUP BY {
 column-name [ WITH (DISTRIBUTED_AGG) ]
 | column-expression
 } [ ,...n ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class GroupBy implements Clause {

    //[ ALL ]
    @Deprecated
    private boolean useAll;

    /*
    {
          column-expression
        | ROLLUP ( <group_by_expression> [ ,...n ] )
        | CUBE ( <cgroup_by_expression> [ ,...n ] )
        | GROUPING SETS ( <grouping set> [ ,...n ]  )
        | () --calculates the grand total
    }
     */
    private List<Item> items;

    //[ WITH { CUBE | ROLLUP } ]
    @Deprecated
    private boolean useWithCube;
    @Deprecated
    private boolean useWithRollup;

    public boolean isUseAll() {
        return useAll;
    }

    public void setUseAll(boolean useAll) {
        this.useAll = useAll;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isUseWithCube() {
        return useWithCube;
    }

    public void setUseWithCube(boolean useWithCube) {
        this.useWithCube = useWithCube;
    }

    public boolean isUseWithRollup() {
        return useWithRollup;
    }

    public void setUseWithRollup(boolean useWithRollup) {
        this.useWithRollup = useWithRollup;
    }


    /**
     *

     {
     column-expression
     | ROLLUP ( <group_by_expression> [ ,...n ] )
     | CUBE ( <cgroup_by_expression> [ ,...n ] )
     | GROUPING SETS ( <grouping set> [ ,...n ]  )
     | () --calculates the grand total
     }

     *
     */
    public interface Item {

    }

    /**
     * column-expression
     */
    public static class BaseItem implements Item {
        private Expression expression;

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }
    }

    /**
     * ROLLUP ( <group_by_expression> [ ,...n ] )
     */
    public static class RollupItem implements Item, GroupingSet.Item {

        private List<GroupByExpression> groupByExpressionList;

        public List<GroupByExpression> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }

    }

    /**
     * CUBE ( <cgroup_by_expression> [ ,...n ] )
     */
    public static class CubeItem implements Item, GroupingSet.Item {

        private List<GroupByExpression> groupByExpressionList;

        public List<GroupByExpression> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }

    }

    /**
     * GROUPING SETS ( <grouping set> [ ,...n ]  )
     */
    public static class GroupingSetsItem implements Item {

        private List<GroupingSet> groupingSetItemList;

        public List<GroupingSet> getGroupingSetItemList() {
            return groupingSetItemList;
        }

        public void setGroupingSetItemList(List<GroupingSet> groupingSetItemList) {
            this.groupingSetItemList = groupingSetItemList;
        }

    }

    /**
     * () --calculates the grand total
     */
    public static class TotalItem implements Item {

    }


    /**
     * <group_by_expression>
     */
    public static class GroupByExpression implements GroupingSet.Item {

        private List<Expression> columnExpressionList;

        public List<Expression> getColumnExpressionList() {
            return columnExpressionList;
        }

        public void setColumnExpressionList(List<Expression> columnExpressionList) {
            this.columnExpressionList = columnExpressionList;
        }

    }

    /**
     * <grouping_set>
     */
    public static class GroupingSet {
        private boolean useTotal;
        private List<Item> groupByExpressionList;

        public boolean isUseTotal() {
            return useTotal;
        }

        public void setUseTotal(boolean useTotal) {
            this.useTotal = useTotal;
        }

        public List<Item> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<Item> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }


        /**
         * <grouping_set_item>
         */
        public interface Item {

        }
    }



}
