package com.xy.xsql.entity.model.sql;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public abstract class EntityCondition {
    protected Boolean useAnd;

    public Boolean getUseAnd() {
        return useAnd;
    }

    public void setUseAnd(Boolean useAnd) {
        this.useAnd = useAnd;
    }

}
