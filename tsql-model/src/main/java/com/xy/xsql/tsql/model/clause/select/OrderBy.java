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

 -- Syntax for SQL Server and Azure SQL Database

 ORDER BY order_by_expression
     [ COLLATE collation_name ]
     [ ASC | DESC ]
     [ ,...n ]
 [ <offset_fetch> ]

 <offset_fetch> ::=
 {
    OFFSET { integer_constant | offset_row_count_expression } { ROW | ROWS }
    [
      FETCH { FIRST | NEXT } {integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY
    ]
 }

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 [ ORDER BY
     {
     order_by_expression
     [ ASC | DESC ]
     } [ ,...n ]
 ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class OrderBy implements Clause {

    /*
    order_by_expression
     [ COLLATE collation_name ]
     [ ASC | DESC ]
     [ ,...n ]
     */
    private List<Item> items;

    //[ <offset_fetch> ]
    private OffsetFetch offsetFetch;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OffsetFetch getOffsetFetch() {
        return offsetFetch;
    }

    public void setOffsetFetch(OffsetFetch offsetFetch) {
        this.offsetFetch = offsetFetch;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.ORDER)
                .append(Keywords.BY)
                .append(items)
                .append(offsetFetch);
        return b.build();
    }


    /**
     *

     order_by_expression
     [ COLLATE collation_name ]
     [ ASC | DESC ]

     *
     */
    public static class Item implements Block {

        //order_by_expression
        private Expression orderByExpression;
        //TODO [ COLLATE collation_name ]

        //[ ASC | DESC ]
        private boolean useAsc;
        private boolean useDesc;

        public Item(){}

        public Item(Expression orderByExpression){
            this.orderByExpression = orderByExpression;
            this.useAsc = true;
        }


        public Expression getOrderByExpression() {
            return orderByExpression;
        }

        public void setOrderByExpression(Expression orderByExpression) {
            this.orderByExpression = orderByExpression;
        }

        public boolean isUseAsc() {
            return useAsc;
        }

        public void setUseAsc(boolean useAsc) {
            this.useAsc = useAsc;
        }

        public boolean isUseDesc() {
            return useDesc;
        }

        public void setUseDesc(boolean useDesc) {
            this.useDesc = useDesc;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(orderByExpression);
            if(useAsc){
                b.append(Keywords.ASC);
            } else if(useDesc){
                b.append(Keywords.DESC);
            }
            return b.build();
        }
    }

    /**
     *

     <offset_fetch> ::=
     {
         OFFSET { integer_constant | offset_row_count_expression } { ROW | ROWS }
         [
           FETCH { FIRST | NEXT } {integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY
         ]
     }

     *
     */
    public static class OffsetFetch implements Block {

        //{ integer_constant | offset_row_count_expression }
        private Integer integerConstant;
        private Expression offsetRowCountExpression;

        //{ ROW | ROWS }
        private boolean useRow;

        //[
        //    FETCH { FIRST | NEXT } {integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY
        //]
        private boolean useFetch;
        private boolean useFetchFirst;
        private Integer fetchIntegerConstant;
        private Expression fetchOffsetRowCountExpression;
        private boolean useFetchRow;


        public Integer getIntegerConstant() {
            return integerConstant;
        }

        public void setIntegerConstant(Integer integerConstant) {
            this.integerConstant = integerConstant;
        }

        public Expression getOffsetRowCountExpression() {
            return offsetRowCountExpression;
        }

        public void setOffsetRowCountExpression(Expression offsetRowCountExpression) {
            this.offsetRowCountExpression = offsetRowCountExpression;
        }

        public boolean isUseRow() {
            return useRow;
        }

        public void setUseRow(boolean useRow) {
            this.useRow = useRow;
        }

        public boolean isUseFetch() {
            return useFetch;
        }

        public void setUseFetch(boolean useFetch) {
            this.useFetch = useFetch;
        }

        public boolean isUseFetchFirst() {
            return useFetchFirst;
        }

        public void setUseFetchFirst(boolean useFetchFirst) {
            this.useFetchFirst = useFetchFirst;
        }

        public Integer getFetchIntegerConstant() {
            return fetchIntegerConstant;
        }

        public void setFetchIntegerConstant(Integer fetchIntegerConstant) {
            this.fetchIntegerConstant = fetchIntegerConstant;
        }

        public Expression getFetchOffsetRowCountExpression() {
            return fetchOffsetRowCountExpression;
        }

        public void setFetchOffsetRowCountExpression(Expression fetchOffsetRowCountExpression) {
            this.fetchOffsetRowCountExpression = fetchOffsetRowCountExpression;
        }

        public boolean isUseFetchRow() {
            return useFetchRow;
        }

        public void setUseFetchRow(boolean useFetchRow) {
            this.useFetchRow = useFetchRow;
        }

        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .withDelimiter(Other.SPACE)
                    .append(Keywords.Key.OFFSET);
            if(integerConstant != null){
                b.append(integerConstant);
            } else {
                b.append(offsetRowCountExpression);
            }
            b.append(useRow ? Keywords.Key.ROW : Keywords.Key.ROWS);

            if(useFetch){
                b.append(Keywords.FETCH)
                        .append(useFetchFirst ? Keywords.Key.FIRST : Keywords.Key.NEXT);
                if(integerConstant != null){
                    b.append(fetchIntegerConstant);
                } else {
                    b.append(fetchOffsetRowCountExpression);
                }
                b.append(useFetchRow ? Keywords.Key.ROW : Keywords.Key.ROWS)
                        .append(Keywords.Key.ONLY);
            }

            return b.build();
        }
    }

}
