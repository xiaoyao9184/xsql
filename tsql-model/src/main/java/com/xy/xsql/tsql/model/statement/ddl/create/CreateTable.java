package com.xy.xsql.tsql.model.statement.ddl.create;

import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.element.index.Partition;
import com.xy.xsql.tsql.model.element.table.TableOption;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms174979.aspx
 *

 --Simple CREATE TABLE Syntax (common if not using options)
 CREATE TABLE
 [ database_name . [ schema_name ] . | schema_name . ] table_name
 ( { <column_definition> } [ ,...n ] )
 [ ; ]

 *
 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class CreateTable implements Statement {

    private TableName tableName;
    private List<ColumnDefinition> columnDefinitionList;


    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<ColumnDefinition> getColumnDefinitionList() {
        return columnDefinitionList;
    }

    public void setColumnDefinitionList(List<ColumnDefinition> columnDefinitionList) {
        this.columnDefinitionList = columnDefinitionList;
    }

    public interface DiskBasedColumn {}
    public interface MemoryBasedColumn {}

    /**
     * --Disk-Based CREATE TABLE Syntax
     */
    public static class CreateDiskTable extends CreateTable {
        //[ AS FileTable ]
        private boolean useAsFileTable;
        //{   <column_definition>
        //| <computed_column_definition>
        //| <column_set_definition>
        //| [ <table_constraint> ]
        //| [ <table_index> ] }
        private List<DiskBasedColumn> items;

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

        public List<DiskBasedColumn> getItems() {
            return items;
        }

        public void setItems(List<DiskBasedColumn> items) {
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
    }

    /**
     * --Memory optimized CREATE TABLE Syntax
     */
    public static class CreateMemoryTable extends CreateTable {
        //{ <column_definition>
        //| [ <table_constraint> ] [ ,... n ]
        //| [ <table_index> ]
        //[ ,... n ] }
        private List<MemoryBasedColumn> items;

        //[ PERIOD FOR SYSTEM_TIME ( system_start_time_column_name
        //     , system_end_time_column_name ) ]
        private String systemStartTimeColumnName;
        private String systemEndTimeColumnName;

        //[ WITH ( <table_option> [ ,...n ] ) ]
        private List<TableOption> tableOptions;

        public List<MemoryBasedColumn> getItems() {
            return items;
        }

        public void setItems(List<MemoryBasedColumn> items) {
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
    }

}
