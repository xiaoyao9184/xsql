package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.OutputConverter;
import com.xy.xsql.block.tsql.core.clause.TableValueConstructorConverter;
import com.xy.xsql.block.tsql.core.clause.hints.TableHintLimitedConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.statement.dml.Insert;

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
                        .style_required()
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
                        .style_sub_line_delimiter()
                        .style_convention_line_delimiter()
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
                            .style_remove_reference()
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
                        .style_required()
                        //TODO donot use ref
                        .czse(d -> d.getValues() != null, "VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n ]")
                            .ref(TableValueConstructorConverter.class)
                            .scope(Insert::getValues)
                            .style_remove_reference()
                            .and()
    //                    .czse_meta("derived_table")
    //                    .czse_meta("execute_statement")
    //                    .czse_meta("<dml_table_source>")
                        .czse(Insert::isUseDefaultValues)
                            .description("detault values")
                            .sub_keyword(Keywords.DEFAULT)
                            .sub_keyword(Keywords.VALUES)
                            .and()
                        .style_convention_line_delimiter()
                        .style_sub_line_delimiter()
                        .and()
                    .style_sub_line_delimiter()
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
                        .scope(d -> d)
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
