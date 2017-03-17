package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.DataType;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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
public class DeclareVariable implements Statement {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(Keywords.DECLARE)
                .append(items)
                .build();
    }


    public static class Item implements Block {

        //@local_variable
        private VariableString localVariable;
        //data_type
        private DataType dataType;
        //value
        private Expression value;

        public VariableString getLocalVariable() {
            return localVariable;
        }

        public void setLocalVariable(VariableString localVariable) {
            this.localVariable = localVariable;
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

        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(localVariable)
                    .append(Keywords.AS)
                    .append(dataType);
            if(value != null){
                 b.append(Assignment.ASSIGNMENT)
                        .append(value);
            }

            return b.build();
        }
    }
}
