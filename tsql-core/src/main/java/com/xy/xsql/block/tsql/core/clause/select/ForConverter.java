package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.clause.select.For;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ForConverter
        implements BlockConverter<For> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,For> builder =
            new ReferenceBlockBuilder<Void,For>()
                .overall("FOR")
                .sub_keyword(Keywords.FOR)
                .sub()
                    .description("{ BROWSE | <XML> | <JSON>}")
                    .required()
                    .oneOf()
                        .keyword(Keywords.BROWSE)
                        .and()
                    .oneOf("XML")
                        .ref(For.Xml.class)
                        .data(For::getXml)
                        .and()
                    .oneOf("JSON")
                        .ref(For.Json.class)
                        .data(For::getJson)
                        .and()
                    .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(For aFor) {
        return builder
                .data(aFor)
                .build();
    }


    public static class XmlConverter
            implements BlockConverter<For.Xml> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,For.Xml> builder =
                new ReferenceBlockBuilder<Void,For.Xml>()
                        .overall("XML")
                        .sub_keyword(Keywords.Key.XML)
                        .sub()
                            .required()
                            .oneOf()
                                .sub()
                                    .description("RAW | AUTO")
                                    .required()
                                    .oneOf()
                                        .description("RAW")
                                        .filter(For.Xml::isUseRaw)
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
                                    .oneOf()
                                        .description("AUTO")
                                        .filter(For.Xml::isUseAuto)
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
                                            .oneOf()
                                                .filter(d -> !d.isUseXmlData())
                                                .keyword(Keywords.Key.XMLDATA)
                                                .and()
                                            .oneOf()
                                                .filter(d -> !d.isUseXmlSchema())
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
                                            .oneOf()
                                                .filter(For.Xml::isUseElementsXsinil)
                                                .keyword(Keywords.Key.XSINIL)
                                                .and()
                                            .oneOf()
                                                .filter(For.Xml::isUseElementsAbsent)
                                                .keyword(Keywords.Key.ABSENT)
                                                .and()
                                            .and()
                                        .and()
                                    .startNewline()
                                    .headFootTakeLine()
                                    .subTakeLine()
                                    .and()
                                .and()
                            .oneOf()
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
                            .oneOf()
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
                                            .oneOf()
                                                .filter(For.Xml::isUseElementsXsinil)
                                                .keyword(Keywords.Key.XSINIL)
                                                .and()
                                            .oneOf()
                                                .filter(For.Xml::isUseElementsAbsent)
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
                        .subTakeLine();

        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(For.Xml xml) {
            return builder
                    .data(xml)
                    .build();
        }
    }


    public static class CommonDirectivesForXMLConverter
            implements BlockConverter<For.Xml> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,For.Xml> builder =
                new ReferenceBlockBuilder<Void,For.Xml>()
                        .overall("CommonDirectivesForXML")
                        .sub()
                            .optional(For.Xml::isUseBinaryBase64)
                            .sub_keyword(Other.DELIMITER)
                            .sub_keyword(Keywords.Key.BINARY)
                            .sub_keyword(Keywords.Key.BASE64)
                            .and()
                        .sub()
                            .optional(For.Xml::isUseType)
                            .sub_keyword(Other.DELIMITER)
                            .sub_keyword(Keywords.Key.TYPE)
                            .and()
                        .sub()
                            .optional(For.Xml::isUseRoot)
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
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(For.Xml xml) {
            return builder
                    .data(xml)
                    .build();
        }
    }


    public static class JsonConverter
            implements BlockConverter<For.Json> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,For.Json> builder =
                new ReferenceBlockBuilder<Void,For.Json>()
                        .sub_keyword(Keywords.Key.JSON)
                        .sub()
                            .required()
                            .sub()
                                .description("AUTO | PATH")
                                .required()
                                .oneOf()
                                    .keyword(Keywords.Key.AUTO)
                                    .and()
                                .oneOf()
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
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(For.Json json) {
            return builder
                    .data(json)
                    .build();
        }
    }

}
