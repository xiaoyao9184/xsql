package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.util.CheckUtil;

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
public class GroupBy implements ElementList {

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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.ORDER)
                .append(GrammarEnum.BY);

        //[ ALL ]
//        b.append(useAll ? GrammarEnum.ALL : null);

        /*
        {
              column-expression
            | ROLLUP ( <group_by_expression> [ ,...n ] )
            | CUBE ( <cgroup_by_expression> [ ,...n ] )
            | GROUPING SETS ( <grouping set> [ ,...n ]  )
            | () --calculates the grand total
        } [ ,...n ]
         */
        b.append(items, OtherEnum.DELIMITER);

        //[ WITH { CUBE | ROLLUP } ]
//        if(!useAll){
//            if(useWithCube){
//                b.append(GrammarEnum.WITH)
//                        .append(GrammarEnum.CUBE);
//            }else if(useWithRollup){
//                b.append(GrammarEnum.WITH)
//                        .append(GrammarEnum.ROLLUP);
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
    public static class GroupByItem implements ElementList  {

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
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);

            switch (type){
                case Base:
                    b.append(columnExpression);
                    break;
                case Rollup:
                    b.append(GrammarEnum.ROLLUP)
                            .append(OtherEnum.GROUP_START)
                            .append(groupByExpressionList, OtherEnum.DELIMITER)
                            .append(OtherEnum.GROUP_END);
                    break;
                case Cube:
                    b.append(GrammarEnum.CUBE)
                            .append(OtherEnum.GROUP_START)
                            .append(groupByExpressionList, OtherEnum.DELIMITER)
                            .append(OtherEnum.GROUP_END);
                    break;
                case GroupingSet:
                    b.append(GrammarEnum.GROUPING)
                            .append(GrammarEnum.SETS)
                            .append(OtherEnum.GROUP_START)
                            .append(groupingSetList, OtherEnum.DELIMITER)
                            .append(OtherEnum.GROUP_END);
                    break;
            }

            return b.build();
        }
    }

    /**
     * <group_by_expression>
     */
    public static class GroupByExpression implements ElementList {

        private List<Expression> columnExpressionList;


        public List<Expression> getColumnExpressionList() {
            return columnExpressionList;
        }

        public void setColumnExpressionList(List<Expression> columnExpressionList) {
            this.columnExpressionList = columnExpressionList;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(CheckUtil.isNullOrEmpty(columnExpressionList)){
                b.append(columnExpressionList.get(0));
            }else {
                b.append(OtherEnum.GROUP_START)
                        .append(columnExpressionList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     * <grouping_set>
     */
    public static class GroupingSet implements ElementList {

        private List<GroupingSetItem> groupingSetItemList;

        public List<GroupingSetItem> getGroupingSetItemList() {
            return groupingSetItemList;
        }

        public void setGroupingSetItemList(List<GroupingSetItem> groupingSetItemList) {
            this.groupingSetItemList = groupingSetItemList;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(CheckUtil.isNullOrEmpty(groupingSetItemList)){
                b.append(groupingSetItemList.get(0));
            }else{
                b.append(OtherEnum.GROUP_START)
                        .append(groupingSetItemList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     * <grouping_set_item>
     */
    public static class GroupingSetItem implements ElementList {

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
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);
            if(CheckUtil.isNullOrEmpty(groupByExpressionList)){
                b.append(groupByExpressionList.get(0));
            }else{
                if(useRollup){
                    b.append(GrammarEnum.ROLLUP);
                } else if(useCube){
                    b.append(GrammarEnum.CUBE);
                }
                b.append(OtherEnum.GROUP_START)
                        .append(groupByExpressionList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            }
            return b.build();
        }
    }


}
