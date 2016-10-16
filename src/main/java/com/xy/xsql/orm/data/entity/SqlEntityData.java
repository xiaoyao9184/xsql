package com.xy.xsql.orm.data.entity;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * 支持主键、状态字段、基本字段、外键字段、参数字段、排序字段
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class SqlEntityData {

    private SqlTable tableName = null;

    private List<SqlKey> tableKey = null;

    private SqlStatus tableStatus = null;

    private List<Field> tableField = null;

    private List<SqlColumn> tableColumn = null;

    private List<SqlEntity> tableEntity = null;

    private List<SqlParam> tableParam = null;

    private List<SqlOrder> tableOrder = null;

    /**
     * 表名
     */
    public SqlTable getTableName() {
        return tableName;
    }

    public void setTableName(SqlTable tableName) {
        this.tableName = tableName;
    }

    /**
     * 特殊字段：主键
     */
    public List<SqlKey> getTableKey() {
        return tableKey;
    }

    public void setTableKey(List<SqlKey> tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 特殊字段：状态
     */
    public SqlStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(SqlStatus tableStatus) {
        this.tableStatus = tableStatus;
    }

    /**
     * 字段集合
     */
    public List<Field> getTableField() {
        return tableField;
    }

    public void setTableField(List<Field> tableField) {
        this.tableField = tableField;
    }

    /**
     * 字段集合(标注为TableColumn)
     */
    public List<SqlColumn> getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(List<SqlColumn> tableColumn) {
        this.tableColumn = tableColumn;
    }

    /**
     * 相关实体集合(标注为TableEntity)
     */
    public List<SqlEntity> getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(List<SqlEntity> tableEntity) {
        this.tableEntity = tableEntity;
    }

    /**
     * 查询条件集合(标注为TableParam)
     */
    public List<SqlParam> getTableParam() {
        return tableParam;
    }

    public void setTableParam(List<SqlParam> tableParam) {
        this.tableParam = tableParam;
    }

    /**
     * 查询排序集合(标注为TableOrder)
     */
    public List<SqlOrder> getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(List<SqlOrder> tableOrder) {
        this.tableOrder = tableOrder;
    }
}
