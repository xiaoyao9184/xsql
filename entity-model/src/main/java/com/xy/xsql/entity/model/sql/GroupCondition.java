package com.xy.xsql.entity.model.sql;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class GroupCondition extends EntityCondition {
    private List<EntityCondition> internal;

    public List<EntityCondition> getInternal() {
        return internal;
    }

    public void setInternal(List<EntityCondition> internal) {
        this.internal = internal;
    }
}
