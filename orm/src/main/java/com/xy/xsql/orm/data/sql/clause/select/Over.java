package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.TableName;

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
public class Over implements ElementList {

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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.OVER)
                .append(OtherEnum.GROUP_START);

        b.append(partitionBy != null ? partitionBy : null);

        b.append(orderBy != null ? orderBy : null);

        return b
                .append(OtherEnum.GROUP_END)
                .build();
    }

    /**
     * <PARTITION BY clause>
     */
    public static class PartitionBy implements ElementList {
        //PARTITION BY value_expression , ... [ n ]
        private List<Expression> valueExpressionList;

        public List<Expression> getValueExpressionList() {
            return valueExpressionList;
        }

        public void setValueExpressionList(List<Expression> valueExpressionList) {
            this.valueExpressionList = valueExpressionList;
        }


        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(GrammarEnum.PARTITION)
                    .append(GrammarEnum.BY)
                    .append(valueExpressionList,OtherEnum.DELIMITER)
                    .build();
        }
    }

    /**
     * <ORDER BY clause>
     */
    public static class OrderBy implements ElementList {
        /*
        ORDER BY order_by_expression
            [ COLLATE collation_name ]
            [ ASC | DESC ]
            [ ,...n ]
        */
        private List<com.xy.xsql.orm.data.sql.clause.select.OrderBy.OrderByItem> items;

        public List<com.xy.xsql.orm.data.sql.clause.select.OrderBy.OrderByItem> getItems() {
            return items;
        }

        public void setItems(List<com.xy.xsql.orm.data.sql.clause.select.OrderBy.OrderByItem> items) {
            this.items = items;
        }


        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(GrammarEnum.ORDER)
                    .append(GrammarEnum.BY)
                    .append(items, OtherEnum.DELIMITER)
                    .build();
        }
    }

}
