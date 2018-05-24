package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.model.datatypes.table.TableName;

import java.util.*;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * ColumnName Factory
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableNameFactory {

    /**
     * Quick build
     * @param name name
     * @return TableName
     */
    public static TableName t(String name){
        return new TableName(name);
    }

    /**
     * Quick build
     * like this [Server Name,][Database Name,][Schema Name,]Table Name
     * @param server_db_schema_table Max length is 4
     * @return TableName
     */
    public static TableName t(String... server_db_schema_table){
        List<String> listReversedOrder = reverse(server_db_schema_table);

        TableName tableName = new TableName();
        //noinspection unchecked
        setter(listReversedOrder,
                tableName::setTableOrViewName,
                tableName::setSchemaName,
                tableName::setDatabaseName,
                tableName::setServerName);

        return tableName;
    }
}
