package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Configurator;
import com.xy.xsql.tsql.model.queries.select.For;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

import java.util.Arrays;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * ForBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess","UnusedReturnValue"})
public class ForBuilder<ParentBuilder>
        extends CodeTreeBuilder<ForBuilder<ParentBuilder>,ParentBuilder,For> {

    public ForBuilder() {
        super(new For());
    }

    public ForBuilder(For forClause) {
        super(forClause);
    }


    /**
     * set
     * @return THIS
     */
    public ForBuilder<ParentBuilder> withBrowse(){
        target.setUseBrowse(true);
        return this;
    }

    /**
     * set
     * @param xml Xml
     * @return THIS
     */
    public ForBuilder<ParentBuilder> withXml(For.Xml xml){
        target.setXml(xml);
        return this;
    }

    /**
     * in
     * @return XmlBuilder
     */
    public XmlBuilder<ForBuilder<ParentBuilder>> withXml(){
        return new XmlBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Xml::new,
                        target::getXml,
                        target::setXml))
                .in(this);
    }

    /**
     * set
     * @param json Json
     * @return THIS
     */
    public ForBuilder<ParentBuilder> withJson(For.Json json){
        target.setJson(json);
        return this;
    }

    /**
     * in
     * @return JsonBuilder
     */
    public JsonBuilder<ForBuilder<ParentBuilder>> withJson(){
        return new JsonBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Json::new,
                        target::getJson,
                        target::setJson))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public ForBuilder<ParentBuilder> $Browse(){
        return withBrowse();
    }

    /**
     * Quick set
     * @return THIS
     */
    public ForBuilder<ParentBuilder> $Xml(XmlConfigurator... items){
        return withXml().$(items).and();
    }

    /**
     * Quick set
     * @return THIS
     */
    public ForBuilder<ParentBuilder> $Json(JsonConfigurator... items){
        return withJson().$(items).and();
    }




    /**
     * XmlBuilder
     * @param <ParentBuilder>
     */
    public static class XmlBuilder<ParentBuilder>
            extends CodeTreeBuilder<XmlBuilder<ParentBuilder>,ParentBuilder,For.Xml> {

        public XmlBuilder() {
            super(new For.Xml());
        }

        public XmlBuilder(For.Xml tar) {
            super(tar);
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withRaw(){
            target.setUseRaw(true);
            return this;
        }

        /**
         * set
         * @param elementName element name
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withRaw(String elementName){
            target.setUseRaw(true);
            if(elementName != null){
                target.setRootName(c_string(elementName));
            }
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withAuto(){
            target.setUseAuto(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withExplicit(){
            target.setUseExplicit(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withPath(){
            target.setUsePath(true);
            return this;
        }

        /**
         * set
         * @param elementName element name
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withPath(String elementName){
            target.setUsePath(true);
            if(elementName != null){
                target.setRootName(c_string(elementName));
            }
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withXmlData(){
            target.setUseXmlData(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withXmlSchema(){
            target.setUseXmlSchema(true);
            return this;
        }

        /**
         * set
         * @param targetNameSpaceURI target namespace URI
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withXmlSchema(String targetNameSpaceURI){
            target.setUseXmlSchema(true);
            if(targetNameSpaceURI != null){
                target.setRootName(c_string(targetNameSpaceURI));
            }
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withElements(){
            target.setUseElements(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withElementsAbsent(){
            target.setUseElementsAbsent(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withElementsXsinil(){
            target.setUseElementsXsinil(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withBinaryBase64(){
            target.setUseBinaryBase64(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withType(){
            target.setUseType(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withRoot(){
            target.setUseRoot(true);
            return this;
        }

        /**
         * set
         * @param rootName root name
         * @return THIS
         */
        public XmlBuilder<ParentBuilder> withRoot(String rootName){
            target.setUseRoot(true);
            if(rootName != null){
                target.setRootName(c_string(rootName));
            }
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick config
         * @param items XmlConfigurator
         * @return THIS
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

        public JsonBuilder() {
            super(new For.Json());
        }

        public JsonBuilder(For.Json tar) {
            super(tar);
        }

        /**
         * set
         * @return THIS
         */
        public JsonBuilder<ParentBuilder> withPath(){
            target.setUsePath(true);
            return this;
        }

        /**
         * set
         * @param rootName root name
         * @return THIS
         */
        public JsonBuilder<ParentBuilder> withRoot(String rootName){
            target.setUseRoot(true);
            if(rootName != null){
                target.setRootName(c_string(rootName));
            }
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public JsonBuilder<ParentBuilder> withIncludeNullValue(){
            target.setUseIncludeNullValue(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public JsonBuilder<ParentBuilder> withWithoutArrayWrapper(){
            target.setUseWithoutArrayWrapper(true);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick config
         * @param items JsonConfigurator
         * @return THIS
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

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Raw(){
            return xml -> xml.setUseRaw(true);
        }

        /**
         * Quick build
         * @param elementName element name
         * @return XmlConfigurator
         */
        static XmlConfigurator $Raw(String elementName){
            return xml -> {
                xml.setUseRaw(true);
                if(elementName != null){
                    xml.setElementName(c_string(elementName));
                }
            };
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Auto(){
            return xml -> xml.setUseAuto(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Explicit(){
            return xml -> xml.setUseExplicit(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Path(){
            return xml -> xml.setUsePath(true);
        }

        /**
         * Quick build
         * @param elementName element name
         * @return XmlConfigurator
         */
        static XmlConfigurator $Path(String elementName){
            return xml -> {
                xml.setUsePath(true);
                if(elementName != null){
                    xml.setElementName(c_string(elementName));
                }
            };
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Xmldata(){
            return xml -> xml.setUseXmlData(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Xmlschema(){
            return xml -> xml.setUseXmlSchema(true);
        }

        /**
         * Quick build
         * @param targetNameSpaceURI target namespace URI
         * @return XmlConfigurator
         */
        static XmlConfigurator $Xmlschema(String targetNameSpaceURI){
            return xml -> {
                xml.setUseXmlSchema(true);
                if(targetNameSpaceURI != null){
                    xml.setRootName(c_string(targetNameSpaceURI));
                }
            };
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $ElementsXsinil(){
            return xml -> xml.setUseElementsXsinil(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $ElementsAbsent(){
            return xml -> xml.setUseElementsAbsent(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $BinaryBase64(){
            return xml -> xml.setUseBinaryBase64(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Type(){
            return xml -> xml.setUseType(true);
        }

        /**
         * Quick build
         * @return XmlConfigurator
         */
        static XmlConfigurator $Root(){
            return xml -> xml.setUseRoot(true);
        }

        /**
         * Quick build
         * @param rootName root name
         * @return XmlConfigurator
         */
        static XmlConfigurator $Root(String rootName){
            return xml -> {
                xml.setUseRoot(true);
                if(rootName != null){
                    xml.setRootName(c_string(rootName));
                }
            };
        }
    }

    /**
     * Quick config
     */
    public interface JsonConfigurator
            extends Configurator<For.Json> {

        /**
         * Quick build
         * @return JsonConfigurator
         */
        static JsonConfigurator $Auto(){
            return json -> json.setUsePath(false);
        }

        /**
         * Quick build
         * @return JsonConfigurator
         */
        static JsonConfigurator $Path(){
            return json -> json.setUsePath(true);
        }

        /**
         * Quick build
         * @return JsonConfigurator
         */
        static XmlConfigurator $Root(){
            return json -> json.setUseRoot(true);
        }

        /**
         * Quick build
         * @param rootName root name
         * @return JsonConfigurator
         */
        static JsonConfigurator $Root(String rootName){
            return json -> {
                json.setUseRoot(true);
                if(rootName != null){
                    json.setRootName(c_string(rootName));
                }
            };
        }

        /**
         * Quick build
         * @return JsonConfigurator
         */
        static JsonConfigurator $IncludeNullValues(){
            return json -> json.setUseIncludeNullValue(true);
        }

        /**
         * Quick build
         * @return JsonConfigurator
         */
        static JsonConfigurator $WithoutArrayWrapper(){
            return json -> json.setUseWithoutArrayWrapper(true);
        }

    }

}
