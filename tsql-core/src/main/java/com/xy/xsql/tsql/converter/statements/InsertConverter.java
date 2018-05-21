package com.xy.xsql.tsql.converter.statements;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.queries.OutputConverter;
import com.xy.xsql.tsql.converter.queries.TableValueConstructorConverter;
import com.xy.xsql.tsql.converter.queries.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.statements.Insert;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class InsertConverter
        implements ModelMetaBlockConverter<Insert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Insert>()
                    .overall("INSERT")
                    .sub("WITH <common_table_expression> [ ,...n ]")
                        .optional(d -> d.getWith() == null)
                        .scope(Insert::getWith)
                        .and()
                    .sub_keyword(Keywords.INSERT)
                    .sub("TOP ( expression ) [ PERCENT ]")
                        .optional(d -> d.getTop() == null)
                        .scope(Insert::getTop)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseInto())
                        .keyword(Keywords.INTO)
                        .and()
                    .sub()
                        .description("into target")
                        .syntax_required()
                        .sub()
                            .description("object/function")
                            .czse(d -> d.getTableName() != null, "<object>")
                                .scope(Insert::getTableName)
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
                                .scope(Insert::getTableHintLimitedList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .syntax_sub_line()
                        .syntax_context_indentation()
                        .format_indentation_right()
                        .and()
                    .sub()
                        .description("(column_list)")
                        .optional(d -> d.getColumns() == null)
                        .sub_keyword(Other.GROUP_START)
                        .sub("column_list")
                    //TODO reference style
//                            .sub_ref(ColumnListConverter.meta,Insert::getColumns)
                            .ref(ColumnListConverter.meta)
                            .scope(Insert::getColumns)
                            .syntax_reference_remove()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .format_indentation_right()
                        .and()
                    .sub("OUTPUT Clause")
                        .optional(d -> d.getOutput() == null)
                        .ref(OutputConverter.class)
                        .scope(Insert::getOutput)
                        .and()
                    .sub()
                        .description("values")
                        .syntax_required()
                        //TODO donot use ref
                        .czse(d -> d.getValues() != null, "VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n ]")
                            .ref(TableValueConstructorConverter.class)
                            .scope(Insert::getValues)
                            .syntax_reference_remove()
                            .and()
    //                    .czse_meta("derived_table")
    //                    .czse_meta("execute_statement")
    //                    .czse_meta("<dml_table_source>")
                        .czse(Insert::isUseDefaultValues)
                            .description("detault values")
                            .sub_keyword(Keywords.DEFAULT)
                            .sub_keyword(Keywords.VALUES)
                            .and()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .and()
                    .syntax_sub_line()
                    .sub_format_line(true)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ColumnListConverter
            implements ModelMetaBlockConverter<List<ColumnName>> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,List<ColumnName>>()
                        .description("column_list")
                        .list()
                        .ref(ColumnNameConverter.meta)
                        .scope(d -> d)
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class ColumnNameConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,ColumnName>()
                        .description("column")
                        .scope(ColumnName::getName)
                        .build();
        // @formatter:on

    }

}
