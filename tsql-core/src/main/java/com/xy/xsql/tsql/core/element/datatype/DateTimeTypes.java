package com.xy.xsql.tsql.core.element.datatype;

import com.xy.xsql.tsql.model.datatype.DataType;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class DateTimeTypes {

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
}
