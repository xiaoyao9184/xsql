package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        if(useBrowse) {
            b.append(Keywords.BROWSE);
        } else if(xml != null){
            b.append(xml);
        } else if(json != null){
            b.append(json);
        }
        return b.build();
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
    public static class Xml implements Block {
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
        private String targetNameSpaceURI;

        //[ , ELEMENTS [ XSINIL | ABSENT ] ]
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

        public String getTargetNameSpaceURI() {
            return targetNameSpaceURI;
        }

        public void setTargetNameSpaceURI(String targetNameSpaceURI) {
            this.targetNameSpaceURI = targetNameSpaceURI;
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


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();
            if(useRaw || useAuto){
                b.append(useRaw ? Keywords.Key.RAW : Keywords.Key.AUTO)
                        .append(useRaw && !CheckUtil.isNull(elementName) ?
                                "'" + elementName + "'" :
                                null);
            } else if(useExplicit){
                b.append(Keywords.Key.EXPLICIT);
            } else if(usePath){
                b.append(Keywords.Key.PATH)
                        .append(CheckUtil.isNull(elementName) ?
                                null :
                                "'" + elementName + "'");
            }

            if(useBinaryBase64){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.BINARY)
                        .append(Keywords.Key.BASE64);
            } else if(useType){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.TYPE);
            } else if(useRoot){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.ROOT)
                        .append(rootName);
            }


            if(useRaw || useAuto){
                if(useXmlData){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.XMLDATA);
                }else if(useXmlSchema){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.XMLSCHEMA)
                            .append(targetNameSpaceURI);
                }

                if(useElementsAbsent){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.ELEMENTS)
                            .append(Keywords.Key.ABSENT);
                }else if(useElementsXsinil){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.ELEMENTS)
                            .append(Keywords.Key.XSINIL);
                }
            } else if(useExplicit && useXmlData){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.XMLDATA);
            } else if(usePath){
                if(useElementsAbsent){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.ELEMENTS)
                            .append(Keywords.Key.ABSENT);
                }else if(useElementsXsinil){
                    b.append(Other.DELIMITER)
                            .append(Keywords.Key.ELEMENTS)
                            .append(Keywords.Key.XSINIL);
                }
            }

            return b.build();
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
    public static class Json implements Block {

        //{ AUTO | PATH }
        private boolean usePath;

        //[ , ROOT [ ( 'RootName' ) ] ]
        private boolean useRoot;
        private String rootName;

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

        public String getRootName() {
            return rootName;
        }

        public void setRootName(String rootName) {
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


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(usePath ? Keywords.Key.PATH : Keywords.Key.AUTO);
            if(useRoot){
                b.append(Keywords.Key.ROOT)
                        .append(CheckUtil.isNullOrEmpty(rootName) ?
                                null :
                                "'" + rootName + "'");
            }
            if(useIncludeNullValue){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.INCLUDE_NULL_VALUES);
            }
            if(useWithoutArrayWrapper){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.WITHOUT_ARRAY_WRAPPER);
            }

            return b.build();
        }
    }
}
