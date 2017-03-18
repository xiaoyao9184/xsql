package com.xy.xsql.tsql.core;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class MockParent<T> {

    private T buidData;

    public T getBuidData() {
        return buidData;
    }

    public void setBuidData(T buidData) {
        this.buidData = buidData;
    }

    @SuppressWarnings("unchecked")
    public T get() {
        return buidData;
    }
}
