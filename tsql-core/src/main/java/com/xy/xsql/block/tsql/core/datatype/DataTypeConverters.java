package com.xy.xsql.block.tsql.core.datatype;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.table.column.DataType;
import com.xy.xsql.tsql.model.elements.Other;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class DataTypeConverters {

    public static class Base {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType>()
                        .overall("data type")
                        .sub()
                            .description("schema name")
                            .optional(d -> d.getTypeSchemaName() == null)
                            .sub("type_schema_name")
                                .scope(d -> d.getTypeSchemaName())
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("type_name")
                            .scope(d -> d.getName())
                            .and()
                        .sub()
                            .optional(d -> d.getPrecision() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub("precision")
                                .scope(d -> d.getPrecision())
                                .and()
                            .sub()
                                .description("scale")
                                .optional(d -> d.getScale() == null)
                                .sub_keyword(Other.DELIMITER)
                                .sub("scale")
                                    .scope(d -> d.getScale())
                                    .and()
                                .and()
                            .and()
                        .build();
        // @formatter:on

    }

    public static class Full {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,DataType>()
                        .overall("data type")
                        .sub()
                            .description("schema name")
                            .optional(d -> d.getTypeSchemaName() == null)
                            .sub("type_schema_name")
                                .scope(d -> d.getTypeSchemaName())
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("type_name")
                            .scope(d -> d.getName())
                            .and()
                        .sub()
                            .optional(d -> d.getPrecision() == null)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .czse(d -> d.getPrecision() != null)
                                    .sub("precision")
                                        .scope(d -> d.getPrecision())
                                        .and()
                                    .sub()
                                        .description("scale")
                                        .optional(d -> d.getScale() == null)
                                        .sub_keyword(Other.DELIMITER)
                                        .sub("scale")
                                            .scope(d -> d.getScale())
                                            .and()
                                        .and()
                                    .and()
                                .czse(d -> d.isUseMax())
                                    .sub("max")
                                        .scope(d -> "max")
                                        .and()
                                    .and()
                                .czse(d -> !d.isUseMax())
                                    .sub()
                                        .syntax_required()
                                        .czse_keyword(d -> !d.isUseDocument(),Keywords.Key.CONTENT)
                                        .czse_keyword(d -> d.isUseDocument(),Keywords.Key.DOCUMENT)
                                        .and()
                                    .sub("xml_schema_collection")
                                        .scope(d -> d.getXmlSchemaCollection())
                                        .and()
                                    .and()
                                .syntax_context_indentation()
                                .syntax_sub_line()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .syntax_line()
                            .syntax_indentation_right()
                            .and()
                        .build();
        // @formatter:on

    }

}
