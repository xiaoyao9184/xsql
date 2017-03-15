package com.xy.xsql.tsql.model.statement.ddl.create;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.ColumnDefinition;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms174979.aspx
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
public class CreateTable implements Statement {

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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.CREATE)
                .append(Keywords.TABLE)
                .append(tableName)
                .append(Other.GROUP_START)
                .append(columnDefinitionList)
                .append(Other.GROUP_END);

        return b.build();
    }
}
