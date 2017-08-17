package com.xy.xsql.tsql.model.element.column;

import com.xy.xsql.tsql.model.statement.ddl.create.CreateTable;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class ColumnSetDefinition
        implements CreateTable.DiskBasedColumn {
    //column_set_name XML COLUMN_SET FOR ALL_SPARSE_COLUMNS
    private String columnSetName;

    public String getColumnSetName() {
        return columnSetName;
    }

    public void setColumnSetName(String columnSetName) {
        this.columnSetName = columnSetName;
    }
}
