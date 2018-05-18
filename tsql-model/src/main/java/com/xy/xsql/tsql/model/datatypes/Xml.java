package com.xy.xsql.tsql.model.datatypes;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Xml implements DataType, KeywordNamed {

    private boolean useContent;
    private boolean useDocument;
    private String xmlSchemaCollection;

    public boolean isUseContent() {
        return useContent;
    }

    public void setUseContent(boolean useContent) {
        this.useContent = useContent;
    }

    public boolean isUseDocument() {
        return useDocument;
    }

    public void setUseDocument(boolean useDocument) {
        this.useDocument = useDocument;
    }

    public String getXmlSchemaCollection() {
        return xmlSchemaCollection;
    }

    public void setXmlSchemaCollection(String xmlSchemaCollection) {
        this.xmlSchemaCollection = xmlSchemaCollection;
    }

    @Override
    public Keywords keyword() {
        return Keywords.xml;
    }
}
