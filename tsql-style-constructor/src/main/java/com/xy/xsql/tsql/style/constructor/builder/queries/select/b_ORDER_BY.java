package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_FROM;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_ORDER_BY extends OrderByBuilder<b_ORDER_BY> {

    public b_ORDER_BY() {
        this.in(this);
    }

    /*
    Item
     */

    public b_ORDER_BY $$(Expression expression){
        return withItem()
                .withExpression(expression)
                .and()
            .and();
    }

    public b_ORDER_BY $$(Expression expression, k_ASC ASC){
        return withItem()
                .withAsc()
                .withExpression(expression)
                .and()
            .and();
    }

    public b_ORDER_BY $$(Expression expression, k_DESC desc){
        return withItem()
                .withDesc()
                .withExpression(expression)
                .and()
            .and();
    }

    public static class k_ASC {}

    public static class k_DESC {}

    public static class k_ROW {}

    /**
     * same as
     * @see b_FROM.b_TABLESAMPLE.k_ROWS
     * @see b_ORDER_BY.k_ROWS
     */
    public static class k_ROWS {}

    public static class b_OFFSET extends OffsetFetchBuilder<b_OFFSET> {

        public b_OFFSET() {
            this.in(this);
        }


        public static class b_FETCH extends OffsetFetchBuilder<b_FETCH> {

            public b_FETCH() {
                this.in(this);
            }

            public static class k_FIRST {}

            public static class k_NEXT {}

            public static class k_ONLY {}

        }
    }

}
