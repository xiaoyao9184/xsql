package com.xy.xsql.tsql.model.element.constraint;

/**
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class NullOrNotNull implements Constraint {
    private boolean useNull;

    public boolean isUseNull() {
        return useNull;
    }

    public void setUseNull(boolean useNull) {
        this.useNull = useNull;
    }
}
