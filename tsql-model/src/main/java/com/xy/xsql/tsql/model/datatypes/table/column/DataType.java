package com.xy.xsql.tsql.model.datatypes.table.column;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.Other;

/**
 *
 *
 * Also in CREATE TABLE like this
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql?view=sql-server-2017
 *
 * [type_schema_name . ] type_name [ (precision [ , scale ]) ]
 *
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class DataType {


    private String typeSchemaName;
    private String name;
    private Synonyms synonym;
    private Integer precision;
    private Integer scale;
    private Integer length;

    private boolean useMax;

    private boolean useDocument;
    private StringConstant xmlSchemaCollection;

    public DataType() {
    }

    public DataType(String name) {
        this.name = name;
    }


    public String getTypeSchemaName() {
        return typeSchemaName;
    }

    public void setTypeSchemaName(String typeSchemaName) {
        this.typeSchemaName = typeSchemaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Synonyms getSynonym() {
        return synonym;
    }

    public void setSynonym(Synonyms synonym) {
        this.synonym = synonym;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }




    public DataType withSynonym(Synonyms synonym) {
        this.synonym = synonym;
        return this;
    }

    public DataType withPrecision(Integer precision) {
        this.precision = precision;
        return this;
    }

    public DataType withScale(Integer scale) {
        this.scale = scale;
        return this;
    }

    public DataType withLength(Integer length) {
        this.length = length;
        return this;
    }

    public boolean isUseMax() {
        return useMax;
    }

    public void setUseMax(boolean useMax) {
        this.useMax = useMax;
    }

    public boolean isUseDocument() {
        return useDocument;
    }

    public void setUseDocument(boolean useDocument) {
        this.useDocument = useDocument;
    }

    public StringConstant getXmlSchemaCollection() {
        return xmlSchemaCollection;
    }

    public void setXmlSchemaCollection(StringConstant xmlSchemaCollection) {
        this.xmlSchemaCollection = xmlSchemaCollection;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(name != null){
            b.append(name);
            return b.toString();
        }

        b.append(this.synonym.toString());
        if(length != null){
            b.append(Other.GROUP_START.toString())
                .append(this.length)
                .append(Other.GROUP_END.toString());
        }else if(precision != null && scale != null){
            b.append(Other.GROUP_START.toString())
                    .append(this.precision)
                    .append(Other.DELIMITER.toString())
                    .append(this.scale)
                    .append(Other.GROUP_END.toString());
        }else if(precision != null){
            b.append(Other.GROUP_START.toString())
                    .append(this.precision)
                    .append(Other.GROUP_END.toString());
        }
        return b.toString();
    }


    public enum Synonyms {
        _date(),
        _datetime(),
        _datetime2(),
        _datetimeoffset(),
        _smalldatetime(),
        _time(),

        _bit(),
        _decimal(),
        _numeric(),
        _float(),
        _real(),
        _bigint(),
        _int(),
        _smallint(),
        _tinyint(),
        _money(),
        _smallmoney(),

        _binary(),
        _varbinary(),
        _char(),
        _varchar(),
        _nchar(),
        _nvarchar(),
        _ntext(),
        _text(),
        _image(),


        _uniqueidentifier(),
        _timestamp(),
        _rowversion(),
        _hierarchyid(),
        _sql_variant(),
        _xml();

        @Override
        public String toString() {
            return name().replace("_","");
        }
    }
}
