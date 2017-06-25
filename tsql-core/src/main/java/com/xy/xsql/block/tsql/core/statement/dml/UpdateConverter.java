package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.dml.Update;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class UpdateConverter
        implements BlockConverter<Update> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Update> builder =
            new ReferenceBlockBuilder<Void,Update>()
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
                        .required()
                        .czse(d -> d.getTableAlias() != null, "table_alias")
                            .data(Update::getTableAlias)
                            .and()
                        .czse(d -> d.getTableName() != null, "<object>")
                            .data(Update::getTableName)
                            .and()
    //                    .czse("rowset_function_limited")
    //                    .czse("@table_variable")
                        .headFootTakeLine()
                        .subTakeLine()
                        .and()
                    .sub_keyword(Keywords.SET)
                    .sub()
                        .list(UpdateConverter.SetItemConverter.meta())
                        .data(Update::getSets)
                        .oneMore()
                        .headFootTakeLine()
                        .and()
                    .sub("<OUTPUT Clause>")
                        .optional(d -> d.getOutput() == null)
                        .data(Update::getOutput)
                        .and()
                    .sub("FROM table_source [ ,...n ] ")
                        .optional(d -> d.getFrom() == null)
                        .data(Update::getFrom)
                        .and()
                    //TODO
                    .sub("WHERE { <search_condition>")
                        .optional(d -> d.getWhere() == null)
                        .data(Update::getWhere)
                        .and()
                    .sub("OPTION ( <Query Hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Update::getOption)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Update update) {
        return builder
                .data(update)
                .build();
    }

    public static class SetItemConverter
            implements BlockConverter<Update.SetItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.SetItem> builder =
                new ReferenceBlockBuilder<Void,Update.SetItem>()
                        .subTakeLine()
                        .czse(d -> d instanceof Update.ColumnAssignmentSet, ColumnAssignmentSetConverter.meta())
                        .czse(d -> d instanceof Update.VariableAssignmentSet, VariableAssignmentSetConverter.meta())
                        .czse(d -> d instanceof Update.VariableColumnAssignmentSet, VariableColumnAssignmentSetConverter.meta())
                        .czse(d -> d instanceof Update.ColumnCompoundSet, ColumnCompoundSetConverter.meta())
                        .czse(d -> d instanceof Update.VariableCompoundSet, VariableCompoundSetConverter.meta())
                        .czse(d -> d instanceof Update.VariableColumnCompoundSet, VariableColumnCompoundSetConverter.meta());
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.SetItem setItem) {
            return builder
                    .data(setItem)
                    .build();
        }
    }


    public static class ColumnAssignmentSetConverter
            implements BlockConverter<Update.ColumnAssignmentSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.ColumnAssignmentSet> builder =
                new ReferenceBlockBuilder<Void,Update.ColumnAssignmentSet>()
                    .sub("column_name")
                        .data(Update.ColumnAssignmentSet::getColumnName)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression | DEFAULT | NULL")
                        .data(Update.ColumnAssignmentSet::getExpression)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.ColumnAssignmentSet columnAssignmentSet) {
            return builder
                    .data(columnAssignmentSet)
                    .build();
        }
    }

    public static class VariableAssignmentSetConverter
            implements BlockConverter<Update.VariableAssignmentSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.VariableAssignmentSet> builder =
                new ReferenceBlockBuilder<Void,Update.VariableAssignmentSet>()
                    .sub("@variable")
                        .data(Update.VariableAssignmentSet::getVariable)
                        .and()
                    .sub_keyword(Assignment.ASSIGNMENT)
                    .sub("expression")
                        .data(Update.VariableAssignmentSet::getExpression)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.VariableAssignmentSet variableAssignmentSet) {
            return builder
                    .data(variableAssignmentSet)
                    .build();
        }
    }


    public static class VariableColumnAssignmentSetConverter
            implements BlockConverter<Update.VariableColumnAssignmentSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.VariableColumnAssignmentSet> builder =
                new ReferenceBlockBuilder<Void,Update.VariableColumnAssignmentSet>()
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
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.VariableColumnAssignmentSet variableColumnAssignmentSet) {
            return builder
                    .data(variableColumnAssignmentSet)
                    .build();
        }
    }


    public static class ColumnCompoundSetConverter
            implements BlockConverter<Update.ColumnCompoundSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.ColumnCompoundSet> builder =
                new ReferenceBlockBuilder<Void,Update.ColumnCompoundSet>()
                    .sub("column_name")
                        .data(Update.ColumnCompoundSet::getColumnName)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .data(Update.ColumnCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .data(Update.ColumnCompoundSet::getExpression)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.ColumnCompoundSet columnCompoundSet) {
            return builder
                    .data(columnCompoundSet)
                    .build();
        }
    }


    public static class VariableCompoundSetConverter
            implements BlockConverter<Update.VariableCompoundSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.VariableCompoundSet> builder =
                new ReferenceBlockBuilder<Void,Update.VariableCompoundSet>()
                    .sub("@variable")
                        .data(Update.VariableCompoundSet::getVariable)
                        .and()
                    .sub("+= | -= | *= | /= | %= | &= | ^= | |=")
                        .required()
                        .data(Update.VariableCompoundSet::getCompound)
                        .and()
                    .sub("expression")
                        .data(Update.VariableCompoundSet::getExpression)
                        .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.VariableCompoundSet variableCompoundSet) {
            return builder
                    .data(variableCompoundSet)
                    .build();
        }
    }


    public static class VariableColumnCompoundSetConverter
            implements BlockConverter<Update.VariableColumnCompoundSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Update.VariableColumnCompoundSet> builder =
                new ReferenceBlockBuilder<Void,Update.VariableColumnCompoundSet>()
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
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Update.VariableColumnCompoundSet variableColumnCompoundSet) {
            return builder
                    .data(variableColumnCompoundSet)
                    .build();
        }
    }

}
