package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.dml.Update;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class UpdateConverter
        implements ModelMetaBlockConverter<Update> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Update>()
                    .overall("UPDATE")
                    .style_sub_line_delimiter()
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .data(Update::getWith)
                        .and()
                    .sub_keyword(Keywords.UPDATE)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .data(Update::getTop)
                        .and()
                    .sub()
                        .description("update target")
                        .style_required()
                        .czse(d ->
                                d.getTableAlias() != null ||
                                d.getTableName() != null
                            )
                            .style_required()
                            .sub()
                                .description("table_alias/object/function")
                                .czse(d -> d.getTableAlias() != null, "table_alias")
                                    .data(Update::getTableAlias)
                                    .and()
                                .czse(d -> d.getTableName() != null, "<object>")
                                    .data(Update::getTableName)
                                    .and()
            //                    .czse_ref("rowset_function_limited")
                                .and()
                            .sub()
                                .description("with table hint")
                                .optional(d -> d.getTableHintLimitedList() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub("Table_Hint_Limited")
                                    .repeat()
                                    .ref(TableHintLimitedConverter.class)
                                    .data(Update::getTableHintLimitedList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .style_sub_line_delimiter()
                            .and()
//                        .czse_ref(d -> d.getTableVariable() != null,"@table_variable")
//                            .data(d -> d.getTableVariable())
//                            .and()
                        .style_convention_line_delimiter()
                        .style_sub_line_delimiter()
                        .and()
                    .sub_keyword(Keywords.SET)
                    .sub()
                        .description("set clause")
                        .list()
                        .ref(UpdateConverter.SetItemConverter.meta)
                        .data(Update::getSets)
                        .and()
                    .sub("<OUTPUT Clause>")
                        .optional(d -> d.getOutput() == null)
                        .data(Update::getOutput)
                        .and()
                    .sub("FROM table_source [ ,...n ] ")
                        .optional(d -> d.getFrom() == null)
                        .data(Update::getFrom)
                        .and()
                    //TODO support CURRENT OF
                    .sub("WHERE <search_condition>")
                        .optional(d -> d.getWhere() == null)
                        .data(Update::getWhere)
                        .and()
                    .sub("OPTION ( <Query Hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Update::getOption)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class SetItemConverter
            implements ModelMetaBlockConverter<Update.SetItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.SetItem>()
                        .style_sub_line_delimiter()
                        .style_required()
                        .czse_ref(d -> d instanceof Update.ColumnAssignmentSet, ColumnAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableAssignmentSet, VariableAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableColumnAssignmentSet, VariableColumnAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.ColumnCompoundSet, ColumnCompoundSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableCompoundSet, VariableCompoundSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableColumnCompoundSet, VariableColumnCompoundSetConverter.meta)
                        .style_convention_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }


    public static class ColumnAssignmentSetConverter
            implements ModelMetaBlockConverter<Update.ColumnAssignmentSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.ColumnAssignmentSet>()
                    .sub("column_name")
                        .data(Update.ColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub()
                        .description("expression/default/null")
                        .czse(d -> d.getExpression() != null,"expression")
                            .data(Update.ColumnAssignmentSet::getExpression)
                            .and()
                        .czse(Update.ColumnAssignmentSet::isUseDefault,"DEFAULT")
                            .keyword(Keywords.DEFAULT)
                            .and()
                        .czse(Update.ColumnAssignmentSet::isUseNull,"NULL")
                            .keyword(Keywords.NULL)
                            .and()
                        .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class VariableAssignmentSetConverter
            implements ModelMetaBlockConverter<Update.VariableAssignmentSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.VariableAssignmentSet>()
                    .sub("@variable")
                        .data(Update.VariableAssignmentSet::getVariable)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression")
                        .data(Update.VariableAssignmentSet::getExpression)
                        .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class VariableColumnAssignmentSetConverter
            implements ModelMetaBlockConverter<Update.VariableColumnAssignmentSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.VariableColumnAssignmentSet>()
                    .sub("@variable")
                        .data(Update.VariableColumnAssignmentSet::getVariable)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("column")
                        .data(Update.VariableColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression")
                        .data(Update.VariableColumnAssignmentSet::getExpression)
                        .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class ColumnCompoundSetConverter
            implements ModelMetaBlockConverter<Update.ColumnCompoundSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.ColumnCompoundSet>()
                    .sub("column_name")
                        .data(Update.ColumnCompoundSet::getColumnName)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .data(Update.ColumnCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .data(Update.ColumnCompoundSet::getExpression)
                        .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class VariableCompoundSetConverter
            implements ModelMetaBlockConverter<Update.VariableCompoundSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.VariableCompoundSet>()
                    .sub("@variable")
                        .data(Update.VariableCompoundSet::getVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .style_required()
                        .data(Update.VariableCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .data(Update.VariableCompoundSet::getExpression)
                        .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }


    public static class VariableColumnCompoundSetConverter
            implements ModelMetaBlockConverter<Update.VariableColumnCompoundSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.VariableColumnCompoundSet>()
                        .sub("@variable")
                            .data(Update.VariableCompoundSet::getVariable)
                            .and()
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("column")
                            .data(Update.VariableColumnCompoundSet::getColumnName)
                            .and()
                        .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                            .style_required()
                            .data(Update.VariableCompoundSet::getCompound)
                            .and()
                        .sub("expression")
                            .data(Update.VariableCompoundSet::getExpression)
                            .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }

}
