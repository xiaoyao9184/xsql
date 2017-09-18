package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterConstraint implements Item {

    private boolean useWith;
    private boolean useEnable;
    private List<String> constraintNames;

    public boolean isUseWith() {
        return useWith;
    }

    public void setUseWith(boolean useWith) {
        this.useWith = useWith;
    }

    public boolean isUseEnable() {
        return useEnable;
    }

    public void setUseEnable(boolean useEnable) {
        this.useEnable = useEnable;
    }

    public List<String> getConstraintNames() {
        return constraintNames;
    }

    public void setConstraintNames(List<String> constraintNames) {
        this.constraintNames = constraintNames;
    }
}
