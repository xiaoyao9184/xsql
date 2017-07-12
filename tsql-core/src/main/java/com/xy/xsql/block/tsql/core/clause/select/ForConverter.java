package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.ModelMetaBlockConverter;
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
                    .description("{ BROWSE | <XML> | <JSON>}")
                    .required()
                    .czse(For::isUseBrowse)
                        .keyword(Keywords.BROWSE)
                        .and()
                    .czse(d -> d.getXml() != null, "XML")
                        .ref(XmlConverter.class)
                        .data(For::getXml)
                        .and()
                    .czse(d -> d.getJson() != null, "JSON")
                        .ref(JsonConverter.class)
                        .data(For::getJson)
                        .and()
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
                            .required()
                            .czse(d -> d.isUseRaw() ||
                                    d.isUseAuto()
                            )
                                .sub()
                                    .description("RAW | AUTO")
                                    .required()
                                    .czse(For.Xml::isUseRaw)
                                        .description("RAW")
                                        .sub_keyword(Keywords.Key.RAW)
                                        .sub()
                                            .description("[ ( 'ElementName' ) ]")
                                            .optional(d -> d.getElementName() == null)
                                            .sub_keyword(Other.GROUP_START)
                                            .sub("'ElementName'")
                                                .data(For.Xml::getElementName)
                                                .and()
                                            .sub_keyword(Other.GROUP_END)
                                            .and()
                                        .and()
                                    .czse(For.Xml::isUseAuto)
                                        .description("AUTO")
                                        .keyword(Keywords.Key.AUTO)
                                        .and()
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
                                        .ref(CommonDirectivesForXMLConverter.class)
                                        .data(d -> d)
                                        .and()
                                    .sub()
                                        .description("[ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]")
                                        .optional(d -> !(
                                            d.isUseXmlData() ||
                                            d.isUseXmlSchema()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub()
                                            .required()
                                            .czse(For.Xml::isUseXmlData)
                                                .keyword(Keywords.Key.XMLDATA)
                                                .and()
                                            .czse(For.Xml::isUseXmlSchema)
                                                .sub_keyword(Keywords.Key.XMLSCHEMA)
                                                .sub()
                                                    .description("( 'TargetNameSpaceURI' )")
                                                    .optional(d -> d.getTargetNameSpaceURI() == null)
                                                    .sub_keyword(Other.GROUP_START)
                                                    .sub("'TargetNameSpaceURI'")
                                                        .data(For.Xml::getTargetNameSpaceURI)
                                                        .and()
                                                    .sub_keyword(Other.GROUP_END)
                                                    .and()
                                                .and()
                                            .and()
                                        .and()
                                    .sub()
                                        .description("[ , ELEMENTS [ XSINIL | ABSENT ]")
                                        .optional(d -> !(
                                            d.isUseElementsXsinil() ||
                                            d.isUseElementsAbsent()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.ELEMENTS)
                                        .sub()
                                            .description("XSINIL | ABSENT")
                                            .optional(d -> !(
                                                d.isUseElementsXsinil() ||
                                                d.isUseElementsAbsent()
                                                ))
                                            .czse(For.Xml::isUseElementsXsinil)
                                                .keyword(Keywords.Key.XSINIL)
                                                .and()
                                            .czse(For.Xml::isUseElementsAbsent)
                                                .keyword(Keywords.Key.ABSENT)
                                                .and()
                                            .and()
                                        .and()
                                    .startNewline()
                                    .headFootTakeLine()
                                    .subTakeLine()
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
                                        .ref(CommonDirectivesForXMLConverter.class)
                                        .data(d -> d)
                                        .and()
                                    .sub()
                                        .description(", XMLDATA")
                                        .optional(d -> !(
                                            d.isUseXmlData()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.XMLDATA)
                                        .and()
                                    .headFootTakeLine()
                                    .subTakeLine()
                                    .and()
                                .subTakeLine()
                                .and()
                            .czse(For.Xml::isUsePath)
                                .description("-PATH")
                                .sub_keyword(Keywords.Key.PATH)
                                .sub()
                                    .description("( 'ElementName' )")
                                    .optional(d -> d.getElementName() == null)
                                    .sub_keyword(Other.GROUP_START)
                                    .sub("'ElementName'")
                                        .data(For.Xml::getElementName)
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
                                        .ref(CommonDirectivesForXMLConverter.class)
                                        .data(d -> d)
                                        .and()
                                    .sub()
                                        .description(", ELEMENTS [ XSINIL | ABSENT ]")
                                        .optional(d -> !(
                                            d.isUseElementsXsinil() ||
                                            d.isUseElementsAbsent()
                                            ))
                                        .sub_keyword(Other.DELIMITER)
                                        .sub_keyword(Keywords.Key.ELEMENTS)
                                        .sub()
                                            .description("XSINIL | ABSENT")
                                            .optional(d -> !(
                                                d.isUseElementsXsinil() ||
                                                d.isUseElementsAbsent()
                                                ))
                                            .czse(For.Xml::isUseElementsXsinil)
                                                .keyword(Keywords.Key.XSINIL)
                                                .and()
                                            .czse(For.Xml::isUseElementsAbsent)
                                                .keyword(Keywords.Key.ABSENT)
                                                .and()
                                            .and()
                                        .and()
                                    .startNewline()
                                    .headFootTakeLine()
                                    .subTakeLine()
                                    .and()
                                .and()
                            .headFootTakeLine()
                            .subTakeLine()
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class CommonDirectivesForXMLConverter
            implements ModelMetaBlockConverter<For.Xml> {

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
                                    .data(For.Xml::getRootName)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
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
                            .required()
                            .sub()
                                .description("AUTO | PATH")
                                .required()
                                .czse(d -> !d.isUsePath())
                                    .keyword(Keywords.Key.AUTO)
                                    .and()
                                .czse(For.Json::isUsePath)
                                    .keyword(Keywords.Key.PATH)
                                    .and()
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
                                        .data(For.Json::getRootName)
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
                                .subTakeLine()
                                .headFootTakeLine()
                                .and()
                            .subTakeLine()
                            .headFootTakeLine()
                            .and()
                        .subTakeLine()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
    }

    }

}
