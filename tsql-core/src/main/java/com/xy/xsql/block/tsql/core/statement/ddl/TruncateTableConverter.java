package com.xy.xsql.block.tsql.core.statement.ddl;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.ddl.TruncateTable;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class TruncateTableConverter
        implements MetaContextBlockConverter<TruncateTable> {

    // @formatter:off
    public static BlockMeta meta =
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
                                .ref(PartitionsConverter.meta)
                                .data(TruncateTable::getPartitionsList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .startNewline()
                        .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(TruncateTable context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class PartitionsConverter
            implements MetaContextBlockConverter<TruncateTable.Partitions> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TruncateTable.Partitions>()
                        .required()
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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(TruncateTable.Partitions context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class RangeConverter
            implements MetaContextBlockConverter<TruncateTable.Range> {

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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(TruncateTable.Range context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class PartitionNumberExpressionConverter
            implements MetaContextBlockConverter<TruncateTable.PartitionNumberExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,TruncateTable.PartitionNumberExpression>()
                        .overall("partition_number_expression")
                        .data(TruncateTable.PartitionNumberExpression::getNumber)
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(TruncateTable.PartitionNumberExpression context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }
}
