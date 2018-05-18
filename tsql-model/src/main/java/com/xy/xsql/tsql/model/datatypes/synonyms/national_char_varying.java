package com.xy.xsql.tsql.model.datatypes.synonyms;

import com.xy.xsql.tsql.model.datatypes.DataType;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class national_char_varying
        extends DataType.FixedLengthDataType
        implements Synonyms.ISOKeywordNamed {

    @Override
    public Synonyms.Keywords keyword() {
        return Synonyms.Keywords.national_char_varying;
    }
}
