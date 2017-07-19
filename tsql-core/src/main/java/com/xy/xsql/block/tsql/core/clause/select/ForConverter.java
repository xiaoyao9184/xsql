package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.For;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ForConverter
        implements ModelMetaBlockConverter<For> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,For>()
                .overall("FOR")
                .sub_keyword(Keywords.FOR)
                .sub()
                    .description("browse/xml/json")
                    .czse_keyword(For::isUseBrowse, Keywords.BROWSE)
                    .czse(d -> d.getXml() != null, "XML")
                        .ref(XmlConverter.class)
                        .scope(For::getXml)
                        .and()
                    .czse(d -> d.getJson() != null, "JSON")
                        .ref(JsonConverter.class)
                        .scope(For::getJson)
                        .and()
                    .syntax_required()
                    .and()
                .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class XmlConverter
            implements ModelMetaBlockConverter<For.Xml> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,For.Xml>()
                        .overall("XML")
                        .sub_keyword(Keywords.Key.XML)
                        .sub()
                            .syntax_required()
                            .czse(d -> d.isUseRaw() ||
                                    d.isUseAuto()
                            )
                                .sub()
                                    .description("raw/auto")
                                    .syntax_required()
                                    .czse(For.Xml::isUseRaw)
                                        .description("raw")
                                        .sub_keyword(Keywords.Key.RAW)
                                        .sub()
                                            .description("ElementName")
                                            .optional(d -> d.getElementName() == null)
                                            .sub_keyword(Other.GROUP_START)
                                            .sub("'ElementName'")
                                                .scope(For.Xml::getElementName)
                                                .and()
                                            .sub_keyword(Other.GROUP_END)
                                            .and()
                                        .and()
                                    .czse_keyword(For.Xml::isUseAuto,Keywords.Key.AUTO)
                                    .and()
                                .sub()
                                    .optional(d -> !(
                                        d.isUseBinaryBase64() ||
                                        d.isUseType() ||
                                        d.isUseRoot() ||
                                        d.isUseXmlData() ||
                                        d.isUseXmlSchema() ||
                                        d.isUseElementsXsinil() ||
                                        d.isUseElementsAbsent()
                                        ))
                                    .sub("CommonDirectivesForXML")
                                        .ref(CommonDirectivesForXMLConverter.meta)
                                        .scope(d -> d)
                                        .and()
                                    .sub()
                                        .description("xmldata/xmlschema")
                                        .optional(d -> !(
                                            d.isUseXmlData() ||
                                            d.isUseXmlSchema()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub()
                                            .syntax_required()
                                            .czse_keyword(For.Xml::isUseXmlData, Keywords.Key.XMLDATA)
                                            .czse(For.Xml::isUseXmlSchema)
                                                .sub_keyword(Keywords.Key.XMLSCHEMA)
                                                .sub()
                                                    .description("( 'TargetNameSpaceURI' )")
                                                    .optional(d -> d.getTargetNameSpaceURI() == null)
                                                    .sub_keyword(Other.GROUP_START)
                                                    .sub("'TargetNameSpaceURI'")
                                                        .scope(For.Xml::getTargetNameSpaceURI)
                                                        .and()
                                                    .sub_keyword(Other.GROUP_END)
                                                    .and()
                                                .and()
                                            .and()
                                        .and()
                                    .sub()
                                        .description("elememts xsinil/absent")
                                        .optional(d -> !(
                                            d.isUseElementsXsinil() ||
                                            d.isUseElementsAbsent()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.ELEMENTS)
                                        .sub()
                                            .description("xsinil/absent")
                                            .optional(d -> !(
                                                d.isUseElementsXsinil() ||
                                                d.isUseElementsAbsent()
                                                ))
                                            .czse_keyword(For.Xml::isUseElementsXsinil,Keywords.Key.XSINIL)
                                            .czse_keyword(For.Xml::isUseElementsAbsent,Keywords.Key.ABSENT)
                                            .and()
                                        .and()
                                    .syntax_line()
                                    .syntax_context_indentation()
                                    .syntax_sub_line()
                                    .and()
                                .and()
                            .czse(For.Xml::isUseExplicit)
                                .description("EXPLICIT")
                                .sub_keyword(Keywords.Key.EXPLICIT)
                                .sub()
                                    .optional(d -> !(
                                        d.isUseBinaryBase64() ||
                                        d.isUseType() ||
                                        d.isUseRoot() ||
                                        d.isUseXmlData()
                                        ))
                                    .sub("CommonDirectivesForXML")
                                        .ref(CommonDirectivesForXMLConverter.meta)
                                        .scope(d -> d)
                                        .and()
                                    .sub()
                                        .description(", XMLDATA")
                                        .optional(d -> !(
                                            d.isUseXmlData()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.XMLDATA)
                                        .and()
                                    .syntax_context_indentation()
                                    .syntax_sub_line()
                                    .and()
                                .syntax_sub_line()
                                .and()
                            .czse(For.Xml::isUsePath)
                                .description("-PATH")
                                .sub_keyword(Keywords.Key.PATH)
                                .sub()
                                    .description("( 'ElementName' )")
                                    .optional(d -> d.getElementName() == null)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("'ElementName'")
                                        .scope(For.Xml::getElementName)
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .and()
                                .sub()
                                    .optional(d -> !(
                                        d.isUseBinaryBase64() ||
                                        d.isUseType() ||
                                        d.isUseRoot() ||
                                        d.isUseElementsXsinil() ||
                                        d.isUseElementsAbsent()
                                        ))
                                    .sub("CommonDirectivesForXML")
                                        .ref(CommonDirectivesForXMLConverter.meta)
                                        .scope(d -> d)
                                        .and()
                                    .sub()
                                        .description("elememts xsinil/absent")
                                        .optional(d -> !(
                                            d.isUseElementsXsinil() ||
                                            d.isUseElementsAbsent()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.ELEMENTS)
                                        .sub()
                                            .description("xsinil/absent")
                                            .optional(d -> !(
                                                d.isUseElementsXsinil() ||
                                                d.isUseElementsAbsent()
                                                ))
                                            .czse_keyword(For.Xml::isUseElementsXsinil,Keywords.Key.XSINIL)
                                            .czse_keyword(For.Xml::isUseElementsAbsent,Keywords.Key.ABSENT)
                                            .and()
                                        .and()
                                    .syntax_line()
                                    .syntax_context_indentation()
                                    .syntax_sub_line()
                                    .and()
                                .and()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .and()
                        .syntax_sub_line()
                        .sub_format_line_empty_delimiter()
                        .sub_format_indentation_right()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class CommonDirectivesForXMLConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,For.Xml>()
                        .overall("CommonDirectivesForXML")
                        .sub()
                            .optional(d -> !d.isUseBinaryBase64())
                            .sub_keyword(Other.DELIMITER)
                            .sub_keyword(Keywords.Key.BINARY)
                            .sub_keyword(Keywords.Key.BASE64)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseType())
                            .sub_keyword(Other.DELIMITER)
                            .sub_keyword(Keywords.Key.TYPE)
                            .and()
                        .sub()
                            .optional(d -> !d.isUseRoot())
                            .sub_keyword(Other.DELIMITER)
                            .sub_keyword(Keywords.Key.ROOT)
                            .sub()
                                .optional(d -> d.getRootName() == null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("'RootName'")
                                    .scope(For.Xml::getRootName)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }


    public static class JsonConverter
            implements ModelMetaBlockConverter<For.Json> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,For.Json>()
                        .sub_keyword(Keywords.Key.JSON)
                        .sub()
                            .sub()
                                .description("auto/path")
                                .czse_keyword(d -> !d.isUsePath(),Keywords.Key.AUTO)
                                .czse_keyword(For.Json::isUsePath,Keywords.Key.PATH)
                                .syntax_required()
                                .and()
                            .sub()
                                .optional(d -> !(
                                        d.isUseRoot() ||
                                        d.isUseIncludeNullValue() ||
                                        d.isUseWithoutArrayWrapper()
                                ))
                                .sub()
                                    .description(", ROOT [ ( 'RootName' ) ]")
                                    .optional(d -> !d.isUseRoot())
                                    .sub_keyword(Other.DELIMITER)
                                    .sub_keyword(Keywords.Key.ROOT)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("'RootName'")
                                        .scope(For.Json::getRootName)
                                        .and()
                                    .sub_keyword(Other.GROUP_END)
                                    .and()
                                .sub()
                                    .description(", INCLUDE_NULL_VALUES")
                                    .optional(d -> !d.isUseIncludeNullValue())
                                    .sub_keyword(Other.DELIMITER)
                                    .sub_keyword(Keywords.Key.INCLUDE_NULL_VALUES)
                                    .and()
                                .sub()
                                    .description(", WITHOUT_ARRAY_WRAPPER")
                                    .optional(d -> !d.isUseWithoutArrayWrapper())
                                    .sub_keyword(Other.DELIMITER)
                                    .sub_keyword(Keywords.Key.WITHOUT_ARRAY_WRAPPER)
                                    .and()
                                .syntax_context_indentation()
                                .syntax_sub_line()
                                .and()
                            .syntax_required()
                            .syntax_context_indentation()
                            .syntax_sub_line()
                            .format_line()
                            .and()
                        .syntax_sub_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
    }

    }

}
