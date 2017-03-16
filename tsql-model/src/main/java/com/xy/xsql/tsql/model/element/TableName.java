package com.xy.xsql.tsql.model.element;

import com.xy.xsql.tsql.model.Block;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *

 <object> ::=
 {
 [ server_name . database_name . schema_name .
 | database_name .[ schema_name ] .
 | schema_name .
 ]
 table_or_view_name
 }

 *
 * Created by xiaoyao9184 on 2017/1/9.
 */
public class TableName
        implements Block, Cloneable {

    private String serverName;
    private String databaseName;
    private String schemaName;
    private String tableOrViewName;

    public TableName(String tableName) {
        this.tableOrViewName = tableName;
    }

    public TableName() {

    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableOrViewName() {
        return tableOrViewName;
    }

    public void setTableOrViewName(String tableOrViewName) {
        this.tableOrViewName = tableOrViewName;
    }


    public String getFullName() {
        List<String> nameList = new LinkedList<>();
        nameList.add(serverName);
        nameList.add(databaseName);
        nameList.add(schemaName);
        nameList.add(tableOrViewName);
        nameList.removeAll(Collections.singleton(null));
        return StringUtils.join(nameList,".");
    }

    public TableName clone() {
        TableName tableName = new TableName(tableOrViewName);
        tableName.setServerName(serverName);
        tableName.setDatabaseName(databaseName);
        tableName.setSchemaName(schemaName);
        return tableName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public List<Block> toBlockList() {
        return null;
    }
}
