package com.xy.xsql.tsql.model.statements.create.table;

import com.xy.xsql.tsql.model.datatypes.table.index.Partition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableOption;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql#syntax
 * --Disk-Based CREATE TABLE Syntax
 *
 * Created by xiaoyao9184 on 2017/8/18.
 */
public class DiskBasedCreateTable extends SimpleCreateTable {
    //[ AS FileTable ]
    private boolean useAsFileTable;
    //{   <column_definition>
    //| <computed_column_definition>
    //| <column_set_definition>
    //| [ <table_constraint> ]
    //| [ <table_index> ] }
    private List<Item> items;

    //[ PERIOD FOR SYSTEM_TIME ( system_start_time_column_name
    //     , system_end_time_column_name ) ]
    private String systemStartTimeColumnName;
    private String systemEndTimeColumnName;

    //[ ON { partition_scheme_name ( partition_column_name )
    //   | filegroup
    //   | "default" } ]
    private Partition on;

    //[ TEXTIMAGE_ON { filegroup | "default" } ]
    private Partition textImageOn;

    //[ FILESTREAM_ON { partition_scheme_name
    //    | filegroup
    //    | "default" } ]
    private Partition fileStreamOn;

    //[ WITH ( <table_option> [ ,...n ] ) ]
    private List<TableOption> tableOptions;

    public boolean isUseAsFileTable() {
        return useAsFileTable;
    }

    public void setUseAsFileTable(boolean useAsFileTable) {
        this.useAsFileTable = useAsFileTable;
    }

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

    public Partition getOn() {
        return on;
    }

    public void setOn(Partition on) {
        this.on = on;
    }

    public Partition getTextImageOn() {
        return textImageOn;
    }

    public void setTextImageOn(Partition textImageOn) {
        this.textImageOn = textImageOn;
    }

    public Partition getFileStreamOn() {
        return fileStreamOn;
    }

    public void setFileStreamOn(Partition fileStreamOn) {
        this.fileStreamOn = fileStreamOn;
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
