package com.xy.xsql.tsql.model.clause.select;

import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

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
    //[ <ROW or RANGE clause> ]
    private RowRange rowRange;


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

    public RowRange getRowRange() {
        return rowRange;
    }

    public void setRowRange(RowRange rowRange) {
        this.rowRange = rowRange;
    }


    /**
     * <PARTITION BY clause>
     */
    public static class PartitionBy {
        //PARTITION BY value_expression , ... [ n ]
        private List<Expression> valueExpressionList;

        public List<Expression> getValueExpressionList() {
            return valueExpressionList;
        }

        public void setValueExpressionList(List<Expression> valueExpressionList) {
            this.valueExpressionList = valueExpressionList;
        }

    }

    /**
     * <ORDER BY clause>
     * TODO maybe use select.OrderBy replace
     */
    public static class OrderBy {
        /*
        ORDER BY order_by_expression
            [ COLLATE collation_name ]
            [ ASC | DESC ]
            [ ,...n ]
        */
        private List<com.xy.xsql.tsql.model.clause.select.OrderBy.Item> items;

        public List<com.xy.xsql.tsql.model.clause.select.OrderBy.Item> getItems() {
            return items;
        }

        public void setItems(List<com.xy.xsql.tsql.model.clause.select.OrderBy.Item> items) {
            this.items = items;
        }

    }

    public static class RowRange {
        private boolean useRows;
        private WindowFrameExtent windowFrameExtent;

        public boolean isUseRows() {
            return useRows;
        }

        public void setUseRows(boolean useRows) {
            this.useRows = useRows;
        }

        public WindowFrameExtent getWindowFrameExtent() {
            return windowFrameExtent;
        }

        public void setWindowFrameExtent(WindowFrameExtent windowFrameExtent) {
            this.windowFrameExtent = windowFrameExtent;
        }
    }

    public interface WindowFrameExtent {

    }

    public static class WindowFrameBetween implements WindowFrameExtent {
        private WindowFrameBound betweenBound;
        private WindowFrameBound andBound;

        public WindowFrameBound getBetweenBound() {
            return betweenBound;
        }

        public void setBetweenBound(WindowFrameBound betweenBound) {
            this.betweenBound = betweenBound;
        }

        public WindowFrameBound getAndBound() {
            return andBound;
        }

        public void setAndBound(WindowFrameBound andBound) {
            this.andBound = andBound;
        }
    }

    public interface WindowFrameBound {

    }

    public static class WindowFramePreceding implements WindowFrameExtent, WindowFrameBound {
        private boolean useUnbounded;
        private UnsignedValueSpecification unsignedvaluespecification;
        private boolean useCurrent;

        public boolean isUseUnbounded() {
            return useUnbounded;
        }

        public void setUseUnbounded(boolean useUnbounded) {
            this.useUnbounded = useUnbounded;
        }

        public UnsignedValueSpecification getUnsignedvaluespecification() {
            return unsignedvaluespecification;
        }

        public void setUnsignedvaluespecification(UnsignedValueSpecification unsignedvaluespecification) {
            this.unsignedvaluespecification = unsignedvaluespecification;
        }

        public boolean isUseCurrent() {
            return useCurrent;
        }

        public void setUseCurrent(boolean useCurrent) {
            this.useCurrent = useCurrent;
        }
    }

    public static class WindowFrameFollowing implements WindowFrameBound {
        private boolean useUnbounded;
        private UnsignedValueSpecification unsignedvaluespecification;
        private boolean useCurrent;

        public boolean isUseUnbounded() {
            return useUnbounded;
        }

        public void setUseUnbounded(boolean useUnbounded) {
            this.useUnbounded = useUnbounded;
        }

        public UnsignedValueSpecification getUnsignedvaluespecification() {
            return unsignedvaluespecification;
        }

        public void setUnsignedvaluespecification(UnsignedValueSpecification unsignedvaluespecification) {
            this.unsignedvaluespecification = unsignedvaluespecification;
        }

        public boolean isUseCurrent() {
            return useCurrent;
        }

        public void setUseCurrent(boolean useCurrent) {
            this.useCurrent = useCurrent;
        }
    }

    public static class UnsignedValueSpecification extends NumberConstant {

        public UnsignedValueSpecification(Number number) {
            super(number);
            withUnsigned().withInteger();
        }

        public NumberConstant toNumberConstant(){
            return this;
        }

    }
}
