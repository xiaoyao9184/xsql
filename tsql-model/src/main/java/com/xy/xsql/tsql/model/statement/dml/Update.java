package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;

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
    //<WITH Clause>
    private With with;
    //<TOP Clause>
    private Top top;

    /*
    { { table_alias | <object> | rowset_function_limited
         [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
      }
      | @table_variable
    }
     */
    //table_alias
    private Alias<Void> tableAlias;
    //<object>
    private TableName tableName;
    //TODO rowset_function_limited
    //[ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
    private List<TableHintLimited> tableHintLimitedList;
    //TODO @table_variable

    //SET
    private List<SetItem> sets;

    //<OUTPUT Clause>
    private Output output;
    //<FROM Clause>
    private From from;
    //<WHERE Clause>
    private Where where;
    //<OPTION Clause>
    private Option option;


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

    public List<SetItem> getSets() {
        return sets;
    }

    public void setSets(List<SetItem> sets) {
        this.sets = sets;
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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


    /**
     * Set Item

    {   column_name = { expression | DEFAULT | NULL }
        |   { udt_column_name.{ { property_name = expression
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
    }

     *
     */
    @Deprecated
    public static class Set {
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

    }

    /**
     *
     */
    public interface SetItem {

    }

    /**
     * column_name = { expression | DEFAULT | NULL }
     */
    public static class ColumnAssignmentSet implements SetItem {
        private ColumnName columnName;
        private Expression expression;
        private boolean useNull = false;
        private boolean useDefault = false;

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

        public boolean isUseDefault() {
            return useDefault;
        }

        public void setUseDefault(boolean useDefault) {
            this.useDefault = useDefault;
        }

    }

    /**
     * @variable = expression
     */
    public static class VariableAssignmentSet implements SetItem {
        private LocalVariable variable;
        private Expression expression;

        public LocalVariable getVariable() {
            return variable;
        }

        public void setVariable(LocalVariable variable) {
            this.variable = variable;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

    }

    /**
     * @variable = column = expression
     */
    public static class VariableColumnAssignmentSet extends VariableAssignmentSet {
        private LocalVariable variable;
        private ColumnName columnName;
        private Expression expression;

        public LocalVariable getVariable() {
            return variable;
        }

        public void setVariable(LocalVariable variable) {
            this.variable = variable;
        }

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

    }

    /**
     * column_name { += | -= | *= | /= | %= | &= | ^= | |= } expression
     */
    public static class ColumnCompoundSet implements SetItem {
        private ColumnName columnName;
        protected Compound compound;
        private Expression expression;

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

        public Compound getCompound() {
            return compound;
        }

        public void setCompound(Compound compound) {
            this.compound = compound;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

    }

    /**
     * @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression
     */
    public static class VariableCompoundSet implements SetItem {
        protected LocalVariable variable;
        protected Compound compound;
        protected Expression expression;

        public LocalVariable getVariable() {
            return variable;
        }

        public void setVariable(LocalVariable variable) {
            this.variable = variable;
        }

        public Compound getCompound() {
            return compound;
        }

        public void setCompound(Compound compound) {
            this.compound = compound;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

    }

    /**
     * @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression
     */
    public static class VariableColumnCompoundSet extends VariableCompoundSet {
        private ColumnName columnName;

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

    }



}
