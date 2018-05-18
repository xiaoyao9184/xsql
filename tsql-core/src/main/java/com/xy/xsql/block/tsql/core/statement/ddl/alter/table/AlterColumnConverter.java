package com.xy.xsql.block.tsql.core.statement.ddl.alter.table;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.operators.Assignment;
import com.xy.xsql.tsql.model.statements.alter.table.AlterColumn;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterColumnConverter
        implements ModelMetaBlockConverter<AlterColumn> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AlterColumn>()
                    .description("alter table alter column item")
                    .sub()
                        .description("alter column")
                        .sub_keyword(Keywords.ALTER)
                        .sub_keyword(Keywords.COLUMN)
                        .sub("column_name")
                            .scope(AlterColumn::getColumnName)
                            .and()
                        .and()
                    .sub()
                        .description("type/other flag")
                        .czse(d -> d.getTypeName() != null || d.getUseNull() != null || d.getCollationName() != null)
                            .sub()
//                                .optional(d -> d.getTypeName() == null)
                                .ref(AlterColumnTypeConverter.meta)
                                .scope(d -> d.getTypeName())
                                .and()
                            .sub()
                                .optional(d -> d.getCollationName() == null)
                                .sub_keyword(Keywords.COLLATE)
                                .sub("collation_name")
                                    .scope(d -> d.getCollationName())
                                    .and()
                                .and()
                            .sub()
                                .sub()
                                    .optional(d -> d.getUseNull() == null)
                                    .czse_keyword(d -> d.getUseNull(), Keywords.NULL)
                                    .czse(d -> !d.getUseNull())
                                        .keyword(Keywords.NOT)
                                        .keyword(Keywords.NULL)
                                        .and()
                                    .and()
                                .sub()
                                    .optional(d -> !d.isUseSparse())
                                    .keyword(Keywords.Key.SPARSE)
                                    .and()
                                .and()
                            .syntax_sub_line()
                            .and()
                        .czse(d -> d.getUseAdd() != null && d.getMaskFunction() == null)
                            .sub()
                                .czse_keyword(d -> d.getUseAdd(),Keywords.ADD)
                                .czse_keyword(d -> !d.getUseAdd(),Keywords.DROP)
                                .syntax_required()
                                .and()
                            .sub()
                                .syntax_required()
                                .czse_keyword(d -> d.isUseRowGuidCol(),Keywords.ROWGUIDCOL)
                                .czse_keyword(d -> d.isUsePersisted(),Keywords.Key.PERSISTED)
                                .czse(d -> d.isUseNotForReplication())
                                    .description("not for reflication")
                                    .optional(d -> !d.isUseNotForReplication())
                                    .sub_keyword(Keywords.NOT)
                                    .sub_keyword(Keywords.FOR)
                                    .sub_keyword(Keywords.REPLICATION)
                                    .and()
                                .czse_keyword(d -> d.isUseSparse(),Keywords.Key.SPARSE)
                                .czse_keyword(d -> d.isUseHidden(),Keywords.Key.HIDDEN)
                                .and()
                            .and()
                        .czse(d -> d.getUseAdd() != null && d.getMaskFunction() != null)
                            .description("masked")
                            .sub()
                                .czse_keyword(d -> d.getUseAdd(),Keywords.ADD)
                                .czse_keyword(d -> !d.getUseAdd(),Keywords.DROP)
                                .syntax_required()
                                .and()
                            .sub_keyword(Keywords.Key.MASKED)
                            .sub()
                                .optional(d -> d.getMaskFunction() == null)
                                .sub_keyword(Keywords.WITH)
                                .sub_keyword(Other.GROUP_START)
                                .sub_keyword(Keywords.FUNCTION)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("'mask_function'")
                                    .scope(d -> d.getMaskFunction())
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .syntax_required()
                        .syntax_context_indentation()
                        .syntax_sub_line()
                        .and()
                    .sub()
                        .description("with")
                        .optional(d -> d.getUseOnline() == null && d.getUseCheck() == null)
                        .czse(d -> d.getUseOnline() != null)
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub_keyword(Keywords.Key.ONLINE)
                            .sub_keyword(Assignment.ASSIGNMENT)
                            .sub()
                                .czse_keyword(d -> d.getUseOnline(),Keywords.ON)
                                .czse_keyword(d -> !d.getUseOnline(), Keywords.OFF)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .czse(d -> d.getUseCheck() != null)
                            .sub_keyword(Keywords.WITH)
                            .sub()
                                .czse_keyword(d -> d.getUseCheck(),Keywords.CHECK)
                                .czse_keyword(d -> !d.getUseCheck(), Keywords.NOCHECK)
                                .and()
                            .and()
                        .syntax_sub_line()
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

    public static class AlterColumnTypeConverter {
        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType>()
                        .description("data type")
                        .sub("type_schema_name")
                            .optional(d -> d.getTypeSchemaName() == null)
                            .scope(d -> d.getTypeSchemaName())
                            .and()
                        .sub("type_name")
                            .scope(d ->d.getName())
                            .and()
                        .sub()
                            .description("precision and scale")
                            .optional(d -> d.getPrecision() == null || d.isUseMax() || d.getXmlSchemaCollection() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .sub()
                                    .czse(d -> d.getPrecision() != null)
                                        .sub("precision")
                                            .scope(d -> d.getPrecision())
                                            .and()
                                        .sub()
                                            .optional(d -> d.getScale() == null)
                                            .sub_keyword(Other.DELIMITER)
                                            .sub("scale")
                                                .scope(d -> d.getScale())
                                                .and()
                                            .and()
                                        .and()
                                    .czse(d -> d.isUseMax(),"max")
                                        .scope(d -> "max")
                                        .and()
                                    .czse(d -> d.getXmlSchemaCollection() != null, "xml_schema_collection")
                                        .scope(d -> d.getXmlSchemaCollection().toString())
                                        .and()
                                    .syntax_required()
                                    .syntax_context_indentation()
                                    .syntax_sub_line()
                                    .and()
//                                .syntax_line()
                                .syntax_context_indentation()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .syntax_line()
                            .syntax_indentation_right()
                            .and()
                        .build();
    }

}
