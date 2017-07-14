package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverter
        implements ModelMetaBlockConverter<TruncateTable> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TruncateTable>()
                    .overall("TRUNCATE TABLE")
                    .sub_keyword(Keywords.TRUNCATE)
                    .sub_keyword(Keywords.TABLE)
                    .sub("[ { database_name .[ schema_name ] . | schema_name . } ]  table_name")
                        .data(TruncateTable::getTableName)
                        .style_start_new_line()
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
                                .ref(PartitionsConverter.meta)
                                .data(TruncateTable::getPartitionsList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .style_start_new_line()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class PartitionsConverter
            implements ModelMetaBlockConverter<TruncateTable.Partitions> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TruncateTable.Partitions>()
                        .style_required()
                        .czse(d -> d instanceof TruncateTable.PartitionNumberExpression)
                            .name("partition_number_expression")
                            .ref(PartitionNumberExpressionConverter.class)
                            .and()
                        .czse(d -> d instanceof TruncateTable.Range)
                            .name("range")
                            .ref(RangeConverter.class)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class RangeConverter
            implements ModelMetaBlockConverter<TruncateTable.Range> {

        // @formatter:off
        public static BlockMeta meta =
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
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class PartitionNumberExpressionConverter
            implements ModelMetaBlockConverter<TruncateTable.PartitionNumberExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TruncateTable.PartitionNumberExpression>()
                        .overall("partition_number_expression")
                        .sub()
                            .data(TruncateTable.PartitionNumberExpression::getNumber)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
