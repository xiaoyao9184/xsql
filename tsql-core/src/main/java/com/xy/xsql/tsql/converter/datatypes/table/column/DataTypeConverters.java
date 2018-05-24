package com.xy.xsql.tsql.converter.datatypes.table.column;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.Xml;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.MaxLength;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Precision;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Scale;
import com.xy.xsql.tsql.model.datatypes.precision_scale_length.Schema;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.Other;

/**
 * TODO
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
                            .optional(d -> d instanceof Schema)
                            .sub("type_schema_name")
                                .scope(d -> ((Schema)d).schema())
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("type_name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d -> d instanceof Precision
                                    && !((Precision)d).precisionUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub("precision")
                                .scope(d -> ((Precision)d).precision())
                                .and()
                            .sub()
                                .description("scale")
                                .optional(d -> d instanceof Scale
                                    && !((Scale)d).scaleUsed())
                                .sub_keyword(Other.DELIMITER)
                                .sub("scale")
                                    .scope(d -> ((Scale)d).scale())
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
                            .optional(d -> d instanceof Schema)
                            .sub("type_schema_name")
                                .scope(d -> ((Schema)d).schema())
                                .and()
                            .sub_keyword(Other.POINT)
                            .and()
                        .sub("type_name")
                            .scope(d -> d.name())
                            .and()
                        .sub()
                            .optional(d ->  d instanceof Precision
                                    && !((Precision)d).precisionUsed())
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .czse(d -> d instanceof Precision
                                    && !((Precision)d).precisionUsed())
                                    .sub("precision")
                                        .scope(d -> ((Precision)d).precision())
                                        .and()
                                    .sub()
                                        .description("scale")
                                        .optional(d -> d instanceof Scale
                                                && !((Scale)d).scaleUsed())
                                        .sub_keyword(Other.DELIMITER)
                                        .sub("scale")
                                            .scope(d -> ((Scale)d).scale())
                                            .and()
                                        .and()
                                    .and()
                                .czse(d -> d instanceof MaxLength
                                        && !((MaxLength)d).maxUsed())
                                    .sub("max")
                                        .scope(d -> "max")
                                        .and()
                                    .and()
                                .czse(d -> d instanceof Xml)
                                    .sub()
                                        .syntax_required()
                                        .czse_keyword(d -> !((Xml)d).isUseDocument(),Keywords.Key.CONTENT)
                                        .czse_keyword(d -> ((Xml)d).isUseDocument(),Keywords.Key.DOCUMENT)
                                        .and()
                                    .sub("xml_schema_collection")
                                        .scope(d -> ((Xml)d).getXmlSchemaCollection())
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
