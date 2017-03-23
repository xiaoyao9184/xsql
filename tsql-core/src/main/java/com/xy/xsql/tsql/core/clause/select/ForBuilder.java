package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.select.For;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

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


    /**
     * JsonBuilder
     * @param <ParentBuilder>
     */
    public class JsonBuilder<ParentBuilder>
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
            target.setRootName(rootName);
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

    }

    /**
     * XmlBuilder
     * @param <ParentBuilder>
     */
    public class XmlBuilder<ParentBuilder>
            extends CodeTreeBuilder<XmlBuilder<ParentBuilder>,ParentBuilder,For.Xml> {

        public XmlBuilder(For.Xml tar) {
            super(tar);
        }

        public XmlBuilder<ParentBuilder> withAuto(){
            target.setUseAuto(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRaw(){
            target.setUseRaw(true);
            return this;
        }

        public XmlBuilder<ParentBuilder> withRaw(String elementName){
            target.setUseRaw(true);
            target.setElementName(e_string(elementName));
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
            target.setElementName(e_string(elementName));
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
            target.setTargetNameSpaceURI(targetNameSpaceURI);
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
            target.setRootName(e_string(rootName));
            return this;
        }

    }


}
