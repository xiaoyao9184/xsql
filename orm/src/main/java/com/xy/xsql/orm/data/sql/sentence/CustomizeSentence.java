package com.xy.xsql.orm.data.sql.sentence;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Sentence;

import java.util.List;

/**
 * 元素语句
 * Created by xiaoyao9184 on 2016/10/15.
 */
public abstract class CustomizeSentence extends Sentence<Void>
        implements Element {

    public CustomizeSentence() {
        super(null);
    }

    public abstract BaseElementsSentence toBaseElementsSentence();

    public String toString(){
        return this.toBaseElementsSentence().toString();
    }

}
