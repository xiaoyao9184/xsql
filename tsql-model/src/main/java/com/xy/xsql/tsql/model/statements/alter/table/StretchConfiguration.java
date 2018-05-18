package com.xy.xsql.tsql.model.statements.alter.table;

import com.xy.xsql.tsql.model.datatypes.table.table.TableStretchOptions;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class StretchConfiguration implements Item {

    private Boolean useOn;
    private TableStretchOptions tableStretchOptions;
    private List<TableStretchOptions> tableStretchOptionsList;

    public Boolean getUseOn() {
        return useOn;
    }

    public void setUseOn(Boolean useOn) {
        this.useOn = useOn;
    }

    public TableStretchOptions getTableStretchOptions() {
        return tableStretchOptions;
    }

    public void setTableStretchOptions(TableStretchOptions tableStretchOptions) {
        this.tableStretchOptions = tableStretchOptions;
    }

    public List<TableStretchOptions> getTableStretchOptionsList() {
        return tableStretchOptionsList;
    }

    public void setTableStretchOptionsList(List<TableStretchOptions> tableStretchOptionsList) {
        this.tableStretchOptionsList = tableStretchOptionsList;
    }
}
