package com.xy.xsql.tsql.style.constructor.builder.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_ORDER_BY;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_BULK_INSERT extends BulkInsertBuilder {

    public static class b_FROM extends CodeBuilder<String> {
        public b_FROM(String s) {
            super(s);
        }
    }

    public static class b_WITH extends CodeBuilder<List<b$with_item>> {

        public b_WITH() {
            super(new ArrayList<>());
        }
    }

    public static class b$with_item extends CodeBuilder<WithSetter> {

        public b$with_item(WithSetter withSetter) {
            super(withSetter);
        }

    }

    public static class b$with_order extends b$with_item {

        private OrderColumnListBuilder<b$with_order> orderBuilder;

        public b$with_order() {
            super(null);
            this.orderBuilder = new OrderColumnListBuilder<b$with_order>()
                    .in(this);
            this.target = orderBuilder;
        }

        public OrderColumnBuilder<OrderColumnListBuilder<b$with_order>> withItem(){
            return orderBuilder.withItem();
        }


        /*
        Item
         */

        public b$with_order $$(ColumnName columnName){
            return withItem()
                    .withColumnName(columnName)
                    .and()
                    .and();
        }

        public b$with_order $$(ColumnName columnName, b_ORDER_BY.k_ASC asc){
            return withItem()
                    .withColumnName(columnName)
                    .withAsc()
                    .and()
                    .and();
        }

        public b$with_order $$(ColumnName columnName, b_ORDER_BY.k_DESC desc){
            return withItem()
                    .withColumnName(columnName)
                    .withDesc()
                    .and()
                    .and();
        }
    }

}
