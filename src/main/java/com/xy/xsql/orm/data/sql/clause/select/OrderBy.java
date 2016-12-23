package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

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
public class OrderBy implements ElementList {

    private List<OrderByItem> items;

    //[ <offset_fetch> ]
    private OffsetFetch offsetFetch;


    public List<OrderByItem> getItems() {
        return items;
    }

    public void setItems(List<OrderByItem> items) {
        this.items = items;
    }

    public OffsetFetch getOffsetFetch() {
        return offsetFetch;
    }

    public void setOffsetFetch(OffsetFetch offsetFetch) {
        this.offsetFetch = offsetFetch;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.ORDER)
                .append(GrammarEnum.BY)
                .append(items, OtherEnum.DELIMITER)
                .append(offsetFetch != null ? offsetFetch : null);
        return b.build();
    }


    /**
     *

     order_by_expression
     [ COLLATE collation_name ]
     [ ASC | DESC ]

     *
     */
    public static class OrderByItem implements ElementList  {

        //
        private Expression orderByExpression;
        //[ COLLATE collation_name ]

        //[ ASC | DESC ]
        private boolean useAsc;
        private boolean useDesc;

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
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(orderByExpression);
            if(useAsc){
                b.append(GrammarEnum.ASC);
            } else if(useDesc){
                b.append(GrammarEnum.DESC);
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
    public static class OffsetFetch implements ElementList {

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
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(GrammarEnum.OFFSET);
            if(integerConstant != null){
                b.append(integerConstant);
            } else {
                b.append(offsetRowCountExpression);
            }
            b.append(useRow ? GrammarEnum.ROW : GrammarEnum.ROWS);

            if(useFetch){
                b.append(GrammarEnum.FETCH)
                        .append(useFetchFirst ? GrammarEnum.FIRST : GrammarEnum.NEXT);
                if(integerConstant != null){
                    b.append(fetchIntegerConstant);
                } else {
                    b.append(fetchOffsetRowCountExpression);
                }
                b.append(useFetchRow ? GrammarEnum.ROW : GrammarEnum.ROWS)
                        .append(GrammarEnum.ONLY);
            }


            return b.build();
        }
    }

}
