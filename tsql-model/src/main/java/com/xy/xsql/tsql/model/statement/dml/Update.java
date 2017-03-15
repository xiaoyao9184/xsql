package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms177523.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [...n] ]
 UPDATE
 [ TOP ( expression ) [ PERCENT ] ]
 { { tableName | <object> | rowset_function_limited
 [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
 }
 | @table_variable
 }
 SET
 { column_name = { expression | DEFAULT | NULL }
 | { udt_column_name.{ { property_name = expression
 | field_name = expression }
 | method_name ( argument [ ,...n ] )
 }
 }
 | column_name { .WRITE ( expression , @Offset , @Length ) }
 | @variable = expression
 | @variable = column = expression
 | column_name { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression
 } [ ,...n ]

 [ <OUTPUT Clause> ]
 [ FROM{ <table_source> } [ ,...n ] ]
 [ WHERE { <search_condition>
 | { [ CURRENT OF
 { { [ GLOBAL ] cursor_name }
 | cursor_variable_name
 }
 ]
 }
 }
 ]
 [ OPTION ( <query_hint> [ ,...n ] ) ]
 [ ; ]

 <object> ::=
 {
 [ server_name . database_name . schema_name .
 | database_name .[ schema_name ] .
 | schema_name .
 ]
 table_or_view_name}

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 UPDATE [ database_name . [ schema_name ] . | schema_name . ] table_name
 SET { column_name = { expression | NULL } } [ ,...n ]
 [ FROM from_clause ]
 [ WHERE <search_condition> ]
 [ OPTION ( LABEL = label_name ) ]
 [;]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Update implements Statement {
    //TOP
    private Top top;
    //
    private Alias<Void> tableAlias;
    private TableName tableName;
    //SET
    private List<Set> sets;
    //FROM
    private From from;
    //WHERE
    private Where where;


    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
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

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
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


    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.UPDATE);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(top);

        /*
        { { table_alias | <object> | rowset_function_limited
             [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
          }
          | @table_variable
        }
         */
        if(this.tableAlias != null){
            b.append(tableAlias);
        } else if(this.tableName != null){
            b.append(tableName);
        }

        //SET { column_name = { expression | NULL } } [ ,...n ]
        b.append(Keywords.SET)
                .append(sets);

        //[ FROM{ <table_source> } [ ,...n ] ]
        b.append(from);

        /*
        [ WHERE { <search_condition>
                | { [ CURRENT OF
            { { [ GLOBAL ] cursor_name }
                           | cursor_variable_name
            }
                    ]
                  }
        }
        ]
        */
        b.append(where);

        return b.build();
    }


    /**
     * Set
     *
     * { column_name = { expression | NULL } }
     */
    public static class Set implements Block {
        private ColumnName columnName;
        private Expression expression;
        private boolean useNull = false;

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public boolean isUseNull() {
            return useNull;
        }

        public void setUseNull(boolean useNull) {
            this.useNull = useNull;
        }


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .withDelimiter(Other.SPACE)
                    .append(this.columnName)
                    .append(Operators.EQUAL)
                    .append(this.useNull ? Keywords.NULL : this.expression)
                    .build();
        }
    }
}
