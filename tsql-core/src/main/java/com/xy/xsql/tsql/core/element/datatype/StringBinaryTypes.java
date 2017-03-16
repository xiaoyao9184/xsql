package com.xy.xsql.tsql.core.element.datatype;

import com.xy.xsql.tsql.model.datatype.DataType;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class StringBinaryTypes {

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

}
