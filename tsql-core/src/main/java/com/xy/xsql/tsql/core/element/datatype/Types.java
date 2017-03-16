package com.xy.xsql.tsql.core.element.datatype;

import com.xy.xsql.tsql.model.datatype.DataType;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class Types {

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


}
