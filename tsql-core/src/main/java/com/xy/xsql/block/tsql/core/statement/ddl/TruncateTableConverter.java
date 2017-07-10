package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverter
        implements ReferenceBlockConverter<TruncateTable> {

    // @formatter:off
    private static BlockMetaBuilder<Void,TruncateTable> builder =
            new BlockMetaBuilder<Void,TruncateTable>()
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
                                .list()
                                .ref(PartitionsConverter.meta())
                                .data(TruncateTable::getPartitionsList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .startNewline()
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(TruncateTable truncateTable) {
        return builder
                .data(truncateTable)
                .build();
    }


    public static class PartitionsConverter
            implements ReferenceBlockConverter<TruncateTable.Partitions> {

        // @formatter:off
        private static BlockMetaBuilder<Void,TruncateTable.Partitions> builder =
                new BlockMetaBuilder<Void,TruncateTable.Partitions>()
                        .required()
                        .czse(d -> d instanceof TruncateTable.PartitionNumberExpression)
                            .name("partition_number_expression")
                            .ref(PartitionNumberExpressionConverter.class)
                            .and()
                        .czse(d -> d instanceof TruncateTable.Range)
                            .name("range")
                            .ref(RangeConverter.class)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(TruncateTable.Partitions partitions) {
            return builder
                    .data(partitions)
                    .build();
        }
    }


    public static class RangeConverter
            implements ReferenceBlockConverter<TruncateTable.Range> {

        // @formatter:off
        private static BlockMetaBuilder<Void,TruncateTable.Range> builder =
                new BlockMetaBuilder<Void,TruncateTable.Range>()
                        .overall("range")
                        .sub()
                            .ref(PartitionNumberExpressionConverter.class)
                            .data(TruncateTable.Range::getPartitionNumberExpressionLeft)
                            .and()
                        .sub_keyword(Keywords.TO)
                        .sub()
                            .ref(PartitionNumberExpressionConverter.class)
                            .data(TruncateTable.Range::getPartitionNumberExpressionRight)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(TruncateTable.Range range) {
            return builder
                    .data(range)
                    .build();
        }
    }


    public static class PartitionNumberExpressionConverter
            implements ReferenceBlockConverter<TruncateTable.PartitionNumberExpression> {

        // @formatter:off
        private static BlockMetaBuilder<Void,TruncateTable.PartitionNumberExpression> builder =
                new BlockMetaBuilder<Void,TruncateTable.PartitionNumberExpression>()
                        .overall("partition_number_expression")
                        .data(TruncateTable.PartitionNumberExpression::getNumber);
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(TruncateTable.PartitionNumberExpression partitionNumberExpression) {
            return builder
                    .data(partitionNumberExpression)
                    .build();
        }
    }
}
