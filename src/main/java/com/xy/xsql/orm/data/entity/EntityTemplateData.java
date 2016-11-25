package com.xy.xsql.orm.data.entity;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * 支持主键、状态字段、基本字段、外键字段、参数字段、排序字段
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class EntityTemplateData {

    private EntityTable tableName = null;

    private List<EntityKey> tableKey = null;

    private EntityStatus tableStatus = null;

    private List<Field> tableField = null;

    private List<EntityColumn> tableColumn = null;

    private List<EntityLink> tableEntity = null;

    private List<EntityParam> tableParam = null;

    private List<EntityOrder> tableOrder = null;

    /**
     * 表名
     */
    public EntityTable getTableName() {
        return tableName;
    }

    public void setTableName(EntityTable tableName) {
        this.tableName = tableName;
    }

    /**
     * 特殊字段：主键
     */
    public List<EntityKey> getTableKey() {
        return tableKey;
    }

    public void setTableKey(List<EntityKey> tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 特殊字段：状态
     */
    public EntityStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(EntityStatus tableStatus) {
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
    public List<EntityColumn> getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(List<EntityColumn> tableColumn) {
        this.tableColumn = tableColumn;
    }

    /**
     * 相关实体集合(标注为TableEntity)
     */
    public List<EntityLink> getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(List<EntityLink> tableEntity) {
        this.tableEntity = tableEntity;
    }

    /**
     * 查询条件集合(标注为TableParam)
     */
    public List<EntityParam> getTableParam() {
        return tableParam;
    }

    public void setTableParam(List<EntityParam> tableParam) {
        this.tableParam = tableParam;
    }

    /**
     * 查询排序集合(标注为TableOrder)
     */
    public List<EntityOrder> getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(List<EntityOrder> tableOrder) {
        this.tableOrder = tableOrder;
    }
}
