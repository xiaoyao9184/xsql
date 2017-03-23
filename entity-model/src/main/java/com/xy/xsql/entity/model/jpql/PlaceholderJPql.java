package com.xy.xsql.entity.model.jpql;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public class PlaceholderJPql {
    private String sql;
    private Object[] args;


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    /**
     * Set SQL
     * @param sql SQL
     * @return This
     */
    public PlaceholderJPql withSql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Set c args
     * @param argList args args
     * @return This
     */
    public PlaceholderJPql withArgs(List<Object> argList) {
        this.args = argList.toArray();
        return  this;
    }
}
