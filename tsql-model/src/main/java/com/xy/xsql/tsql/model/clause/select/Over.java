package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 *

 -- Syntax for SQL Server, Azure SQL Database, and Azure SQL Data Warehouse

 OVER (
 [ <PARTITION BY clause> ]
 [ <ORDER BY clause> ]
 [ <ROW or RANGE clause> ]
 )

 <PARTITION BY clause> ::=
 PARTITION BY value_expression , ... [ n ]

 <ORDER BY clause> ::=
 ORDER BY order_by_expression
 [ COLLATE collation_name ]
 [ ASC | DESC ]
 [ ,...n ]

 <ROW or RANGE clause> ::=
 { ROWS | RANGE } <window frame extent>

 <window frame extent> ::=
 {   <window frame preceding>
 | <window frame between>
 }
 <window frame between> ::=
 BETWEEN <window frame bound> AND <window frame bound>

 <window frame bound> ::=
 {   <window frame preceding>
 | <window frame following>
 }

 <window frame preceding> ::=
 {
 UNBOUNDED PRECEDING
 | <unsigned_value_specification> PRECEDING
 | CURRENT ROW
 }

 <window frame following> ::=
 {
 UNBOUNDED FOLLOWING
 | <unsigned_value_specification> FOLLOWING
 | CURRENT ROW
 }

 <unsigned value specification> ::=
 {  <unsigned integer literal> }

 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Over implements Clause {

    //[ <PARTITION BY clause> ]
    private PartitionBy partitionBy;
    //[ <ORDER BY clause> ]
    private OrderBy orderBy;
    //TODO [ <ROW or RANGE clause> ]


    public PartitionBy getPartitionBy() {
        return partitionBy;
    }

    public void setPartitionBy(PartitionBy partitionBy) {
        this.partitionBy = partitionBy;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.OVER)
                .append(Other.GROUP_START);

        b.append(partitionBy != null ? partitionBy : null);

        b.append(orderBy != null ? orderBy : null);

        return b
                .append(Other.GROUP_END)
                .build();
    }

    /**
     * <PARTITION BY clause>
     */
    public static class PartitionBy implements Block {
        //PARTITION BY value_expression , ... [ n ]
        private List<Expression> valueExpressionList;

        public List<Expression> getValueExpressionList() {
            return valueExpressionList;
        }

        public void setValueExpressionList(List<Expression> valueExpressionList) {
            this.valueExpressionList = valueExpressionList;
        }


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .withDelimiter(Other.SPACE)
                    .append(Keywords.Key.PARTITION)
                    .append(Keywords.BY)
                    .append(valueExpressionList,Other.DELIMITER)
                    .build();
        }
    }

    /**
     * <ORDER BY clause>
     */
    public static class OrderBy implements Block {
        /*
        ORDER BY order_by_expression
            [ COLLATE collation_name ]
            [ ASC | DESC ]
            [ ,...n ]
        */
        private List<com.xy.xsql.tsql.model.clause.select.OrderBy.OrderByItem> items;

        public List<com.xy.xsql.tsql.model.clause.select.OrderBy.OrderByItem> getItems() {
            return items;
        }

        public void setItems(List<com.xy.xsql.tsql.model.clause.select.OrderBy.OrderByItem> items) {
            this.items = items;
        }


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .withDelimiter(Other.SPACE)
                    .append(Keywords.ORDER)
                    .append(Keywords.BY)
                    .append(items, Other.DELIMITER)
                    .build();
        }
    }

}
