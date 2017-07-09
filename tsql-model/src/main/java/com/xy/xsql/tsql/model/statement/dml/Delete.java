package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms189835.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [ ,...n ] ]
 DELETE
 [ TOP ( expression ) [ PERCENT ] ]
 [ FROM ]
 { { table_alias
 | <object>
 | rowset_function_limited
 [ WITH ( table_hint_limited [ ...n ] ) ] }
 | @table_variable
 }
 [ <OUTPUT Clause> ]
 [ FROM table_source [ ,...n ] ]
 [ WHERE { <search_condition>
 | { [ CURRENT OF
 { { [ GLOBAL ] cursor_name }
 | cursor_variable_name
 }
 ]
 }
 }
 ]
 [ OPTION ( <Query Hint> [ ,...n ] ) ]
 [; ]

 <object> ::=
 {
 [ server_name.database_name.schema_name.
 | database_name. [ schema_name ] .
 | schema_name.
 ]
 table_or_view_name
 }

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 DELETE FROM [database_name . [ schema ] . | schema. ] table_name
 [ WHERE <search_condition> ]
 [ OPTION ( <query_options> [ ,...n ]  ) ]
 [; ]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Delete implements Statement {
    //<WITH Clause>
    private With with;
    //<TOP Clause>
    private Top top;

    //FROM
    private boolean useForm;
    /*
    { { table_alias
      | <object>
      | rowset_function_limited
      [ WITH ( table_hint_limited [ ...n ] ) ] }
      | @table_variable
    }
     */
    private Alias<Void> tableAlias;
    private TableName tableName;
    //TODO rowset_function_limited
    private List<TableHintLimited> tableHintLimitedList;
    //TODO @table_variable

    //<OUTPUT Clause>
    private Output output;
    //<FROM Clause>
    private From from;
    //<WHERE Clause>
    private Where where;
    //<OPTION Clause>
    private Option option;


    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public boolean isUseForm() {
        return useForm;
    }

    public void setUseForm(boolean useForm) {
        this.useForm = useForm;
    }

    public Alias<Void> getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(Alias<Void> tableAlias) {
        this.tableAlias = tableAlias;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<TableHintLimited> getTableHintLimitedList() {
        return tableHintLimitedList;
    }

    public void setTableHintLimitedList(List<TableHintLimited> tableHintLimitedList) {
        this.tableHintLimitedList = tableHintLimitedList;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public With getWith() {
        return with;
    }

    public void setWith(With with) {
        this.with = with;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
