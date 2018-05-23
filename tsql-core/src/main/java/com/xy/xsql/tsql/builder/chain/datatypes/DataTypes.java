package com.xy.xsql.tsql.builder.chain.datatypes;

import com.xy.xsql.tsql.model.datatypes.*;
import com.xy.xsql.tsql.model.datatypes.datetime.*;
import com.xy.xsql.tsql.model.datatypes.numeric.*;
import com.xy.xsql.tsql.model.datatypes.spatial.Geography;
import com.xy.xsql.tsql.model.datatypes.spatial.Geometry;
import com.xy.xsql.tsql.model.datatypes.string.*;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public interface DataTypes {

    static Date _date(){
        return new Date();
    }

    static DateTime _datetime(){
        return new DateTime();
    }

    static DateTime2 _datetime2(){
        return new DateTime2();
    }

    static DateTime2 _datetime2(Integer precision){
        DateTime2 t = new DateTime2();
        t.precision(precision);
        return t;
    }

    static DateTimeOffset _datetimeoffset(){
        return new DateTimeOffset();
    }

    static DateTimeOffset _datetimeoffset(Integer precision){
        DateTimeOffset t = new DateTimeOffset();
        t.precision(precision);
        return t;
    }

    static SmallDateTime _smalldatetime(){
        return new SmallDateTime();
    }

    static Time _time(){
        return new Time();
    }

    static Time _time(Integer scale){
        Time t = new Time();
        t.scale(scale);
        return t;
    }

    static BigInt _bigint(){
        return new BigInt();
    }

    static Decimal _decimal(){
        return new Decimal();
    }

    static Decimal _decimal(Integer precision){
        Decimal t = new Decimal();
        t.precision(precision);
        return t;
    }

    static Decimal _decimal(Integer precision, Integer scale){
        Decimal t = new Decimal();
        t.precision(precision);
        t.scale(scale);
        return t;
    }

    static com.xy.xsql.tsql.model.datatypes.numeric.Float _float(){
        return new com.xy.xsql.tsql.model.datatypes.numeric.Float();
    }

    static com.xy.xsql.tsql.model.datatypes.numeric.Float _float(Integer length){
        com.xy.xsql.tsql.model.datatypes.numeric.Float t = new com.xy.xsql.tsql.model.datatypes.numeric.Float();
        t.length(length);
        return t;
    }

    static Int _int(){
        return new Int();
    }

    static Money _money(){
        return new Money();
    }

    static Numeric _numeric(){
        return new Numeric();
    }

    static Numeric _numeric(Integer precision){
        Numeric t = new Numeric();
        t.precision(precision);
        return t;
    }

    static Numeric _numeric(Integer precision, Integer scale){
        Numeric t = new Numeric();
        t.precision(precision);
        t.scale(scale);
        return t;
    }

    static Real _real(){
        return new Real();
    }

    static SmallInt _smallint(){
        return new SmallInt();
    }

    static SmallMoney _smallmoney(){
        return new SmallMoney();
    }

    static TinyInt _tinyint(){
        return new TinyInt();
    }

    static Geography _geography(){
        return new Geography();
    }

    static Geometry _geometry(){
        return new Geometry();
    }

    static Binary _binary(){
        return new Binary();
    }

    static Binary _binary(Integer length){
        Binary t = new Binary();
        t.length(length);
        return t;
    }

    static Char _char(){
        return new Char();
    }

    static Char _char(Integer length){
        Char t = new Char();
        t.length(length);
        return t;
    }

    static Image _image(){
        return new Image();
    }

    static NChar _nchar(){
        return new NChar();
    }

    static NChar _nchar(Integer length){
        NChar t = new NChar();
        t.length(length);
        return t;
    }

    static NText _ntext(){
        return new NText();
    }

    static NVarChar _nvarchar(){
        return new NVarChar();
    }

    static NVarChar _nvarchar(Integer length){
        NVarChar t = new NVarChar();
        t.length(length);
        return t;
    }

    static Text _text(){
        return new Text();
    }

    static VarBinary _varbinary(){
        return new VarBinary();
    }

    static VarBinary _varbinary(Integer length){
        VarBinary t = new VarBinary();
        t.length(length);
        return t;
    }

    static VarChar _varchar(){
        return new VarChar();
    }

    static VarChar _varchar(Integer length){
        VarChar t = new VarChar();
        t.length(length);
        return t;
    }

    static Bit bit(){
        return new Bit();
    }

    static HierarchyId hierarchyid(){
        return new HierarchyId();
    }

    static RowVersion rowversion(){
        return new RowVersion();
    }

    static Sql_Variant sql_variant(){
        return new Sql_Variant();
    }

    static Uniqueidentifier uniqueidentifier(){
        return new Uniqueidentifier();
    }

    static Xml xml(boolean useContent, boolean useDocument, String xmlSchemaCollection){
        Xml t = new Xml();
        t.setUseContent(useContent);
        t.setUseDocument(useDocument);
        t.setXmlSchemaCollection(xmlSchemaCollection);
        return t;
    }
}
