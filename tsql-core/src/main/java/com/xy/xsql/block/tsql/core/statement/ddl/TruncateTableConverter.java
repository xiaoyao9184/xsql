package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverter
        implements BlockConverter<TruncateTable> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,TruncateTable> builder =
            new ReferenceBlockBuilder<Void,TruncateTable>()
                    .overall("TRUNCATE TABLE")
                    .sub_keyword(Keywords.TRUNCATE)
                    .sub_keyword(Keywords.TABLE)
                    .sub("[ { database_name .[ schema_name ] . | schema_name . } ]  table_name")
                        .data(TruncateTable::getTableName)
                        .startNewline()
                        .and()
                    .sub()
                        .description("[ WITH ( PARTITIONS ( { <partition_number_expression> | <range> }[ , ...n ] ) ) ]")
                        .optional(d -> d.getPartitionsList() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .sub_keyword(Keywords.Key.PARTITIONS)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .list(PartitionsConverter.meta())
                                .data(TruncateTable::getPartitionsList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .startNewline()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(TruncateTable truncateTable) {
        return builder
                .data(truncateTable)
                .build();
    }


    public static class PartitionsConverter
            implements BlockConverter<TruncateTable.Partitions> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TruncateTable.Partitions> builder =
                new ReferenceBlockBuilder<Void,TruncateTable.Partitions>()
                        .czse(d -> d instanceof TruncateTable.PartitionNumberExpression)
                            .name("partition_number_expression")
                            .ref(TruncateTable.PartitionNumberExpression.class)
                            .and()
                        .czse(d -> d instanceof TruncateTable.Range)
                            .name("range")
                            .ref(TruncateTable.Range.class)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(TruncateTable.Partitions partitions) {
            return builder
                    .data(partitions)
                    .build();
        }
    }


    public static class RangeConverter
            implements BlockConverter<TruncateTable.Range> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TruncateTable.Range> builder =
                new ReferenceBlockBuilder<Void,TruncateTable.Range>()
                        .overall("range")
                        .sub()
                            .ref(TruncateTable.PartitionNumberExpression.class)
                            .data(TruncateTable.Range::getPartitionNumberExpressionLeft)
                            .and()
                        .sub_keyword(Keywords.TO)
                        .sub()
                            .ref(TruncateTable.PartitionNumberExpression.class)
                            .data(TruncateTable.Range::getPartitionNumberExpressionRight)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(TruncateTable.Range range) {
            return builder
                    .data(range)
                    .build();
        }
    }


    public static class PartitionNumberExpressionConverter
            implements BlockConverter<TruncateTable.PartitionNumberExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,TruncateTable.PartitionNumberExpression> builder =
                new ReferenceBlockBuilder<Void,TruncateTable.PartitionNumberExpression>()
                        .overall("partition_number_expression")
                        .data(TruncateTable.PartitionNumberExpression::getNumber);
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(TruncateTable.PartitionNumberExpression partitionNumberExpression) {
            return builder
                    .data(partitionNumberExpression)
                    .build();
        }
    }
}
