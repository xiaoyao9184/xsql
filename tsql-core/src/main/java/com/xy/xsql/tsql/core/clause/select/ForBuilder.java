package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.For;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

/**
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
        tar.setUseBrowse(true);
        return this;
    }

    public XmlBuilder<ForBuilder<ParentBuilder>> withXml(){
        return new XmlBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Xml::new,
                        tar::getXml,
                        tar::setXml))
                .in(this);
    }

    public JsonBuilder<ForBuilder<ParentBuilder>> withJson(){
        return new JsonBuilder<ForBuilder<ParentBuilder>>
                (initSet(For.Json::new,
                        tar::getJson,
                        tar::setJson))
                .in(this);
    }


    public class JsonBuilder<ParentBuilder>
            extends CodeTreeBuilder<JsonBuilder<ParentBuilder>,ParentBuilder,For.Json> {

        public JsonBuilder(For.Json tar) {
            super(tar);
        }
        public JsonBuilder<ParentBuilder> withPath(){
            tar.setUsePath(true);
            return this;
        }

        public JsonBuilder<ParentBuilder> withRoot(String rootName){
            tar.setUseRoot(true);
            tar.setRootName(rootName);
            return this;
        }

        public JsonBuilder<ParentBuilder> withIncludeNullValue(){
            tar.setUseIncludeNullValue(true);
            return this;
        }

        public JsonBuilder<ParentBuilder> withWithoutArrayWrapper(){
            tar.setUseWithoutArrayWrapper(true);
            return this;
        }

    }

    public class XmlBuilder<ParentBuilder>
            extends CodeTreeBuilder<XmlBuilder<ParentBuilder>,ParentBuilder,For.Xml> {

        public XmlBuilder(For.Xml tar) {
            super(tar);
        }

        public XmlBuilder<ParentBuilder> withAuto(){
            tar.setUseAuto(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRaw(){
            tar.setUseRaw(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRaw(String elementName){
            tar.setUseRaw(true);
            tar.setElementName(e_string(elementName));
            return this;
        }

        public XmlBuilder<ParentBuilder> withExplicit(){
            tar.setUseExplicit(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withPath(){
            tar.setUsePath(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withPath(String elementName){
            tar.setUsePath(true);
            tar.setElementName(e_string(elementName));
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlData(){
            tar.setUseXmlData(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlSchema(){
            tar.setUseXmlSchema(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withXmlSchema(String targetNameSpaceURI){
            tar.setUseXmlSchema(true);
            tar.setTargetNameSpaceURI(targetNameSpaceURI);
            return this;
        }


        public XmlBuilder<ParentBuilder> withElementsAbsent(){
            tar.setUseElementsAbsent(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withElementsXsinil(){
            tar.setUseElementsXsinil(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withBinaryBase64(){
            tar.setUseBinaryBase64(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withType(){
            tar.setUseType(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRoot(){
            tar.setUseRoot(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRoot(String rootName){
            tar.setUseRoot(true);
            tar.setRootName(e_string(rootName));
            return this;
        }

    }





}
