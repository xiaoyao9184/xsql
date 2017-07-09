package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.statement.Statement;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 *

-- Syntax for SQL Server and Azure SQL Database

        DECLARE
        {
        { @local_variable [AS] data_type  | [ = value ] }
        | { @cursor_variable_name CURSOR }
        } [,...n]
        | { @table_variable_name [AS] <table_type_definition> }

<table_type_definition> ::=
        TABLE ( { <column_definition> | <table_constraint> } [ ,... ] )

<column_definition> ::=
        column_name { scalar_data_type | AS computed_column_expression }
        [ COLLATE collation_name ]
        [ [ DEFAULT constant_expression ] | IDENTITY [ (seed ,increment ) ] ]
        [ ROWGUIDCOL ]
        [ <column_constraint> ]

<column_constraint> ::=
        { [ NULL | NOT NULL ]
        | [ PRIMARY KEY | UNIQUE ]
        | CHECK ( logical_expression )
        | WITH ( <index_option > )
        }

<table_constraint> ::=
        { { PRIMARY KEY | UNIQUE } ( column_name [ ,... ] )
        | CHECK ( search_condition )
        }

<index_option> ::=
        See CREATE TABLE for index option syntax.

 *
 */
public class DeclareVariable {

    /*
    {
        { @local_variable [AS] data_type  | [ = value ] }
      | { @cursor_variable_name CURSOR }
    } [,...n]
     */
    private List<Item> items;
    //{ @table_variable_name [AS] <table_type_definition> }
    private LocalVariable tableVariableName;
    private boolean useAs;
    private TableTypeDefinition tableTypeDefinition;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalVariable getTableVariableName() {
        return tableVariableName;
    }

    public void setTableVariableName(LocalVariable tableVariableName) {
        this.tableVariableName = tableVariableName;
    }

    public boolean isUseAs() {
        return useAs;
    }

    public void setUseAs(boolean useAs) {
        this.useAs = useAs;
    }

    public TableTypeDefinition getTableTypeDefinition() {
        return tableTypeDefinition;
    }

    public void setTableTypeDefinition(TableTypeDefinition tableTypeDefinition) {
        this.tableTypeDefinition = tableTypeDefinition;
    }


    /**
     * {
     { @local_variable [AS] data_type  | [ = value ] }
     | { @cursor_variable_name CURSOR }
     }
     */
    public static class Item {

        //@local_variable
        //@cursor_variable_name
        private LocalVariable localVariable;
        private boolean useAs;
        //data_type
        private DataType dataType;
        //value
        private Expression value;
        //CURSOR
        private boolean useCursor;

        public LocalVariable getLocalVariable() {
            return localVariable;
        }

        public void setLocalVariable(LocalVariable localVariable) {
            this.localVariable = localVariable;
        }

        public boolean isUseAs() {
            return useAs;
        }

        public void setUseAs(boolean useAs) {
            this.useAs = useAs;
        }

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }

        public Expression getValue() {
            return value;
        }

        public void setValue(Expression value) {
            this.value = value;
        }

        public boolean isUseCursor() {
            return useCursor;
        }

        public void setUseCursor(boolean useCursor) {
            this.useCursor = useCursor;
        }
    }
}
