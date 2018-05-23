package com.xy.xsql.tsql.converter.datatypes;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.Xml;
import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class XmlConverter
            implements ModelMetaBlockConverter<Xml> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Xml>()
                    .name("xml")
                    .description("xml ( [ CONTENT | DOCUMENT ] xml_schema_collection )")
                    .sub()
                        .scope(c -> c.name())
                        .and()
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(c -> !c.isUseContent() && !c.isUseDocument())
                        .czse_keyword(c -> c.isUseContent(),Keywords.Key.CONTENT)
                        .czse_keyword(c -> c.isUseDocument(),Keywords.Key.DOCUMENT)
                        .and()
                    .sub("xml_schema_collection")
                        .scope(c -> c.getXmlSchemaCollection())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
