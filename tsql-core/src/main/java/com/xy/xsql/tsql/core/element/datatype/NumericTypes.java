package com.xy.xsql.tsql.core.element.datatype;

import com.xy.xsql.tsql.model.datatype.DataType;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class NumericTypes {

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

}
