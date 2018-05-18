package com.xy.xsql.tsql.model.statements.create.table;

import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statements.Statement;

import java.util.List;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql#syntax
 *

 --Simple CREATE TABLE Syntax (common if not using options)
 CREATE TABLE
 [ database_name . [ schema_name ] . | schema_name . ] table_name
 ( { <column_definition> } [ ,...n ] )
 [ ; ]

 *
 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class SimpleCreateTable implements Statement {

    private TableName tableName;
    private List<ColumnDefinition> columnDefinitionList;


    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<ColumnDefinition> getColumnDefinitionList() {
        return columnDefinitionList;
    }

    public void setColumnDefinitionList(List<ColumnDefinition> columnDefinitionList) {
        this.columnDefinitionList = columnDefinitionList;
    }

}
