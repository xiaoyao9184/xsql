package com.xy.xsql.tsql.model.statement.ddl.drop;

import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms173790.aspx
 *

 -- Syntax for SQL Server and Azure SQL Database

 DROP TABLE [ IF EXISTS ] [ database_name . [ schema_name ] . | schema_name . ]
 table_name [ ,...n ]
 [ ; ]

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 DROP TABLE [ database_name . [ schema_name ] . | schema_name . ] table_name
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class DropTable implements Statement {

    private boolean useIfExists;
    private List<TableName> tableNameList;


    public boolean isUseIfExists() {
        return useIfExists;
    }

    public void setUseIfExists(boolean useIfExists) {
        this.useIfExists = useIfExists;
    }

    public List<TableName> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<TableName> tableNameList) {
        this.tableNameList = tableNameList;
    }

}
