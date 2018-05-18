package com.xy.xsql.tsql.model.datatypes.table.column;

import com.xy.xsql.tsql.model.statements.alter.table.Add;
import com.xy.xsql.tsql.model.statements.create.table.DiskBasedCreateTable;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class ColumnSetDefinition
        implements DiskBasedCreateTable.Item, Add.AddItem {
    //column_set_name XML COLUMN_SET FOR ALL_SPARSE_COLUMNS
    private String columnSetName;

    public String getColumnSetName() {
        return columnSetName;
    }

    public void setColumnSetName(String columnSetName) {
        this.columnSetName = columnSetName;
    }
}
