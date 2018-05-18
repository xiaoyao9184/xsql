package com.xy.xsql.tsql.model.statements;

import com.xy.xsql.tsql.model.datatypes.table.TableName;

/**
 *
 *

 -- Syntax for Azure SQL Data Warehouse

 -- Rename a table.
 RENAME OBJECT [ :: ]  [ [ database_name .  [schema_name ] ] . ] | [schema_name . ] ] table_name TO new_table_name
 [;]

 *

 -- Syntax for Parallel Data Warehouse

 -- Rename a table
 RENAME OBJECT [::] [ [ database_name . [ schema_name ] . ] | [ schema_name . ] ] table_name TO new_table_name
 [;]

 -- Rename a database
 RENAME DATABASE [::] database_name TO new_database_name
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class ReName implements Statement {

    private String dbName;
    private TableName tableName;
    private String newName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

}
