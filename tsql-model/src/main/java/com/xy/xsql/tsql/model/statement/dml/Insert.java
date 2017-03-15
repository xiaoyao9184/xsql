package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Output;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms174335.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [ ,...n ] ]
 INSERT
 {
 [ TOP ( expression ) [ PERCENT ] ]
 [ INTO ]
 { <object> | rowset_function_limited
 [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
 }
 {
 [ ( column_list ) ]
 [ <OUTPUT Clause> ]
 { VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
 | derived_table
 | execute_statement
 | <dml_table_source>
 | DEFAULT VALUES
 }
 }
 }
 [;]

 <object> ::=
 {
 [ server_name . database_name . schema_name .
 | database_name .[ schema_name ] .
 | schema_name .
 ]
 table_or_view_name
 }

 <dml_table_source> ::=
 SELECT <select_list>
 FROM ( <dml_statement_with_output_clause> )
 [AS] table_alias [ ( column_alias [ ,...n ] ) ]
 [ WHERE <search_condition> ]
 [ OPTION ( <query_hint> [ ,...n ] ) ]

 *

 -- External tool only syntax

 INSERT
 {
 [BULK]
 [ database_name . [ schema_name ] . | schema_name . ]
 [ table_name | view_name ]
 ( <column_definition> )
 [ WITH (
 [ [ , ] CHECK_CONSTRAINTS ]
 [ [ , ] FIRE_TRIGGERS ]
 [ [ , ] KEEP_NULLS ]
 [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
 [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
 [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
 [ [ , ] TABLOCK ]
 ) ]
 }

 [; ] <column_definition> ::=
 column_name <data_type>
 [ COLLATE collation_name ]
 [ NULL | NOT NULL ]

 <data type> ::=
 [ type_schema_name . ] type_name
 [ ( precision [ , scale ] | max ]

 *
 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 INSERT INTO [ database_name . [ schema_name ] . | schema_name . ] table_name
 [ ( column_name [ ,...n ] ) ]
 {
 VALUES ( { NULL | expression } )
 | SELECT <select_criteria>
 }
 [ OPTION ( <query_option> [ ,...n ] ) ]
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class Insert implements Statement {
    //<WITH Clause>
    private With with;
    //<TOP Clause>
    private Top top;

    //INTO
    private boolean useInto;

    /*
    {
        <object>
        | rowset_function_limited [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
    }
     */
    //<object>
    private TableName tableName;
    //TODO rowset_function_limited [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]

    //( column_list )
    private List<ColumnName> columns;

    //<OUTPUT Clause>
    private Output output;

    /*
    {
        VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
        | derived_table
        | execute_statement
        | <dml_table_source>
        | DEFAULT VALUES
    }
     */
    //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
    private TableValueConstructor values;
    //TODO derived_table
    //TODO execute_statement
    //TODO <dml_table_source>
    //DEFAULT VALUES
    private boolean useDefaultValues;


    public With getWith() {
        return with;
    }

    public void setWith(With with) {
        this.with = with;
    }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public boolean isUseInto() {
        return useInto;
    }

    public void setUseInto(boolean useInto) {
        this.useInto = useInto;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<ColumnName> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnName> columns) {
        this.columns = columns;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public TableValueConstructor getValues() {
        return values;
    }

    public void setValues(TableValueConstructor values) {
        this.values = values;
    }

    public boolean isUseDefaultValues() {
        return useDefaultValues;
    }

    public void setUseDefaultValues(boolean useDefaultValues) {
        this.useDefaultValues = useDefaultValues;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();

        b.append(with);
        b.append(Keywords.INSERT);
        b.append(top);

        //[ INTO ]
        b.append(useInto ? Keywords.INTO : null);

        /*
        { <object> | rowset_function_limited
          [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
        }
         */
        b.append(Other.SPACE)
                .append(tableName);

        //[ ( column_list ) ]
        if(!CheckUtil.isNullOrEmpty(columns)){
            b.append(Other.GROUP_START);
            b.append(columns);
            b.append(Other.GROUP_END);
        }

        b.append(output);

        /*
        { VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
        | derived_table
        | execute_statement
        | <dml_table_source>
        | DEFAULT VALUES
        }
         */
        if(values != null){
            //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
            b.append(Keywords.VALUES)
                    .append(this.values);
        }else if(useDefaultValues){
            //DEFAULT VALUES
            b.append(Keywords.DEFAULT)
                    .append(Keywords.VALUES);
        }

        return b.build();
    }
}
