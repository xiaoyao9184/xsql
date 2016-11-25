package com.xy.xsql.orm.data.sql.sentence;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Sentence;

/**
 * 文本语句
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class StringSentence extends Sentence<String>
        implements Element  {

    public StringSentence(String data) {
        super(data);
    }

}
