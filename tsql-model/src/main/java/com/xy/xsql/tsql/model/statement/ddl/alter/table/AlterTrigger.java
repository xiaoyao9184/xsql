package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterTrigger implements Item {

    private boolean useEnable;
    private List<String> triggerNames;

    public boolean isUseEnable() {
        return useEnable;
    }

    public void setUseEnable(boolean useEnable) {
        this.useEnable = useEnable;
    }

    public List<String> getTriggerNames() {
        return triggerNames;
    }

    public void setTriggerNames(List<String> triggerNames) {
        this.triggerNames = triggerNames;
    }
}
