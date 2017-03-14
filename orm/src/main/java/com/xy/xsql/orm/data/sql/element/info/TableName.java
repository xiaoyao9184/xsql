package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
@Deprecated
public class TableName
        extends Name<TableName>
        implements Element, Cloneable {

    private String serverName;
    private String databaseName;
    private String schemaName;
    private String tableOrViewName;

    public TableName(String tableName) {
        this.tableOrViewName = tableName;
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


    @Override
    public String getName() {
        return tableOrViewName;
    }

    @Override
    public String getFullName() {
        List<String> nameList = new LinkedList<>();
        nameList.add(serverName);
        nameList.add(databaseName);
        nameList.add(schemaName);
        nameList.add(tableOrViewName);
        nameList.removeAll(Collections.singleton(null));
        return StringUtils.join(nameList,".");
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
