package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Configurator;
import com.xy.xsql.tsql.model.queries.select.For;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

import java.util.Arrays;

import static com.xy.xsql.core.FiledBuilder.initSet;

/**
 * ForBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class ForBuilder<ParentBuilder>
        extends CodeTreeBuilder<ForBuilder<ParentBuilder>,ParentBuilder,For> {

    public ForBuilder() {
        super(new For());
    }

    public ForBuilder(For forClause) {
        super(forClause);
    }


    public ForBuilder<ParentBuilder> withBrowse(){
        target.setUseBrowse(true);
        return this;
    }

    public XmlBuilder<ForBuilder<ParentBuilder>> withXml(){
        return new XmlBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Xml::new,
                        target::getXml,
                        target::setXml))
                .in(this);
    }

    public JsonBuilder<ForBuilder<ParentBuilder>> withJson(){
        return new JsonBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Json::new,
                        target::getJson,
                        target::setJson))
                .in(this);
    }

    public ForBuilder<ParentBuilder> withXml(For.Xml xml){
        target.setXml(xml);
        return this;
    }

    public ForBuilder<ParentBuilder> withJson(For.Json json){
        target.setJson(json);
        return this;
    }


    /**
     * Quick set
     * @return
     */
    public ForBuilder<ParentBuilder> $BROWSE(){
        return withBrowse();
    }

    /**
     * Quick set
     * @return
     */
    public ForBuilder<ParentBuilder> $XML(XmlConfigurator... items){
        return withXml().$(items).and();
    }

    /**
     * Quick set
     * @return
     */
    public ForBuilder<ParentBuilder> $JSON(JsonConfigurator... items){
        return withJson().$(items).and();
    }




    /**
     * XmlBuilder
     * @param <ParentBuilder>
     */
    public static class XmlBuilder<ParentBuilder>
            extends CodeTreeBuilder<XmlBuilder<ParentBuilder>,ParentBuilder,For.Xml> {

        public XmlBuilder(For.Xml tar) {
            super(tar);
        }

        public XmlBuilder<ParentBuilder> withRaw(){
            target.setUseRaw(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRaw(String elementName){
            target.setUseRaw(true);
            if(elementName != null){
                target.setRootName(new StringConstant(elementName).withQuote());
            }
            return this;
        }

        public XmlBuilder<ParentBuilder> withAuto(){
            target.setUseAuto(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withExplicit(){
            target.setUseExplicit(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withPath(){
            target.setUsePath(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withPath(String elementName){
            target.setUsePath(true);
            if(elementName != null){
                target.setRootName(new StringConstant(elementName).withQuote());
            }
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlData(){
            target.setUseXmlData(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlSchema(){
            target.setUseXmlSchema(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlSchema(String targetNameSpaceURI){
            target.setUseXmlSchema(true);
            if(targetNameSpaceURI != null){
                target.setRootName(new StringConstant(targetNameSpaceURI).withQuote());
            }
            return this;
        }

        public XmlBuilder<ParentBuilder> withElements(){
            target.setUseElements(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withElementsAbsent(){
            target.setUseElementsAbsent(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withElementsXsinil(){
            target.setUseElementsXsinil(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withBinaryBase64(){
            target.setUseBinaryBase64(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withType(){
            target.setUseType(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRoot(){
            target.setUseRoot(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRoot(String rootName){
            target.setUseRoot(true);
            if(rootName != null){
                target.setRootName(new StringConstant(rootName).withQuote());
            }
            return this;
        }


        /**
         * Quick config
         * @param items
         * @return
         */
        public XmlBuilder<ParentBuilder> $(XmlConfigurator... items){
            Arrays.stream(items)
                    .forEach(item -> item.config(target));
            return this;
        }

    }

    /**
     * JsonBuilder
     * @param <ParentBuilder>
     */
    public static class JsonBuilder<ParentBuilder>
            extends CodeTreeBuilder<JsonBuilder<ParentBuilder>,ParentBuilder,For.Json> {

        public JsonBuilder(For.Json tar) {
            super(tar);
        }

        public JsonBuilder<ParentBuilder> withPath(){
            target.setUsePath(true);
            return this;
        }

        public JsonBuilder<ParentBuilder> withRoot(String rootName){
            target.setUseRoot(true);
            if(rootName != null){
                target.setRootName(new StringConstant(rootName).withQuote());
            }
            return this;
        }

        public JsonBuilder<ParentBuilder> withIncludeNullValue(){
            target.setUseIncludeNullValue(true);
            return this;
        }

        public JsonBuilder<ParentBuilder> withWithoutArrayWrapper(){
            target.setUseWithoutArrayWrapper(true);
            return this;
        }


        /**
         * Quick config
         * @param items
         * @return
         */
        public JsonBuilder<ParentBuilder> $(JsonConfigurator... items){
            Arrays.stream(items)
                    .forEach(item -> item.config(target));
            return this;
        }

    }


    /**
     * Quick config
     */
    public interface XmlConfigurator
            extends Configurator<For.Xml> {

        static XmlConfigurator RAW(){
            return xml -> xml.setUseRaw(true);
        }

        static XmlConfigurator RAW(String elementName){
            return xml -> {
                xml.setUseRaw(true);
                if(elementName != null){
                    xml.setElementName(new StringConstant(elementName).withQuote());
                }
            };
        }

        static XmlConfigurator AUTO(){
            return xml -> xml.setUseAuto(true);
        }

        static XmlConfigurator EXPLICIT(){
            return xml -> xml.setUseExplicit(true);
        }

        static XmlConfigurator PATH(){
            return xml -> xml.setUsePath(true);
        }

        static XmlConfigurator PATH(String elementName){
            return xml -> {
                xml.setUsePath(true);
                if(elementName != null){
                    xml.setElementName(new StringConstant(elementName).withQuote());
                }
            };
        }

        static XmlConfigurator XMLDATA(){
            return xml -> xml.setUseXmlData(true);
        }

        static XmlConfigurator XMLSCHEMA(){
            return xml -> xml.setUseXmlSchema(true);
        }

        static XmlConfigurator XMLSCHEMA(String targetNameSpaceURI){
            return xml -> {
                xml.setUseXmlSchema(true);
                if(targetNameSpaceURI != null){
                    xml.setRootName(new StringConstant(targetNameSpaceURI).withQuote());
                }
            };
        }

        static XmlConfigurator ELEMENTS_XSINIL(){
            return xml -> xml.setUseElementsXsinil(true);
        }

        static XmlConfigurator ELEMENTS_ABSENT(){
            return xml -> xml.setUseElementsAbsent(true);
        }

        static XmlConfigurator BINARY_BASE64(){
            return xml -> xml.setUseBinaryBase64(true);
        }

        static XmlConfigurator TYPE(){
            return xml -> xml.setUseType(true);
        }

        static XmlConfigurator ROOT(){
            return xml -> xml.setUseRoot(true);
        }

        static XmlConfigurator ROOT(String rootName){
            return xml -> {
                xml.setUseRoot(true);
                if(rootName != null){
                    xml.setRootName(new StringConstant(rootName).withQuote());
                }
            };
        }
    }

    /**
     * Quick config
     */
    public interface JsonConfigurator
            extends Configurator<For.Json> {

        static JsonConfigurator AUTO(){
            return json -> json.setUsePath(false);
        }

        static JsonConfigurator PATH(){
            return json -> json.setUsePath(true);
        }

        static XmlConfigurator ROOT(){
            return json -> json.setUseRoot(true);
        }

        static JsonConfigurator ROOT(String rootName){
            return json -> {
                json.setUseRoot(true);
                if(rootName != null){
                    json.setRootName(new StringConstant(rootName).withQuote());
                }
            };
        }

        static JsonConfigurator INCLUDE_NULL_VALUES(){
            return json -> json.setUseIncludeNullValue(true);
        }

        static JsonConfigurator WITHOUT_ARRAY_WRAPPER(){
            return json -> json.setUseWithoutArrayWrapper(true);
        }

    }

}
