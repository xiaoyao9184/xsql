package com.xy.xsql.tsql.style.constructor.builder.queries.hints;

import com.xy.xsql.tsql.builder.chain.queries.hints.QueryHintBuilder;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b$query_hint extends QueryHintBuilder<b$query_hint> {

    public b$query_hint() {
        this.in(this);
    }


    public static class b_OPTIMIZE_FOR extends OptimizeForListBuilder<b_OPTIMIZE_FOR> {

        public b_OPTIMIZE_FOR() {
            this.in(this);
        }


        public static class k_UNKNOWN {}

        /*
        Item
         */

        public b_OPTIMIZE_FOR $$(String variableName, String literalConstant) {
            return withItem()
                    .withVariableName(variableName)
                    .and()
                    .and();
        }

        public b_OPTIMIZE_FOR $$(String variableName, k_UNKNOWN unknown) {
            return withItem()
                    .withVariableName(variableName)
                    .withUnknown()
                    .and()
                    .and();
        }

    }

}
