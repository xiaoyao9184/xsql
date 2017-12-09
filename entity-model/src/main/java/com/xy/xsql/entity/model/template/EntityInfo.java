package com.xy.xsql.entity.model.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EntityInfo
 * Entity class can describe SQL table, Sufficient information can generate SQL statements
 * Created by xiaoyao9184 on 2016/10/15.
 */
@Deprecated
public class EntityInfo {

    private Class clazz;

    private EntityTable table = null;

    private List<EntityColumn> keys = null;

    private EntityColumn status = null;

    private List<EntityColumn> columns = null;

    private List<EntityLink> links = null;

    private List<EntityParam> params = null;

    private List<EntityOrder> orders = null;


    /**
     * Entity Class
     */
    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Table
     */
    public EntityTable getTable() {
        return table;
    }

    public void setTable(EntityTable table) {
        this.table = table;
    }

    /**
     * Special Column：Key
     */
    public List<EntityColumn> getKeys() {
        return keys;
    }

    public void setKeys(List<EntityColumn> keyList) {
        this.keys = keyList;
    }

    /**
     * Special Column：Status
     */
    public EntityColumn getStatus() {
        return status;
    }

    public void setStatus(EntityColumn status) {
        this.status = status;
    }

    /**
     * Columns
     */
    public List<EntityColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<EntityColumn> columns) {
        this.columns = columns;
    }

    /**
     * Column Link to EntityInfo
     */
    public List<EntityLink> getLinks() {
        return links;
    }

    public void setLinks(List<EntityLink> links) {
        this.links = links;
    }

    /**
     * Params
     */
    public List<EntityParam> getParams() {
        return params;
    }

    public void setParams(List<EntityParam> params) {
        this.params = params;
    }

    /**
     * Orders
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
    public EntityInfo withClass(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    /**
     * set table
     * @param table EntityTable
     * @return This
     */
    public EntityInfo withTable(EntityTable table) {
        this.table = table;
        return this;
    }

    /**
     * set column
     * @param columns EntityColumn List
     * @return This
     */
    public EntityInfo withColumns(List<EntityColumn> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * set key
     * @param entityColumns EntityColumn List
     * @return This
     */
    public EntityInfo withKeys(List<EntityColumn> entityColumns) {
        this.keys = entityColumns;
        return this;
    }

    /**
     * set status
     * @param status EntityColumn
     * @return This
     */
    public EntityInfo withStatus(EntityColumn status) {
        this.status = status;
        return this;
    }

    /**
     * set links
     * @param links EntityColumn
     * @return This
     */
    public EntityInfo withLinks(List<EntityLink> links) {
        this.links = links;
        return this;
    }

    /**
     * set params
     * @param params EntityParam List
     * @return This
     */
    public EntityInfo withParams(List<EntityParam> params) {
        this.params = params;
        return this;
    }

    /**
     * set orders
     * @param orders EntityOrder List
     * @return This
     */
    public EntityInfo withOrders(List<EntityOrder> orders) {
        this.orders = orders;
        return this;
    }

    public EntityInfo withKey(EntityColumn... keys) {
        if(this.keys == null){
            this.keys = new ArrayList<>();
        }
        this.keys.addAll(Arrays.asList(keys));
        return this;
    }
    
    public EntityInfo withParam(EntityParam... params){
        if(this.params == null){
            this.params = new ArrayList<>();
        }
        this.params.addAll(Arrays.asList(params));
        return this;

    }
}
