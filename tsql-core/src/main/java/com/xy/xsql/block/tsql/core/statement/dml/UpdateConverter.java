package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.dml.Update;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class UpdateConverter
        implements MetaContextBlockConverter<Update> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Update>()
                    .overall("UPDATE")
                    .subTakeLine()
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
                        .required()
                        .czse(d ->
                                d.getTableAlias() != null ||
                                d.getTableName() != null
                            )
                            .required()
                            .sub()
                                .description("table_alias/object/function")
                                .czse(d -> d.getTableAlias() != null, "table_alias")
                                    .data(Update::getTableAlias)
                                    .and()
                                .czse(d -> d.getTableName() != null, "<object>")
                                    .data(Update::getTableName)
                                    .and()
            //                    .czse_meta("rowset_function_limited")
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
                            .subTakeLine()
                            .and()
//                        .czse_meta(d -> d.getTableVariable() != null,"@table_variable")
//                            .data(d -> d.getTableVariable())
//                            .and()
                        .headFootTakeLine()
                        .subTakeLine()
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

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(Update context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class SetItemConverter
            implements MetaContextBlockConverter<Update.SetItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.SetItem>()
                        .subTakeLine()
                        .required()
                        .czse_meta(d -> d instanceof Update.ColumnAssignmentSet, ColumnAssignmentSetConverter.meta)
                        .czse_meta(d -> d instanceof Update.VariableAssignmentSet, VariableAssignmentSetConverter.meta)
                        .czse_meta(d -> d instanceof Update.VariableColumnAssignmentSet, VariableColumnAssignmentSetConverter.meta)
                        .czse_meta(d -> d instanceof Update.ColumnCompoundSet, ColumnCompoundSetConverter.meta)
                        .czse_meta(d -> d instanceof Update.VariableCompoundSet, VariableCompoundSetConverter.meta)
                        .czse_meta(d -> d instanceof Update.VariableColumnCompoundSet, VariableColumnCompoundSetConverter.meta)
                        .headFootTakeLine()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

        @Override
        public MetaContextBlock convert(Update.SetItem context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class ColumnAssignmentSetConverter
            implements MetaContextBlockConverter<Update.ColumnAssignmentSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.ColumnAssignmentSet>()
                    .sub("column_name")
                        .data(Update.ColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub()
                        .data("expression/default/null")
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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Update.ColumnAssignmentSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }

    public static class VariableAssignmentSetConverter
            implements MetaContextBlockConverter<Update.VariableAssignmentSet> {

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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Update.VariableAssignmentSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class VariableColumnAssignmentSetConverter
            implements MetaContextBlockConverter<Update.VariableColumnAssignmentSet> {

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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Update.VariableColumnAssignmentSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class ColumnCompoundSetConverter
            implements MetaContextBlockConverter<Update.ColumnCompoundSet> {

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

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Update.ColumnCompoundSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class VariableCompoundSetConverter
            implements MetaContextBlockConverter<Update.VariableCompoundSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Update.VariableCompoundSet>()
                    .sub("@variable")
                        .data(Update.VariableCompoundSet::getVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .required()
                        .data(Update.VariableCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .data(Update.VariableCompoundSet::getExpression)
                        .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

        @Override
        public MetaContextBlock convert(Update.VariableCompoundSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }


    public static class VariableColumnCompoundSetConverter
            implements MetaContextBlockConverter<Update.VariableColumnCompoundSet> {

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
                            .required()
                            .data(Update.VariableCompoundSet::getCompound)
                            .and()
                        .sub("expression")
                            .data(Update.VariableCompoundSet::getExpression)
                            .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

        @Override
        public MetaContextBlock convert(Update.VariableColumnCompoundSet context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }

}
