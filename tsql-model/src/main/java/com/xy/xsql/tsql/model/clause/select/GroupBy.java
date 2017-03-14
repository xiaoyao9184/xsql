package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
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

    //column-expression [ ,...n ]
    private List<GroupByItem> items;

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

    public List<GroupByItem> getItems() {
        return items;
    }

    public void setItems(List<GroupByItem> items) {
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


    public enum ItemType {
        Base,
        Rollup,
        Cube,
        GroupingSet
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
    public static class GroupByItem implements Block {

        private GroupBy.ItemType type;

        //column-expression
        private Expression columnExpression;

        //ROLLUP ( <group_by_expression> [ ,...n ] )
        //CUBE ( <cgroup_by_expression> [ ,...n ] )
        private List<GroupByExpression> groupByExpressionList;

        //GROUPING SETS ( <grouping set> [ ,...n ]  )
        private List<GroupingSet> groupingSetList;


        public ItemType getType() {
            return type;
        }

        public void setType(ItemType type) {
            this.type = type;
        }

        public Expression getColumnExpression() {
            return columnExpression;
        }

        public void setColumnExpression(Expression columnExpression) {
            this.columnExpression = columnExpression;
        }

        public List<GroupByExpression> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }

        public List<GroupingSet> getGroupingSetList() {
            return groupingSetList;
        }

        public void setGroupingSetList(List<GroupingSet> groupingSetList) {
            this.groupingSetList = groupingSetList;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE);

            switch (type){
                case Base:
                    b.append(columnExpression);
                    break;
                case Rollup:
                    b.append(Keywords.Key.ROLLUP)
                            .append(Other.GROUP_START)
                            .append(groupByExpressionList, Other.DELIMITER)
                            .append(Other.GROUP_END);
                    break;
                case Cube:
                    b.append(Keywords.Key.CUBE)
                            .append(Other.GROUP_START)
                            .append(groupByExpressionList, Other.DELIMITER)
                            .append(Other.GROUP_END);
                    break;
                case GroupingSet:
                    b.append(Keywords.Key.GROUPING)
                            .append(Keywords.Key.SETS)
                            .append(Other.GROUP_START)
                            .append(groupingSetList, Other.DELIMITER)
                            .append(Other.GROUP_END);
                    break;
            }

            return b.build();
        }
    }

    /**
     * <group_by_expression>
     */
    public static class GroupByExpression implements Block {

        private List<Expression> columnExpressionList;


        public List<Expression> getColumnExpressionList() {
            return columnExpressionList;
        }

        public void setColumnExpressionList(List<Expression> columnExpressionList) {
            this.columnExpressionList = columnExpressionList;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE);
            if(CheckUtil.isNullOrEmpty(columnExpressionList)){
                b.append(columnExpressionList.get(0));
            }else {
                b.append(Other.GROUP_START)
                        .append(columnExpressionList, Other.DELIMITER)
                        .append(Other.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     * <grouping_set>
     */
    public static class GroupingSet implements Block {

        private List<GroupingSetItem> groupingSetItemList;

        public List<GroupingSetItem> getGroupingSetItemList() {
            return groupingSetItemList;
        }

        public void setGroupingSetItemList(List<GroupingSetItem> groupingSetItemList) {
            this.groupingSetItemList = groupingSetItemList;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE);
            if(CheckUtil.isNullOrEmpty(groupingSetItemList)){
                b.append(groupingSetItemList.get(0));
            }else{
                b.append(Other.GROUP_START)
                        .append(groupingSetItemList, Other.DELIMITER)
                        .append(Other.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     * <grouping_set_item>
     */
    public static class GroupingSetItem implements Block {

        private boolean useRollup;
        private boolean useCube;
        private List<GroupByExpression> groupByExpressionList;

        public List<GroupByExpression> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }

        public boolean isUseRollup() {
            return useRollup;
        }

        public void setUseRollup(boolean useRollup) {
            this.useRollup = useRollup;
        }

        public boolean isUseCube() {
            return useCube;
        }

        public void setUseCube(boolean useCube) {
            this.useCube = useCube;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE);
            if(CheckUtil.isNullOrEmpty(groupByExpressionList)){
                b.append(groupByExpressionList.get(0));
            }else{
                if(useRollup){
                    b.append(Keywords.Key.ROLLUP);
                } else if(useCube){
                    b.append(Keywords.Key.CUBE);
                }
                b.append(Other.GROUP_START)
                        .append(groupByExpressionList, Other.DELIMITER)
                        .append(Other.GROUP_END);
            }
            return b.build();
        }
    }


}
