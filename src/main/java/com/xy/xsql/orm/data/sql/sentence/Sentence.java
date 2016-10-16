package com.xy.xsql.orm.data.sql.sentence;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public abstract class Sentence<T> implements Element  {

    protected T data;

    public Sentence(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
