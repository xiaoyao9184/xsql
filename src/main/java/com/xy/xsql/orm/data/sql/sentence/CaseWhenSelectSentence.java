package com.xy.xsql.orm.data.sql.sentence;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.info.Value;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class CaseWhenSelectSentence extends Sentence<Void>
        implements Element  {

    private Value caseValue;
    private List<Value> whenValues;
    private List<Value> thenValues;

    public CaseWhenSelectSentence() {
        super(null);
    }

}
