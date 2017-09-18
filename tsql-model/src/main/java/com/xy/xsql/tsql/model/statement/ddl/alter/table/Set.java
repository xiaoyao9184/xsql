package com.xy.xsql.tsql.model.statement.ddl.alter.table;

import com.xy.xsql.tsql.model.element.index.Partition;

/**
 * Created by xiaoyao9184 on 2017/9/17
 */
public class Set implements Item {

    private Partition fileStreamOn;

    private boolean useSystemVersioningOn;
    private String schemaName;
    private String historyTableName;
    private Boolean useDataConsistencyCheck;
    private Boolean useHistoryRetentionPeriod;
    private Integer historyRetentionPeriodNumber;
    private HistoryRetentionPeriodUnit historyRetentionPeriodUnit;

    public Partition getFileStreamOn() {
        return fileStreamOn;
    }

    public void setFileStreamOn(Partition fileStreamOn) {
        this.fileStreamOn = fileStreamOn;
    }

    public boolean isUseSystemVersioningOn() {
        return useSystemVersioningOn;
    }

    public void setUseSystemVersioningOn(boolean useSystemVersioningOn) {
        this.useSystemVersioningOn = useSystemVersioningOn;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getHistoryTableName() {
        return historyTableName;
    }

    public void setHistoryTableName(String historyTableName) {
        this.historyTableName = historyTableName;
    }

    public Boolean getUseDataConsistencyCheck() {
        return useDataConsistencyCheck;
    }

    public void setUseDataConsistencyCheck(Boolean useDataConsistencyCheck) {
        this.useDataConsistencyCheck = useDataConsistencyCheck;
    }

    public Boolean getUseHistoryRetentionPeriod() {
        return useHistoryRetentionPeriod;
    }

    public void setUseHistoryRetentionPeriod(Boolean useHistoryRetentionPeriod) {
        this.useHistoryRetentionPeriod = useHistoryRetentionPeriod;
    }

    public Integer getHistoryRetentionPeriodNumber() {
        return historyRetentionPeriodNumber;
    }

    public void setHistoryRetentionPeriodNumber(Integer historyRetentionPeriodNumber) {
        this.historyRetentionPeriodNumber = historyRetentionPeriodNumber;
    }

    public HistoryRetentionPeriodUnit getHistoryRetentionPeriodUnit() {
        return historyRetentionPeriodUnit;
    }

    public void setHistoryRetentionPeriodUnit(HistoryRetentionPeriodUnit historyRetentionPeriodUnit) {
        this.historyRetentionPeriodUnit = historyRetentionPeriodUnit;
    }

    public enum HistoryRetentionPeriodUnit {
        DAY,
        DAYS,
        WEEK,
        WEEKS,
        MONTH,
        MONTHS,
        YEAR,
        YEARS
    }
}
