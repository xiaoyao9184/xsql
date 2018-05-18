package com.xy.xsql.tsql.core.datatype;

import com.xy.xsql.tsql.model.datatypes.table.column.DataType;

/**
 * DataType Factory
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class DataTypes {

    //DateTime

    public static DataType _date(){
        return new DataType().withSynonym(DataType.Synonyms._date);
    }

    public static DataType _datetime(){
        return new DataType().withSynonym(DataType.Synonyms._datetime);
    }

    public static DataType _datetime2(){
        return new DataType().withSynonym(DataType.Synonyms._datetime2);
    }

    public static DataType _datetimeoffset(){
        return new DataType().withSynonym(DataType.Synonyms._datetimeoffset);
    }

    public static DataType _smalldatetime(){
        return new DataType().withSynonym(DataType.Synonyms._smalldatetime);
    }

    public static DataType _time(){
        return new DataType().withSynonym(DataType.Synonyms._time);
    }


    //Numeric

    public static DataType _bit(){
        return new DataType().withSynonym(DataType.Synonyms._bit);
    }

    public static DataType _decimal(){
        return new DataType().withSynonym(DataType.Synonyms._decimal);
    }

    public static DataType _decimal(Integer p, Integer... s){
        return new DataType().withSynonym(DataType.Synonyms._decimal)
                .withPrecision(p)
                .withScale(s.length == 1 ? s[0] : null);
    }

    public static DataType _numeric(){
        return new DataType().withSynonym(DataType.Synonyms._numeric);
    }

    public static DataType _numeric(Integer p, Integer... s){
        return new DataType().withSynonym(DataType.Synonyms._numeric)
                .withPrecision(p)
                .withScale(s.length == 1 ? s[0] : null);
    }

    public static DataType _float(){
        return new DataType().withSynonym(DataType.Synonyms._float);
    }

    public static DataType _float(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._float)
                .withLength(n);
    }

    public static DataType _real(){
        return new DataType().withSynonym(DataType.Synonyms._real);
    }

    public static DataType _bigint(){
        return new DataType().withSynonym(DataType.Synonyms._bigint);
    }

    public static DataType _int(){
        return new DataType().withSynonym(DataType.Synonyms._int);
    }

    public static DataType _smallint(){
        return new DataType().withSynonym(DataType.Synonyms._smallint);
    }

    public static DataType _tinyint(){
        return new DataType().withSynonym(DataType.Synonyms._tinyint);
    }

    public static DataType _money(){
        return new DataType().withSynonym(DataType.Synonyms._money);
    }

    public static DataType _smallmoney(){
        return new DataType().withSynonym(DataType.Synonyms._smallmoney);
    }

    //Binary

    public static DataType _binary(){
        return new DataType().withSynonym(DataType.Synonyms._binary);
    }

    public static DataType _binary(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._binary)
                .withLength(n);
    }

    public static DataType _varbinary(){
        return new DataType().withSynonym(DataType.Synonyms._varbinary);
    }

    public static DataType _varbinary(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._varbinary)
                .withLength(n);
    }

    //Char

    public static DataType _char(){
        return new DataType().withSynonym(DataType.Synonyms._char);
    }

    public static DataType _char(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._char)
                .withLength(n);
    }

    public static DataType _varchar(){
        return new DataType().withSynonym(DataType.Synonyms._varchar);
    }

    public static DataType _varchar(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._varchar)
                .withLength(n);
    }

    public static DataType _nchar(){
        return new DataType().withSynonym(DataType.Synonyms._nchar);
    }

    public static DataType _nchar(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._nchar)
                .withLength(n);
    }

    public static DataType _nvarchar(){
        return new DataType().withSynonym(DataType.Synonyms._nvarchar);
    }

    public static DataType _nvarchar(Integer n){
        return new DataType().withSynonym(DataType.Synonyms._nvarchar)
                .withLength(n);
    }

    public static DataType _ntext(){
        return new DataType().withSynonym(DataType.Synonyms._ntext);
    }

    public static DataType _text(){
        return new DataType().withSynonym(DataType.Synonyms._text);
    }

    public static DataType _image(){
        return new DataType().withSynonym(DataType.Synonyms._image);
    }



    //

    public static DataType _uniqueidentifier(){
        return new DataType().withSynonym(DataType.Synonyms._uniqueidentifier);
    }

    public static DataType _timestamp(){
        return new DataType().withSynonym(DataType.Synonyms._timestamp);
    }

    public static DataType _rowversion(){
        return new DataType().withSynonym(DataType.Synonyms._rowversion);
    }

    public static DataType _hierarchyid(){
        return new DataType().withSynonym(DataType.Synonyms._hierarchyid);
    }

    public static DataType _sql_variant(){
        return new DataType().withSynonym(DataType.Synonyms._sql_variant);
    }

    public static DataType _xml(){
        return new DataType().withSynonym(DataType.Synonyms._xml);
    }

    public static DataType _user_defined(String name){
        return new DataType(name);
    }


}
