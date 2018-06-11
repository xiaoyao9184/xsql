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
@SuppressWarnings("unused")
public interface DataTypes {

    /**
     * Quick build
     * @return Date
     */
    static Date _date(){
        return new Date();
    }

    /**
     * Quick build
     * @return DateTime
     */
    static DateTime _datetime(){
        return new DateTime();
    }

    /**
     * Quick build
     * @return DateTime2
     */
    static DateTime2 _datetime2(){
        return new DateTime2();
    }

    /**
     * Quick build
     * @return DateTime2
     */
    static DateTime2 _datetime2(Integer precision){
        DateTime2 t = new DateTime2();
        t.precision(precision);
        return t;
    }

    /**
     * Quick build
     * @return DateTimeOffset
     */
    static DateTimeOffset _datetimeoffset(){
        return new DateTimeOffset();
    }

    /**
     * Quick build
     * @return DateTimeOffset
     */
    static DateTimeOffset _datetimeoffset(Integer precision){
        DateTimeOffset t = new DateTimeOffset();
        t.precision(precision);
        return t;
    }

    /**
     * Quick build
     * @return SmallDateTime
     */
    static SmallDateTime _smalldatetime(){
        return new SmallDateTime();
    }

    /**
     * Quick build
     * @return Time
     */
    static Time _time(){
        return new Time();
    }

    /**
     * Quick build
     * @return Time
     */
    static Time _time(Integer scale){
        Time t = new Time();
        t.scale(scale);
        return t;
    }

    /**
     * Quick build
     * @return BigInt
     */
    static BigInt _bigint(){
        return new BigInt();
    }

    /**
     * Quick build
     * @return Decimal
     */
    static Decimal _decimal(){
        return new Decimal();
    }

    /**
     * Quick build
     * @return Decimal
     */
    static Decimal _decimal(Integer precision){
        Decimal t = new Decimal();
        t.precision(precision);
        return t;
    }

    /**
     * Quick build
     * @return Decimal
     */
    static Decimal _decimal(Integer precision, Integer scale){
        Decimal t = new Decimal();
        t.precision(precision);
        t.scale(scale);
        return t;
    }

    /**
     * Quick build
     * @return Float
     */
    static com.xy.xsql.tsql.model.datatypes.numeric.Float _float(){
        return new com.xy.xsql.tsql.model.datatypes.numeric.Float();
    }

    /**
     * Quick build
     * @return Float
     */
    static com.xy.xsql.tsql.model.datatypes.numeric.Float _float(Integer length){
        com.xy.xsql.tsql.model.datatypes.numeric.Float t = new com.xy.xsql.tsql.model.datatypes.numeric.Float();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return Int
     */
    static Int _int(){
        return new Int();
    }

    /**
     * Quick build
     * @return Money
     */
    static Money _money(){
        return new Money();
    }

    /**
     * Quick build
     * @return Numeric
     */
    static Numeric _numeric(){
        return new Numeric();
    }

    /**
     * Quick build
     * @return Numeric
     */
    static Numeric _numeric(Integer precision){
        Numeric t = new Numeric();
        t.precision(precision);
        return t;
    }

    /**
     * Quick build
     * @return Numeric
     */
    static Numeric _numeric(Integer precision, Integer scale){
        Numeric t = new Numeric();
        t.precision(precision);
        t.scale(scale);
        return t;
    }

    /**
     * Quick build
     * @return Real
     */
    static Real _real(){
        return new Real();
    }

    /**
     * Quick build
     * @return SmallInt
     */
    static SmallInt _smallint(){
        return new SmallInt();
    }

    /**
     * Quick build
     * @return SmallMoney
     */
    static SmallMoney _smallmoney(){
        return new SmallMoney();
    }

    /**
     * Quick build
     * @return TinyInt
     */
    static TinyInt _tinyint(){
        return new TinyInt();
    }

    /**
     * Quick build
     * @return Geography
     */
    static Geography _geography(){
        return new Geography();
    }

    /**
     * Quick build
     * @return Geometry
     */
    static Geometry _geometry(){
        return new Geometry();
    }

    /**
     * Quick build
     * @return Binary
     */
    static Binary _binary(){
        return new Binary();
    }

    /**
     * Quick build
     * @return Binary
     */
    static Binary _binary(Integer length){
        Binary t = new Binary();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return Char
     */
    static Char _char(){
        return new Char();
    }

    /**
     * Quick build
     * @return Char
     */
    static Char _char(Integer length){
        Char t = new Char();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return Image
     */
    static Image _image(){
        return new Image();
    }

    /**
     * Quick build
     * @return NChar
     */
    static NChar _nchar(){
        return new NChar();
    }

    /**
     * Quick build
     * @return NChar
     */
    static NChar _nchar(Integer length){
        NChar t = new NChar();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return NText
     */
    static NText _ntext(){
        return new NText();
    }

    /**
     * Quick build
     * @return NVarChar
     */
    static NVarChar _nvarchar(){
        return new NVarChar();
    }

    /**
     * Quick build
     * @return NVarChar
     */
    static NVarChar _nvarchar(Integer length){
        NVarChar t = new NVarChar();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return Text
     */
    static Text _text(){
        return new Text();
    }

    /**
     * Quick build
     * @return VarBinary
     */
    static VarBinary _varbinary(){
        return new VarBinary();
    }

    /**
     * Quick build
     * @return VarBinary
     */
    static VarBinary _varbinary(Integer length){
        VarBinary t = new VarBinary();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return VarChar
     */
    static VarChar _varchar(){
        return new VarChar();
    }

    /**
     * Quick build
     * @return VarChar
     */
    static VarChar _varchar(Integer length){
        VarChar t = new VarChar();
        t.length(length);
        return t;
    }

    /**
     * Quick build
     * @return Bit
     */
    static Bit _bit(){
        return new Bit();
    }

    /**
     * Quick build
     * @return HierarchyId
     */
    static HierarchyId _hierarchyid(){
        return new HierarchyId();
    }

    /**
     * Quick build
     * @return RowVersion
     */
    static RowVersion _rowversion(){
        return new RowVersion();
    }

    /**
     * Quick build
     * @return Sql_Variant
     */
    static Sql_Variant _sql_variant(){
        return new Sql_Variant();
    }

    /**
     * Quick build
     * @return Uniqueidentifier
     */
    static Uniqueidentifier _uniqueidentifier(){
        return new Uniqueidentifier();
    }

    /**
     * Quick build
     * @return Xml
     */
    static Xml _xml(boolean useContent, boolean useDocument, String xmlSchemaCollection){
        Xml t = new Xml();
        t.setUseContent(useContent);
        t.setUseDocument(useDocument);
        t.setXmlSchemaCollection(xmlSchemaCollection);
        return t;
    }

    /**
     * Quick build
     * @return Xml
     */
    static Xml _xml(){
        return new Xml();
    }

    /**
     * Quick build
     * @return SimpleDataType
     */
    static DataType.SimpleDataType _user_defined(String name){
        return new DataType.SimpleDataType() {
            @Override
            public String name() {
                return name;
            }
        };
    }
}
