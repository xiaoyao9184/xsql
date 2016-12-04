package com.xy.xsql.orm.data.param;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public class ArgSql {
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
    public ArgSql withSql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Set c args
     * @param argList args args
     * @return This
     */
    public ArgSql withArgs(List<Object> argList) {
        this.args = argList.toArray();
        return  this;
    }
}
