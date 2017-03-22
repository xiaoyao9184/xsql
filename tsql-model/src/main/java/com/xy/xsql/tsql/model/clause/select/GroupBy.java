package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.ORDER)
                .append(Keywords.BY);

        //[ ALL ]
//        b.append(useAll ? Keywords.ALL : null);

        /*
        {
              column-expression
            | ROLLUP ( <group_by_expression> [ ,...n ] )
            | CUBE ( <cgroup_by_expression> [ ,...n ] )
            | GROUPING SETS ( <grouping set> [ ,...n ]  )
            | () --calculates the grand total
        } [ ,...n ]
         */
        b.append(items, Other.DELIMITER);

        //[ WITH { CUBE | ROLLUP } ]
//        if(!useAll){
//            if(useWithCube){
//                b.append(Keywords.WITH)
//                        .append(Keywords.CUBE);
//            }else if(useWithRollup){
//                b.append(Keywords.WITH)
//                        .append(Keywords.ROLLUP);
//            }
//        }

        return b.build();
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
    public interface Item extends Block {

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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Keywords.Key.ROLLUP)
                    .append(Other.GROUP_START)
                    .append(groupByExpressionList)
                    .append(Other.GROUP_END)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Keywords.Key.CUBE)
                    .append(Other.GROUP_START)
                    .append(groupByExpressionList)
                    .append(Other.GROUP_END)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Keywords.Key.GROUPING)
                    .append(Keywords.Key.SETS)
                    .append(groupingSetItemList)
                    .build();
        }
    }

    /**
     * () --calculates the grand total
     */
    public static class AllItem implements Item {
        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Other.GROUP_START)
                    .append(Other.GROUP_END)
                    .build();
        }
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


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();
            if(CheckUtil.isNullOrEmpty(columnExpressionList)){
                b.append(columnExpressionList.get(0));
            }else {
                b.append(Other.GROUP_START)
                        .append(columnExpressionList)
                        .append(Other.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     * <grouping_set>
     */
    public static class GroupingSet implements Block {
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
        public interface Item extends Block {

        }
    }



}
