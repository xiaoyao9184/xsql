package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.Other;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OutputConverter
        implements BlockConverter<Output> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Output> builder =
            new ReferenceBlockBuilder<Void,Output>()
                    .overall("OUTPUT_CLAUSE")
                    .oneOf()
                        .description("Output Into")
                        .sub_keyword(Keywords.Key.OUTPUT)
                        .sub("dml_select_list")
                            .ref(Output.DmlSelect.class)
                            .data(Output::getDmlSelectList)
                            .and()
                        .sub_keyword(Keywords.INTO)
                        .sub()
                            .required()
                            .description("{ @table_variable | output_table }")
                            .oneOf("@table_variable")
                                .data(Output::getTableVariable)
                                .and()
                            .oneOf("output_table")
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
                    .oneOf()
                        .description("Output")
                        .optional(d -> d.getOutputDmlSelectList() == null)
                        .sub_keyword(Keywords.Key.OUTPUT)
                        .sub("dml_select_list")
                            .ref(Output.DmlSelect.class)
                            .data(Output::getOutputDmlSelectList)
                            .and()
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Output output) {
        return builder
                .data(output)
                .build();
    }

    public static class DmlSelectListConverter
            implements BlockConverter<List<Output.DmlSelect>> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,List<Output.DmlSelect>> builder =
                new ReferenceBlockBuilder<Void,List<Output.DmlSelect>>()
                        .overall("dml_select_list")
                        .list()
                        .sub(DmlSelectConverter.meta())
                        .more();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(List<Output.DmlSelect> dmlSelectList) {
            return builder
                    .data(dmlSelectList)
                    .build();
        }
    }


    public static class DmlSelectConverter
            implements BlockConverter<Output.DmlSelect> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Output.DmlSelect> builder =
                new ReferenceBlockBuilder<Void,Output.DmlSelect>()
                        .sub()
                            .description("{ <column_name> | scalar_expression }")
                            .required()
                            .oneOf("column_name")
                                .ref(Output.ColumnName.class)
                                .data(Output.DmlSelect::getColumnName)
                                .and()
                            .oneOf("scalar_expression")
                                .data(Output.DmlSelect::getScalarExpression)
                                .and()
                            .required()
                            .and()
                        .sub()
                            .description("[ [AS] column_alias_identifier ]")
                            .optional(data -> data.getScalarExpression() == null)
                            .sub()
                                .optional(Output.DmlSelect::isUseAs)
                                .keyword(Keywords.AS)
                                .and()
                            .sub("column_alias_identifier")
                                .data(Output.DmlSelect::getColumnAliasIdentifier)
                                .and()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Output.DmlSelect dmlSelect) {
            return builder
                    .data(dmlSelect)
                    .build();
        }
    }

    public static class ColumnNameConverter
            implements BlockConverter<Output.ColumnName> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Output.ColumnName> builder =
                new ReferenceBlockBuilder<Void,Output.ColumnName>()
                        .overall("column_name")
                        .oneOf()
                            .sub("{ DELETED | INSERTED | from_table_name }")
                                .required()
                                .oneOf()
                                    .keyword(Keywords.Key.DELETED)
                                    .and()
                                .oneOf()
                                    .keyword(Keywords.Key.INSERTED)
                                    .and()
                                .oneOf("from_table_name")
                                    .data(Output.ColumnName::getFromTableName)
                                    .and()
                                .and()
                            .sub(".")
                                .data(".")
                                .and()
                            .sub()
                                .description("{ * | column_name }")
                                .required()
                                .oneOf("*")
                                    .data("*")
                                    .and()
                                .oneOf("column_name")
                                    .data(Output.ColumnName::getColumnName)
                                    .and()
                                .and()
                            .and()
                        .oneOf("$action")
                            .data("$action")
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Output.ColumnName columnName) {
            return builder
                    .data(columnName)
                    .build();
        }
    }


}
