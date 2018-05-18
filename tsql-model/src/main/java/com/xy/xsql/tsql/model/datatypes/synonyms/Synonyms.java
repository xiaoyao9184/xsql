package com.xy.xsql.tsql.model.datatypes.synonyms;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.RowVersion;
import com.xy.xsql.tsql.model.datatypes.numeric.Decimal;
import com.xy.xsql.tsql.model.datatypes.numeric.Float;
import com.xy.xsql.tsql.model.datatypes.numeric.Int;
import com.xy.xsql.tsql.model.datatypes.numeric.Real;
import com.xy.xsql.tsql.model.datatypes.string.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * synonyms for data_type
 * https://docs.microsoft.com/en-us/sql/t-sql/data-types/data-type-synonyms-transact-sql?view=sql-server-2017
 * Created by xiaoyao9184 on 2018/5/18.
 */
public interface Synonyms {

    /**
     * internal keyword for data_type synonyms
     */
    enum Keywords {

        Binary_varying,
        char_varying,
        character,
        character_varying,
        Dec,
        Double_precision,
        float$,
        integer,
        national_character,
        national_char,
        national_character_varying,
        national_char_varying,
        national_text,
        timestamp,;

        private String string;

        Keywords(){
            this.string = this.name()
                    .replace('_',' ')
                    .replace("$","");
        }
        @Override
        public String toString(){
            return string;
        }
    }

    /**
     * use keyword for data_type's synonyms
     */
    interface ISOKeywordNamed
            extends DataType,Synonyms {

        @Override
        default String name() {
            return keyword().toString();
        }

        Synonyms.Keywords keyword();


        static ISOKeywordNamed keyword_type(Synonyms.Keywords keywords){
            return () -> keywords;
        }

    }


    /**
     * https://docs.microsoft.com/en-us/sql/t-sql/data-types/data-type-synonyms-transact-sql?view=sql-server-2017
     */
    enum SynonymsMap {

        I;

        private Map<Class<? extends Synonyms>,Function<Synonyms,DataType>> mappers = new LinkedHashMap<>();


        /**
         * Default mapping
         */
        {
            mappers.put(Binary_varying.class,(s) -> new VarBinary());

            mappers.put(char_varying.class,(s) -> new VarChar());

            mappers.put(character.class,(s) -> {
                character c = (character) s;
                if(!c.lengthUsed()
                        || c.length() == 1){
                    return new Char();
                }else{
                    Char r = new Char();
                    r.length(c.length());
                    return r;
                }
            });

            mappers.put(character_varying.class,(s) -> {
                character_varying c = (character_varying) s;
                VarChar r = new VarChar();
                r.length(c.length());
                return r;
            });

            mappers.put(Dec.class,(s) -> new Decimal());

            mappers.put(Double_precision.class,(s) -> new Float());

            mappers.put(float$.class,(s) -> {
                float$ c = (float$) s;
                if(c.lengthUsed()){
                    if(c.length() >= 1
                            && c.length() <= 7){
                        return new Real();
                    }else if(c.length() >= 8
                            && c.length() <= 15){
                        Float r = new Float();
                        r.length(c.length());
                        return r;
                    }
                }
                return new Float();
            });

            mappers.put(integer.class,(s) -> new Int());

            mappers.put(national_char.class,(s) -> {
                national_char c = (national_char) s;
                NChar r = new NChar();
                r.length(c.length());
                return r;
            });

            mappers.put(national_character.class,(s) -> {
                national_character c = (national_character) s;
                NChar r = new NChar();
                r.length(c.length());
                return r;
            });

            mappers.put(national_char_varying.class,(s) -> {
                national_char_varying c = (national_char_varying) s;
                NVarChar r = new NVarChar();
                r.length(c.length());
                return r;
            });

            mappers.put(national_character_varying.class,(s) -> {
                national_character_varying c = (national_character_varying) s;
                NVarChar r = new NVarChar();
                r.length(c.length());
                return r;
            });

            mappers.put(national_text.class,(s) -> new NText());

            mappers.put(timestamp.class,(s) -> new RowVersion());
        }


        Optional<DataType> toDataType(Synonyms synonyms){
            Function<Synonyms,DataType> f = mappers.get(synonyms.getClass());
            return Optional.of(f)
                    .map(f1 -> f1.apply(synonyms));
        }
    }

}
