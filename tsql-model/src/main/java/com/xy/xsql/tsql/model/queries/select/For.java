package com.xy.xsql.tsql.model.queries.select;

import com.xy.xsql.tsql.model.queries.Clause;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

/**
 *
 *

 [ FOR { BROWSE | <XML> | <JSON>} ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class For implements Clause {

    private boolean useBrowse;
    private Xml xml;
    private Json json;

    public boolean isUseBrowse() {
        return useBrowse;
    }

    public void setUseBrowse(boolean useBrowse) {
        this.useBrowse = useBrowse;
    }

    public Xml getXml() {
        return xml;
    }

    public void setXml(Xml xml) {
        this.xml = xml;
    }

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }


    /**
     *
     *
     <XML> ::=
     XML
     {
     { RAW [ ( 'ElementName' ) ] | AUTO }
     [
     <CommonDirectivesForXML>
     [ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]
     [ , ELEMENTS [ XSINIL | ABSENT ]
     ]
     | EXPLICIT
     [
     <CommonDirectivesForXML>
     [ , XMLDATA ]
     ]
     | PATH [ ( 'ElementName' ) ]
     [
     <CommonDirectivesForXML>
     [ , ELEMENTS [ XSINIL | ABSENT ] ]
     ]
     }

     <CommonDirectivesForXML> ::=
     [ , BINARY BASE64 ]
     [ , TYPE ]
     [ , ROOT [ ( 'RootName' ) ] ]

     *
     */
    public static class Xml {
        private boolean useRaw;
        private boolean useAuto;
        private boolean useExplicit;
        private boolean usePath;

        private StringConstant elementName;


        //<CommonDirectivesForXML> ::=
        //        [ , BINARY BASE64 ]
        //        [ , TYPE ]
        //        [ , ROOT [ ( 'RootName' ) ] ]
        private boolean useBinaryBase64;
        private boolean useType;
        private boolean useRoot;
        private StringConstant rootName;


        //[ , XMLDATA ]
        private boolean useXmlData;
        //[ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]
        private boolean useXmlSchema;
        private StringConstant targetNameSpaceURI;

        //[ , ELEMENTS [ XSINIL | ABSENT ] ]
        private boolean useElements;
        private boolean useElementsXsinil;
        private boolean useElementsAbsent;

        public boolean isUseRaw() {
            return useRaw;
        }

        public void setUseRaw(boolean useRaw) {
            this.useRaw = useRaw;
        }

        public boolean isUseAuto() {
            return useAuto;
        }

        public void setUseAuto(boolean useAuto) {
            this.useAuto = useAuto;
        }

        public boolean isUseExplicit() {
            return useExplicit;
        }

        public void setUseExplicit(boolean useExplicit) {
            this.useExplicit = useExplicit;
        }

        public boolean isUsePath() {
            return usePath;
        }

        public void setUsePath(boolean usePath) {
            this.usePath = usePath;
        }

        public StringConstant getElementName() {
            return elementName;
        }

        public void setElementName(StringConstant BlockName) {
            this.elementName = BlockName;
        }

        public boolean isUseXmlData() {
            return useXmlData;
        }

        public void setUseXmlData(boolean useXmlData) {
            this.useXmlData = useXmlData;
        }

        public boolean isUseXmlSchema() {
            return useXmlSchema;
        }

        public void setUseXmlSchema(boolean useXmlSchema) {
            this.useXmlSchema = useXmlSchema;
        }

        public StringConstant getTargetNameSpaceURI() {
            return targetNameSpaceURI;
        }

        public void setTargetNameSpaceURI(StringConstant targetNameSpaceURI) {
            this.targetNameSpaceURI = targetNameSpaceURI;
        }

        public boolean isUseElements() {
            return useElements;
        }

        public void setUseElements(boolean useElements) {
            this.useElements = useElements;
        }

        public boolean isUseElementsXsinil() {
            return useElementsXsinil;
        }

        public void setUseElementsXsinil(boolean useElementsXsinil) {
            this.useElementsXsinil = useElementsXsinil;
        }

        public boolean isUseElementsAbsent() {
            return useElementsAbsent;
        }

        public void setUseElementsAbsent(boolean useElementsAbsent) {
            this.useElementsAbsent = useElementsAbsent;
        }

        public boolean isUseBinaryBase64() {
            return useBinaryBase64;
        }

        public void setUseBinaryBase64(boolean useBinaryBase64) {
            this.useBinaryBase64 = useBinaryBase64;
        }

        public boolean isUseType() {
            return useType;
        }

        public void setUseType(boolean useType) {
            this.useType = useType;
        }

        public boolean isUseRoot() {
            return useRoot;
        }

        public void setUseRoot(boolean useRoot) {
            this.useRoot = useRoot;
        }

        public StringConstant getRootName() {
            return rootName;
        }

        public void setRootName(StringConstant rootName) {
            this.rootName = rootName;
        }

    }




    /**
     *
     *

     <JSON> ::=
     JSON
     {
         { AUTO | PATH }
         [
             [ , ROOT [ ( 'RootName' ) ] ]
             [ , INCLUDE_NULL_VALUES ]
             [ , WITHOUT_ARRAY_WRAPPER ]
         ]

     }

     *
     */
    public static class Json {

        //{ AUTO | PATH }
        private boolean usePath;

        //[ , ROOT [ ( 'RootName' ) ] ]
        private boolean useRoot;
        private StringConstant rootName;

        //[ , INCLUDE_NULL_VALUES ]
        private boolean useIncludeNullValue;

        //[ , WITHOUT_ARRAY_WRAPPER ]
        private boolean useWithoutArrayWrapper;


        public boolean isUsePath() {
            return usePath;
        }

        public void setUsePath(boolean usePath) {
            this.usePath = usePath;
        }

        public boolean isUseRoot() {
            return useRoot;
        }

        public void setUseRoot(boolean useRoot) {
            this.useRoot = useRoot;
        }

        public StringConstant getRootName() {
            return rootName;
        }

        public void setRootName(StringConstant rootName) {
            this.rootName = rootName;
        }

        public boolean isUseIncludeNullValue() {
            return useIncludeNullValue;
        }

        public void setUseIncludeNullValue(boolean useIncludeNullValue) {
            this.useIncludeNullValue = useIncludeNullValue;
        }

        public boolean isUseWithoutArrayWrapper() {
            return useWithoutArrayWrapper;
        }

        public void setUseWithoutArrayWrapper(boolean useWithoutArrayWrapper) {
            this.useWithoutArrayWrapper = useWithoutArrayWrapper;
        }

    }
}
