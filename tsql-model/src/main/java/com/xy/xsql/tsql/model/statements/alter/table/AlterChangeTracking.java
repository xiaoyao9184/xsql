package com.xy.xsql.tsql.model.statements.alter.table;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterChangeTracking implements Item {

    private boolean useEnable;
    private Boolean useTrackUpdatedOn;

    public boolean isUseEnable() {
        return useEnable;
    }

    public void setUseEnable(boolean useEnable) {
        this.useEnable = useEnable;
    }

    public Boolean getUseTrackUpdatedOn() {
        return useTrackUpdatedOn;
    }

    public void setUseTrackUpdatedOn(Boolean useTrackUpdatedOn) {
        this.useTrackUpdatedOn = useTrackUpdatedOn;
    }
}
