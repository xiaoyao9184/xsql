package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
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
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .scope(Update::getWith)
                        .and()
                    .sub()
                        .keyword(Keywords.UPDATE)
                        .and()
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .scope(Update::getTop)
                        .and()
                    .sub()
                        .description("update target")
                        .czse(d ->
                                d.getTableAlias() != null ||
                                d.getTableName() != null
                            )
                            .description("base table target")
                            .sub()
                                .description("table_alias/object/function")
                                .czse(d -> d.getTableAlias() != null, "table_alias")
                                    .scope(Update::getTableAlias)
                                    .and()
                                .czse(d -> d.getTableName() != null, "<object>")
                                    .scope(Update::getTableName)
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
                                    .scope(Update::getTableHintLimitedList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .syntax_required()
                            .syntax_sub_indentation_right()
                            .and()
//                        .czse_ref(d -> d.getTableVariable() != null,"@table_variable")
//                            .data(d -> d.getTableVariable())
//                            .and()
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .format_indentation_right()
                        .and()
                    .sub()
                        .keyword(Keywords.SET)
                        .and()
                    .sub()
                        .description("set clause")
                        .list()
                        .ref(UpdateConverter.SetItemConverter.meta)
                        .scope(Update::getSets)
                        .format_indentation_right()
                        .sub_format_line()
                        .and()
                    .sub("<OUTPUT Clause>")
                        .optional(d -> d.getOutput() == null)
                        .scope(Update::getOutput)
                        .and()
                    .sub("FROM table_source [ ,...n ] ")
                        .optional(d -> d.getFrom() == null)
                        .scope(Update::getFrom)
                        .and()
                    //TODO support CURRENT OF
                    .sub("WHERE <search_condition>")
                        .optional(d -> d.getWhere() == null)
                        .scope(Update::getWhere)
                        .and()
                    .sub("OPTION ( <Query Hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .scope(Update::getOption)
                        .format_line()
                        .and()
                    .syntax_sub_line()
                    .sub_format_line(true)
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
                        .czse_ref(d -> d instanceof Update.ColumnAssignmentSet, ColumnAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableAssignmentSet, VariableAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableColumnAssignmentSet, VariableColumnAssignmentSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.ColumnCompoundSet, ColumnCompoundSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableCompoundSet, VariableCompoundSetConverter.meta)
                        .czse_ref(d -> d instanceof Update.VariableColumnCompoundSet, VariableColumnCompoundSetConverter.meta)
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
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
                        .scope(Update.ColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub()
                        .description("expression/default/null")
                        .czse(d -> d.getExpression() != null,"expression")
                            .scope(Update.ColumnAssignmentSet::getExpression)
                            .and()
                        .czse_keyword(Update.ColumnAssignmentSet::isUseDefault,Keywords.DEFAULT)
                        .czse_keyword(Update.ColumnAssignmentSet::isUseNull,Keywords.NULL)
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
                        .scope(Update.VariableAssignmentSet::getVariable)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression")
                        .scope(Update.VariableAssignmentSet::getExpression)
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
                        .scope(Update.VariableColumnAssignmentSet::getVariable)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("column")
                        .scope(Update.VariableColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression")
                        .scope(Update.VariableColumnAssignmentSet::getExpression)
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
                        .scope(Update.ColumnCompoundSet::getColumnName)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .scope(Update.ColumnCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .scope(Update.ColumnCompoundSet::getExpression)
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
                        .scope(Update.VariableCompoundSet::getVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .syntax_required()
                        .scope(Update.VariableCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .scope(Update.VariableCompoundSet::getExpression)
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
                            .scope(Update.VariableCompoundSet::getVariable)
                            .and()
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("column")
                            .scope(Update.VariableColumnCompoundSet::getColumnName)
                            .and()
                        .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                            .syntax_required()
                            .scope(Update.VariableCompoundSet::getCompound)
                            .and()
                        .sub("expression")
                            .scope(Update.VariableCompoundSet::getExpression)
                            .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
        return meta;
    }

    }

}
