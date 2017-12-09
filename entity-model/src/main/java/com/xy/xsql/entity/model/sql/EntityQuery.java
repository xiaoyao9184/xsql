package com.xy.xsql.entity.model.sql;

import com.xy.xsql.entity.api.meta.TableMeta;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/10/19
 */
public class EntityQuery {

    private TableMeta tableMain;
    private List<TableJoin> tableJoins;
    private List<ColumnReturn> columnReturns;
    private List<EntityCondition> entityConditions;
    private List<ColumnOrder> columnOrders;

    public TableMeta getTableMain() {
        return tableMain;
    }

    public void setTableMain(TableMeta tableMain) {
        this.tableMain = tableMain;
    }

    public List<TableJoin> getTableJoins() {
        return tableJoins;
    }

    public void setTableJoins(List<TableJoin> tableJoins) {
        this.tableJoins = tableJoins;
    }

    public List<ColumnReturn> getColumnReturns() {
        return columnReturns;
    }

    public void setColumnReturns(List<ColumnReturn> columnReturns) {
        this.columnReturns = columnReturns;
    }

    public List<EntityCondition> getEntityConditions() {
        return entityConditions;
    }

    public void setEntityConditions(List<EntityCondition> entityConditions) {
        this.entityConditions = entityConditions;
    }

    public List<ColumnOrder> getColumnOrders() {
        return columnOrders;
    }

    public void setColumnOrders(List<ColumnOrder> columnOrders) {
        this.columnOrders = columnOrders;
    }
}
