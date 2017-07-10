package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.element.Other;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OutputConverter
        implements MetaContextBlockConverter<Output> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Output> builder =
            new BlockMetaBuilder<Void,Output>()
                    .overall("OUTPUT Clause")
                    .sub()
                        .description("output with into")
                        .optional(d -> d.getDmlSelectList() == null)
                        .sub_keyword(Keywords.Key.OUTPUT)
                        .sub("dml_select_list")
                            .ref(DmlSelectListConverter.class)
                            .data(Output::getDmlSelectList)
                            .and()
                        .sub_keyword(Keywords.INTO)
                        .sub()
                            .description("@table_variable/output_table")
                            .required()
                            .czse(d -> d.getTableVariable() != null, "@table_variable")
                                .data(Output::getTableVariable)
                                .and()
                            .czse(d -> d.getOutputTable() != null, "output_table")
                                .data(Output::getOutputTable)
                                .and()
                            .and()
                        .sub()
                            .description("[ ( column_list ) ] ]")
                            .optional(data -> data.getColumnList() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub("column_list")
                                .data(Output::getColumnList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .and()
                    .sub()
                        .description("output without into")
                        .optional(d -> d.getOutputDmlSelectList() == null)
                        .sub_keyword(Keywords.Key.OUTPUT)
                        .sub("dml_select_list")
                            .ref(DmlSelectListConverter.class)
                            .data(Output::getOutputDmlSelectList)
                            .and()
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(Output context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

    public static class DmlSelectListConverter
            implements MetaContextBlockConverter<List<Output.DmlSelect>> {

        // @formatter:off
        private static BlockMetaBuilder<Void,List<Output.DmlSelect>> builder =
                new BlockMetaBuilder<Void,List<Output.DmlSelect>>()
                        .overall("dml_select_list")
                        .list()
                        .ref(DmlSelectConverter.meta());
//                        .sub_meta(DmlSelectConverter.meta());
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(List<Output.DmlSelect> context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }


    public static class DmlSelectConverter
            implements MetaContextBlockConverter<Output.DmlSelect> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Output.DmlSelect> builder =
                new BlockMetaBuilder<Void,Output.DmlSelect>()
                        .description("dml_select_list's item")
                        .sub()
                            .description("column_name/scalar_expression")
                            .required()
                            .czse(d -> d.getColumnName() != null, "column_name")
                                .ref(ColumnNameConverter.class)
                                .data(Output.DmlSelect::getColumnName)
                                .and()
                            .czse(d -> d.getScalarExpression() != null, "scalar_expression")
                                .data(Output.DmlSelect::getScalarExpression)
                                .and()
                            .required()
                            .and()
                        .sub()
                            .description("as column_alias_identifier")
                            .optional(data -> data.getColumnAliasIdentifier() == null)
                            .sub()
                                .optional(Output.DmlSelect::isUseAs)
                                .keyword(Keywords.AS)
                                .and()
                            .sub("column_alias_identifier")
                                .data(Output.DmlSelect::getColumnAliasIdentifier)
                                .and()
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(Output.DmlSelect context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

    public static class ColumnNameConverter
            implements MetaContextBlockConverter<Output.ColumnName> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Output.ColumnName> builder =
                new BlockMetaBuilder<Void,Output.ColumnName>()
                        .overall("column_name")
                        .czse(d ->
                            d.isUseDeleted() ||
                            d.isUseInserted() ||
                            d.getFromTableName() != null ||
                            d.getColumnName() != null
                        )
                            .description("deleted/inserted/from_table_name column_name")
                            .sub()
                                .description("deleted/inserted/from_table_name")
                                .required()
                                .czse(Output.ColumnName::isUseDeleted)
                                    .keyword(Keywords.Key.DELETED)
                                    .and()
                                .czse(Output.ColumnName::isUseInserted)
                                    .keyword(Keywords.Key.INSERTED)
                                    .and()
                                .czse(d -> d.getFromTableName() != null,"from_table_name")
                                    .data(Output.ColumnName::getFromTableName)
                                    .and()
                                .and()
                            .sub(".")
                                .keyword(Other.POINT)
                                .and()
                            .sub()
                                .description("*/column_name")
                                .required()
                                .czse(Output.ColumnName::isUseAll, "*")
                                    .keyword(Other.ASTERISK)
                                    .and()
                                .czse(d -> !d.isUseAll(), "column_name")
                                    .data(Output.ColumnName::getColumnName)
                                    .and()
                                .and()
                            .and()
                        .czse(Output.ColumnName::is$action, "$action")
                            .data(d -> "$action")
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(Output.ColumnName context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }


}
