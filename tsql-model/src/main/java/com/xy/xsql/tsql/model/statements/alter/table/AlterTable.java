package com.xy.xsql.tsql.model.statements.alter.table;

import com.xy.xsql.tsql.model.datatypes.table.TableName;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/alter-table-transact-sql
 *
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterTable {

    private TableName tableName;
    private Item item;

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
