package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.TableName;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableNameBuilder {

    public static TableName t(String name){
        return new TableName(name);
    }

    /**
     * like this [Server Name,][Database Name,][Schema Name,]Table Name
     * @param server_db_schema_table Max length is 4
     * @return
     */
    public static TableName t(String... server_db_schema_table){
        List<String> listReversedOrder = reverse(server_db_schema_table);

        TableName tableName = new TableName();
        setter(listReversedOrder,
                tableName::setTableOrViewName,
                tableName::setSchemaName,
                tableName::setDatabaseName,
                tableName::setServerName);

        return tableName;
    }
}
