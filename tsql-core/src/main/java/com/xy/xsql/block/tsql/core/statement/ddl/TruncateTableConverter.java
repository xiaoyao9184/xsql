package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
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
                        .scope(TruncateTable::getTableName)
                        .syntax_line()
                        .and()
                    .sub()
                        .description("with partitions")
                        .optional(d -> d.getPartitionsList() == null)
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .sub_keyword(Keywords.Key.PARTITIONS)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .list()
                                .ref(PartitionsConverter.meta)
                                .scope(TruncateTable::getPartitionsList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_line()
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
                        .syntax_required()
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
                            .scope(TruncateTable.Range::getPartitionNumberExpressionLeft)
                            .and()
                        .sub_keyword(Keywords.TO)
                        .sub()
                            .ref(PartitionNumberExpressionConverter.class)
                            .scope(TruncateTable.Range::getPartitionNumberExpressionRight)
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
                            .scope(TruncateTable.PartitionNumberExpression::getNumber)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
