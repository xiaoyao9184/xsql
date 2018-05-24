package com.xy.xsql.tsql.builder.chain.datatypes.table;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * ColumnName Factory
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameFactory {

    /**
     * Quick build
     * @param tableName TableName
     * @param name name
     * @return ColumnName
     */
    public static ColumnName c(TableName tableName, String name){
        return new ColumnName(tableName,name);
    }

    /**
     * Quick build
     * @param name name
     * @return ColumnName
     */
    public static ColumnName c(String name){
        return new ColumnName(name);
    }

    /**
     * like this [Server Name,][Database Name,][Schema Name,][Table Name,]Column Name
     * @param server_db_schema_table_column Max length is 4
     * @return ColumnName
     */
    @SuppressWarnings("unchecked")
    public static ColumnName c(String... server_db_schema_table_column){
        if(server_db_schema_table_column.length == 1){
            return c(server_db_schema_table_column[0]);
        }
        List<String> listReversedOrder = reverse(server_db_schema_table_column);

        ColumnName columnName = new ColumnName();
        TableName tableName = new TableName();
        columnName.setTable(tableName);
        setter(listReversedOrder,
                columnName::setName,
                tableName::setTableOrViewName,
                tableName::setSchemaName,
                tableName::setDatabaseName,
                tableName::setServerName);

        return columnName;
    }

}
