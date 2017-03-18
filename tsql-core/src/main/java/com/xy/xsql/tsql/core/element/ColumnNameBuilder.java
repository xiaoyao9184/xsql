package com.xy.xsql.tsql.core.element;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;

import java.util.List;

import static com.xy.xsql.core.ListBuilder.reverse;
import static com.xy.xsql.core.ListBuilder.setter;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnNameBuilder {

    public static ColumnName c(TableName tableName, String name){
        return new ColumnName(tableName,name);
    }

    public static ColumnName c(String name){
        return new ColumnName(name);
    }

    /**
     * like this [Server Name,][Database Name,][Schema Name,][Table Name,]Column Name
     * @param name Max length is 4
     * @return
     */
    public static ColumnName c(String... name){
        List<String> listReversedOrder = reverse(name);

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
