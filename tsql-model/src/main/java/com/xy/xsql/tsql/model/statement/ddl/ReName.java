package com.xy.xsql.tsql.model.statement.ddl;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

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

    private TableName tableName;
    private String newTableName;

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public String getNewTableName() {
        return newTableName;
    }

    public void setNewTableName(String newTableName) {
        this.newTableName = newTableName;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder builder = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.Key.RENAME)
                .append(Keywords.Key.OBJECT)
                .append(tableName)
                .append(Keywords.TO)
                .append(newTableName);

        return builder.build();
    }
}
