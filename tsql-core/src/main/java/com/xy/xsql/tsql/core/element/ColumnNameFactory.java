package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * ColumnNameFactory
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameFactory {

    public static ColumnName c(TableName tableName, String name){
        return new ColumnName(tableName,name);
    }

    public static ColumnName c(String name){
        return new ColumnName(name);
    }

    /**
     * like this [Server Name,][Database Name,][Schema Name,][Table Name,]Column Name
     * @param server_db_schema_table_column Max length is 4
     * @return
     */
    public static ColumnName c(String... server_db_schema_table_column){
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
