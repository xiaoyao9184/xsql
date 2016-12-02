package com.xy.xsql.orm.data.entity;

import java.util.List;

/**
 *
 * 支持主键、状态字段、基本字段、外键字段、参数字段、排序字段
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class EntityTemplate {

    private Class clazz;

    private EntityTable table = null;

    private List<EntityColumn> keys = null;

    private EntityColumn status = null;

    private List<EntityColumn> columns = null;

    private List<EntityLink> links = null;

    private List<EntityParam> params = null;

    private List<EntityOrder> orders = null;


    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 表名
     */
    public EntityTable getTable() {
        return table;
    }

    public void setTable(EntityTable table) {
        this.table = table;
    }

    /**
     * 特殊字段：主键
     */
    public List<EntityColumn> getKeys() {
        return keys;
    }

    public void setKeys(List<EntityColumn> keyList) {
        this.keys = keyList;
    }

    /**
     * 特殊字段：状态
     */
    public EntityColumn getStatus() {
        return status;
    }

    public void setStatus(EntityColumn status) {
        this.status = status;
    }

    /**
     * 字段集合(标注为TableColumn)
     */
    public List<EntityColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<EntityColumn> columns) {
        this.columns = columns;
    }

    /**
     * 相关实体集合(标注为TableEntity)
     */
    public List<EntityLink> getLinks() {
        return links;
    }

    public void setLinks(List<EntityLink> links) {
        this.links = links;
    }

    /**
     * 查询条件集合(标注为TableParam)
     */
    public List<EntityParam> getParams() {
        return params;
    }

    public void setParams(List<EntityParam> params) {
        this.params = params;
    }

    /**
     * 查询排序集合(标注为TableOrder)
     */
    public List<EntityOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<EntityOrder> orders) {
        this.orders = orders;
    }


    /**
     * set class
     * @param clazz Class
     * @return This
     */
    public EntityTemplate withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    /**
     * set table
     * @param table EntityTable
     * @return This
     */
    public EntityTemplate withTable(EntityTable table) {
        this.table = table;
        return this;
    }

    /**
     * set column
     * @param columns EntityColumn List
     * @return This
     */
    public EntityTemplate withColumns(List<EntityColumn> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * set key
     * @param entityColumns EntityColumn List
     * @return This
     */
    public EntityTemplate withKeys(List<EntityColumn> entityColumns) {
        this.keys = entityColumns;
        return this;
    }

    /**
     * set status
     * @param entityStatus EntityColumn
     * @return This
     */
    public EntityTemplate withKeys(EntityColumn entityStatus) {
        this.status = entityStatus;
        return this;
    }

}
