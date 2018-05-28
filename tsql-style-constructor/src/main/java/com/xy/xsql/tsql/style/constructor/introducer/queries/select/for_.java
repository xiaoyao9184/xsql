package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_FOR;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface for_ {

    static b_FOR FOR(b_FOR.k_BROWSE browse){
        return new b_FOR(){
            {
                withBrowse();
            }
        };
    }

    static b_FOR FOR(b_FOR.b_XML xml){
        return new b_FOR(){
            {
                withXml(xml.build());
            }
        };
    }

    static b_FOR FOR(b_FOR.b_JSON json){
        return new b_FOR(){
            {
                withJson(json.build());
            }
        };
    }



    static b_FOR.k_AUTO AUTO(){
        return null;
    }
    static b_FOR.b_ROOT ROOT(){
        return new b_FOR.b_ROOT(null);
    }
    static b_FOR.b_ROOT ROOT(String rootName){
        return new b_FOR.b_ROOT(rootName);
    }

    static b_FOR.b_XML.b_RAW RAW(){
        return new b_FOR.b_XML.b_RAW(null);
    }
    static b_FOR.b_XML.b_RAW RAW(String elementName){
        return new b_FOR.b_XML.b_RAW(elementName);
    }
    static b_FOR.b_XML.k_BINARY_BASE64 BINARY_BASE64(){
        return null;
    }
    static b_FOR.b_XML.k_TYPE TYPE(){
        return null;
    }
    static b_FOR.b_XML.k_XMLDATA XMLDATA(){
        return null;
    }
    static b_FOR.b_XML.b_XMLSCHEMA XMLSCHEMA(){
        return new b_FOR.b_XML.b_XMLSCHEMA(null);
    }
    static b_FOR.b_XML.b_XMLSCHEMA XMLSCHEMA(String targetNameSpaceURI){
        return new b_FOR.b_XML.b_XMLSCHEMA(targetNameSpaceURI);
    }
    static b_FOR.b_XML.k_ELEMENTS ELEMENTS(){
        return null;
    }
    static b_FOR.b_XML.k_XSINIL XSINIL(){
        return null;
    }
    static b_FOR.b_XML.k_ABSENT ABSENT(){
        return null;
    }
    static b_FOR.b_XML.k_EXPLICIT EXPLICIT(){
        return null;
    }

    static b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES(){
        return null;
    }
    static b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER(){
        return null;
    }



    static b_FOR.k_BROWSE BROWSE(){
        return null;
    }



    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.b_RAW raw){
        return new b_FOR.b_XML(){
            {
                withRaw(raw.build());
                withBinaryBase64();
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }


    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_XMLDATA xmldata,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlData();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.b_XMLSCHEMA xmlschema){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withXmlSchema(xmlschema.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElementsXsinil();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElementsAbsent();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElements();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.k_AUTO auto){
        return new b_FOR.b_XML(){
            {
                withAuto();
                withBinaryBase64();
                withElements();
            }
        };
    }

    //

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withType();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withType();
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withType();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withRoot(root.build());
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withBinaryBase64();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withType();
                withRoot(root.build());
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withType();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withType();
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withType();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withRoot(root.build());
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit,
                           b_FOR.b_XML.k_XMLDATA xmldata){
        return new b_FOR.b_XML(){
            {
                withExplicit();
                withXmlData();
            }
        };
    }
    static b_FOR.b_XML XML(b_FOR.b_XML.k_EXPLICIT explicit){
        return new b_FOR.b_XML(){
            {
                withExplicit();
            }
        };
    }

    //

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withType();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withRoot(root.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_BINARY_BASE64 binary_base64){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withBinaryBase64();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withRoot(root.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_TYPE type){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withType();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withRoot(root.build());
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withRoot(root.build());
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_ROOT root,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withRoot(root.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_ROOT root){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withRoot(root.build());
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_XSINIL xsinil){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withElementsXsinil();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_ELEMENTS elements,
                           b_FOR.b_XML.k_ABSENT absent){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withElementsAbsent();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path,
                           b_FOR.b_XML.k_ELEMENTS elements){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
                withElements();
            }
        };
    }

    static b_FOR.b_XML XML(b_FOR.b_PATH path){
        return new b_FOR.b_XML(){
            {
                withPath(path.build());
            }
        };
    }

    //

    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto){
        return new b_FOR.b_JSON(){
            {
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_ROOT root){
        return new b_FOR.b_JSON(){
            {
                withRoot(root.build());
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES){
        return new b_FOR.b_JSON(){
            {
                withRoot(root.build());
                withIncludeNullValue();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                withRoot(root.build());
                withIncludeNullValue();
                withWithoutArrayWrapper();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                withRoot(root.build());
                withWithoutArrayWrapper();
            }
        };
    }

    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES){
        return new b_FOR.b_JSON(){
            {
                withIncludeNullValue();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                withIncludeNullValue();
                withWithoutArrayWrapper();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.k_AUTO auto,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                withWithoutArrayWrapper();
            }
        };
    }

    //

    static b_FOR.b_JSON JSON(b_FOR.b_PATH path){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_ROOT root){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withRoot(root.build());
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withRoot(root.build());
                withIncludeNullValue();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withRoot(root.build());
                withIncludeNullValue();
                withWithoutArrayWrapper();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_ROOT root,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withRoot(root.build());
                withWithoutArrayWrapper();
            }
        };
    }

    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withIncludeNullValue();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_JSON.k_INCLUDE_NULL_VALUES INCLUDE_NULL_VALUES,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withIncludeNullValue();
                withWithoutArrayWrapper();
            }
        };
    }
    static b_FOR.b_JSON JSON(b_FOR.b_PATH auto,
                             b_FOR.b_JSON.k_WITHOUT_ARRAY_WRAPPER WITHOUT_ARRAY_WRAPPER){
        return new b_FOR.b_JSON(){
            {
                build().setUsePath(true);
                withWithoutArrayWrapper();
            }
        };
    }
}
