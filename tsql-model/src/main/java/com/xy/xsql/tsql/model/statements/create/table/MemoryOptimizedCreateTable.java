package com.xy.xsql.tsql.model.statements.create.table;

import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql#syntax
 * --Memory optimized CREATE TABLE Syntax
 *
 * Created by xiaoyao9184 on 2017/8/18.
 */
public class MemoryOptimizedCreateTable extends SimpleCreateTable {
    //{ <column_definition>
    //| [ <table_constraint> ] [ ,... n ]
    //| [ <table_index> ]
    //[ ,... n ] }
    private List<Item> items;

    //[ PERIOD FOR SYSTEM_TIME ( system_start_time_column_name
    //     , system_end_time_column_name ) ]
    private String systemStartTimeColumnName;
    private String systemEndTimeColumnName;

    //[ WITH ( <table_option> [ ,...n ] ) ]
    private List<TableOption> tableOptions;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSystemStartTimeColumnName() {
        return systemStartTimeColumnName;
    }

    public void setSystemStartTimeColumnName(String systemStartTimeColumnName) {
        this.systemStartTimeColumnName = systemStartTimeColumnName;
    }

    public String getSystemEndTimeColumnName() {
        return systemEndTimeColumnName;
    }

    public void setSystemEndTimeColumnName(String systemEndTimeColumnName) {
        this.systemEndTimeColumnName = systemEndTimeColumnName;
    }

    public List<TableOption> getTableOptions() {
        return tableOptions;
    }

    public void setTableOptions(List<TableOption> tableOptions) {
        this.tableOptions = tableOptions;
    }


    /**
     *
     */
    public interface Item {}
}
