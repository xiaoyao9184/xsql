package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.tsql.builder.chain.queries.select.OverBuilder;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_OVER extends OverBuilder<b_OVER> {

    public b_OVER() {
        this.in(this);
    }


    public static class b_PARTITION_BY extends PartitionByBuilder<b_PARTITION_BY> {

        public b_PARTITION_BY() {
            this.in(this);
        }

    }

    @Deprecated
    public static class b_ORDER_BY extends OrderByBuilder<b_ORDER_BY> {

        public b_ORDER_BY() {
            this.in(this);
        }

    }

    public static class b_ROW_RANGE extends RowRangeBuilder<b_ROW_RANGE> {

        public b_ROW_RANGE() {
            this.in(this);
        }


        public static class k_UNBOUNDED {}

        public static class k_PRECEDING {}

        public static class k_FOLLOWING {}

        public static class k_CURRENT_ROW {}

        public static class b_BETWEEN extends OverBuilder.WindowFrameBetweenBuilder<b_BETWEEN>{

            public b_BETWEEN() {
                this.in(this);
            }

            public static class b_AND extends OverBuilder.WindowFrameBoundBuilder<b_AND>{

                public b_AND() {
                    this.in(this);
                }
            }

        }


    }

}
