package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
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
     { RAW [ ( 'BlockName' ) ] | AUTO }
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
     | PATH [ ( 'BlockName' ) ]
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

        private String BlockName;

        //[ , XMLDATA ]
        private boolean useXxmData;
        //[ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]
        private boolean useXxmSchema;
        private String targetNameSpaceURI;


        //<CommonDirectivesForXML>
        private CommonDirectivesForXML commonDirectivesForXML;
        //[ , ELEMENTS [ XSINIL | ABSENT ] ]
        private boolean useELEMENTS_XSINIL;
        private boolean useELEMENTS_ABSENT;

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

        public String getBlockName() {
            return BlockName;
        }

        public void setBlockName(String BlockName) {
            this.BlockName = BlockName;
        }

        public boolean isUseXxmData() {
            return useXxmData;
        }

        public void setUseXxmData(boolean useXxmData) {
            this.useXxmData = useXxmData;
        }

        public boolean isUseXxmSchema() {
            return useXxmSchema;
        }

        public void setUseXxmSchema(boolean useXxmSchema) {
            this.useXxmSchema = useXxmSchema;
        }

        public String getTargetNameSpaceURI() {
            return targetNameSpaceURI;
        }

        public void setTargetNameSpaceURI(String targetNameSpaceURI) {
            this.targetNameSpaceURI = targetNameSpaceURI;
        }

        public CommonDirectivesForXML getCommonDirectivesForXML() {
            return commonDirectivesForXML;
        }

        public void setCommonDirectivesForXML(CommonDirectivesForXML commonDirectivesForXML) {
            this.commonDirectivesForXML = commonDirectivesForXML;
        }

        public boolean isUseELEMENTS_XSINIL() {
            return useELEMENTS_XSINIL;
        }

        public void setUseELEMENTS_XSINIL(boolean useELEMENTS_XSINIL) {
            this.useELEMENTS_XSINIL = useELEMENTS_XSINIL;
        }

        public boolean isUseELEMENTS_ABSENT() {
            return useELEMENTS_ABSENT;
        }

        public void setUseELEMENTS_ABSENT(boolean useELEMENTS_ABSENT) {
            this.useELEMENTS_ABSENT = useELEMENTS_ABSENT;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();
            if(useRaw || useAuto){
                b.append(useRaw ? Keywords.Key.RAW : Keywords.Key.AUTO)
                        .append(useRaw && !CheckUtil.isNullOrEmpty(BlockName) ?
                                "'" + BlockName + "'" :
                                null);

                if(commonDirectivesForXML != null){
                    b.append(commonDirectivesForXML);
                    if(useXxmData){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.XMLDATA);
                    }else {
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.XMLSCHEMA)
                                .append(CheckUtil.isNullOrEmpty(targetNameSpaceURI) ?
                                        null :
                                        "'" + targetNameSpaceURI + "'");
                    }
                    if(useELEMENTS_ABSENT){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.ELEMENTS)
                                .append(Keywords.Key.ABSENT);
                    }else if(useELEMENTS_XSINIL){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.ELEMENTS)
                                .append(Keywords.Key.XSINIL);
                    }
                }
            } else if(useExplicit){
                b.append(Keywords.Key.EXPLICIT);
                if(commonDirectivesForXML != null){
                    b.append(commonDirectivesForXML);
                    if(useXxmData){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.XMLDATA);
                    }
                }
            } else if(usePath){
                b.append(Keywords.Key.PATH)
                        .append(CheckUtil.isNullOrEmpty(BlockName) ?
                                null :
                                "'" + BlockName + "'");
                if(commonDirectivesForXML != null){
                    b.append(commonDirectivesForXML);
                    if(useELEMENTS_ABSENT){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.ELEMENTS)
                                .append(Keywords.Key.ABSENT);
                    }else if(useELEMENTS_XSINIL){
                        b.append(Other.DELIMITER)
                                .append(Keywords.Key.ELEMENTS)
                                .append(Keywords.Key.XSINIL);
                    }
                }
            }
            return b.build();
        }
    }

    /**
     *
     *

     <CommonDirectivesForXML> ::=
     [ , BINARY BASE64 ]
     [ , TYPE ]
     [ , ROOT [ ( 'RootName' ) ] ]

     *
     */
    public static class CommonDirectivesForXML implements Block {
        private boolean useBINARY_BASE64;
        private boolean useTYPE;
        private boolean useROOT;
        private String rootName;

        public boolean isUseBINARY_BASE64() {
            return useBINARY_BASE64;
        }

        public void setUseBINARY_BASE64(boolean useBINARY_BASE64) {
            this.useBINARY_BASE64 = useBINARY_BASE64;
        }

        public boolean isUseTYPE() {
            return useTYPE;
        }

        public void setUseTYPE(boolean useTYPE) {
            this.useTYPE = useTYPE;
        }

        public boolean isUseROOT() {
            return useROOT;
        }

        public void setUseROOT(boolean useROOT) {
            this.useROOT = useROOT;
        }

        public String getRootName() {
            return rootName;
        }

        public void setRootName(String rootName) {
            this.rootName = rootName;
        }

        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder();
            if(useBINARY_BASE64){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.BINARY)
                        .append(Keywords.Key.BASE64);
            } else if(useTYPE){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.TYPE);
            } else if(useROOT){
                b.append(Other.DELIMITER)
                        .append(Keywords.Key.ROOT)
                        .append(CheckUtil.isNullOrEmpty(rootName) ?
                                null :
                                "'" + rootName + "'");
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
