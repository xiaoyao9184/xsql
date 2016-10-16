package com.xy.xsql.orm.data.config;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AnnotationEntityDataBuilderConfig {

    private String tablePrefix;
    private boolean supportMultipleKey;
    private boolean scanStatus;
    private boolean scanEntity;
    private boolean scanParam;
    private boolean scanOrder;

    public String getTablePrefix() {
        return tablePrefix;
    }

    public AnnotationEntityDataBuilderConfig tablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public boolean isSupportMultipleKey() {
        return supportMultipleKey;
    }

    public AnnotationEntityDataBuilderConfig supportMultipleKey(boolean supportMultipleKey) {
        this.supportMultipleKey = supportMultipleKey;
        return this;
    }

    public boolean isScanStatus() {
        return scanStatus;
    }

    public AnnotationEntityDataBuilderConfig scanStatus(boolean scanStatus) {
        this.scanStatus = scanStatus;
        return this;
    }

    public boolean isScanEntity() {
        return scanEntity;
    }

    public AnnotationEntityDataBuilderConfig scanEntity(boolean scanEntity) {
        this.scanEntity = scanEntity;
        return this;
    }

    public boolean isScanParam() {
        return scanParam;
    }

    public AnnotationEntityDataBuilderConfig scanParam(boolean scanParam) {
        this.scanParam = scanParam;
        return this;
    }

    public boolean isScanOrder() {
        return scanOrder;
    }

    public AnnotationEntityDataBuilderConfig scanOrder(boolean scanOrder) {
        this.scanOrder = scanOrder;
        return this;
    }

}
