package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.expression.GroupByExpression;

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
    //
    private List<GroupByItem> items;

    public List<GroupByItem> getItems() {
        return items;
    }

    public void setItems(List<GroupByItem> items) {
        this.items = items;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        if(items.size() == 0){
            b.append(items.get(0));
        }else{
            b.append(OtherEnum.GROUP_START)
                    .append(items, OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END);
        }
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
    public static class GroupByItem implements ElementList  {

        //column-expression
        private Expression columnExpression;

        //ROLLUP ( <group_by_expression> [ ,...n ] )
        private boolean useRollup;
        private List<GroupByExpression> groupByExpressionList;

        //CUBE ( <cgroup_by_expression> [ ,...n ] )
        private boolean useCube;

        //GROUPING SETS ( <grouping set> [ ,...n ]  )
        private boolean useGroupingSets;
        private List<GroupingSet> groupingSetList;


        public Expression getColumnExpression() {
            return columnExpression;
        }

        public void setColumnExpression(Expression columnExpression) {
            this.columnExpression = columnExpression;
        }

        public boolean isUseRollup() {
            return useRollup;
        }

        public void setUseRollup(boolean useRollup) {
            this.useRollup = useRollup;
        }

        public List<GroupByExpression> getGroupByExpressionList() {
            return groupByExpressionList;
        }

        public void setGroupByExpressionList(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }

        public boolean isUseCube() {
            return useCube;
        }

        public void setUseCube(boolean useCube) {
            this.useCube = useCube;
        }

        public boolean isUseGroupingSets() {
            return useGroupingSets;
        }

        public void setUseGroupingSets(boolean useGroupingSets) {
            this.useGroupingSets = useGroupingSets;
        }

        public List<GroupingSet> getGroupingSetList() {
            return groupingSetList;
        }

        public void setGroupingSetList(List<GroupingSet> groupingSetList) {
            this.groupingSetList = groupingSetList;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder();
            if(columnExpression != null){
                b.append(columnExpression);
            } else if(useRollup){
                b.append(GrammarEnum.ROLLUP)
                        .append(OtherEnum.GROUP_START)
                        .append(groupByExpressionList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            } else if(useCube){
                b.append(GrammarEnum.CUBE)
                        .append(OtherEnum.GROUP_START)
                        .append(groupByExpressionList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            } else if(useGroupingSets){
                b.append(GrammarEnum.GROUPING)
                        .append(GrammarEnum.SETS)
                        .append(OtherEnum.GROUP_START)
                        .append(groupingSetList, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            }
            return b.build();
        }
    }


    /**
     *

     <grouping_set> ::=
     () --calculates the grand total
     | <grouping_set_item>
     | ( <grouping_set_item> [ ,...n ] )

     *
     */
    public static class GroupingSet implements ElementList {
        private List<GroupingSetItem> items;

        public List<GroupingSetItem> getItems() {
            return items;
        }

        public void setItems(List<GroupingSetItem> items) {
            this.items = items;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder();
            if(items.size() == 0){
                b.append(items.get(0));
            }else{
                b.append(OtherEnum.GROUP_START)
                        .append(items, OtherEnum.DELIMITER)
                        .append(OtherEnum.GROUP_END);
            }
            return b.build();
        }
    }

    /**
     *

     <grouping_set_item> ::=
     <group_by_expression>
     | ROLLUP ( <group_by_expression> [ ,...n ] )
     | CUBE ( <group_by_expression> [ ,...n ] )

     *
     */
    public static class GroupingSetItem implements ElementList {
        private GroupByExpression groupByExpression;


        public GroupByExpression getGroupByExpression() {
            return groupByExpression;
        }

        public void setGroupByExpression(GroupByExpression groupByExpression) {
            this.groupByExpression = groupByExpression;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder();
            b.append(groupByExpression);
            return b.build();
        }
    }


}
