package com.xy.xsql.orm.data.sql.sentence;

import com.xy.xsql.orm.data.sql.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class ElementsSentence extends Sentence<List<Element>>
        implements Element  {

    public ElementsSentence(List<Element> data) {
        super(data);
    }
}
