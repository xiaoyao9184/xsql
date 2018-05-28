package com.xy.xsql.tsql.style.constructor.builder.statements;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder;
import com.xy.xsql.tsql.model.statements.TruncateTable;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_TRUNCATE_TABLE extends TruncateTableBuilder {


    public static class b_WITH extends b_PARTITIONS {

    }

    public static class b_PARTITIONS extends PartitionsListBuilder<b_PARTITIONS> {

        public b_PARTITIONS() {
            this.in(this);
        }


        public b_PARTITIONS $$(
                Integer partition_number_expression){
            withItem(e_pn(partition_number_expression));
            return this;
        }

        public b_PARTITIONS $$(
                Integer partition_number_expression,
                b_TO to){
            TruncateTable.Range range = new TruncateTable.Range();
            range.setPartitionNumberExpressionLeft(e_pn(partition_number_expression));
            range.setPartitionNumberExpressionRight(to.build());
            withItem(range);
            return this;
        }


        public static class b_TO extends CodeBuilder<TruncateTable.PartitionNumberExpression> {

            public b_TO(TruncateTable.PartitionNumberExpression partitionNumberExpression) {
                super(partitionNumberExpression);
            }
        }
    }
}
