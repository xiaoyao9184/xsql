package com.xy.xsql.tsql.model.statements.alter.table;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class AlterFiletableOption implements Item {

    private Boolean useEnableFileTableNamespace;
    private String directoryName;

    public Boolean getUseEnableFileTableNamespace() {
        return useEnableFileTableNamespace;
    }

    public void setUseEnableFileTableNamespace(Boolean useEnableFileTableNamespace) {
        this.useEnableFileTableNamespace = useEnableFileTableNamespace;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}
