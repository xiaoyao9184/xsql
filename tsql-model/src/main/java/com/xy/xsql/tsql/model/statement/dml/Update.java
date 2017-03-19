package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.operator.Comparison;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.model.variable.LocalVariable;
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
    //TODO rowset_function_limited [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
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


    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();

        b.append(with);
        b.append(Keywords.UPDATE);
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

        b.append(output);
        b.append(from);
        b.append(where);
        b.append(option);

        return b.build();
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
                    .append(Comparison.EQUAL)
                    .append(this.useNull ? Keywords.NULL : this.expression)
                    .build();
        }
    }

    /**
     *
     */
    public interface SetItem extends Block {

        /**
         * must override
         * @return
         */
        List<Block> toBlockList();
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

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.columnName)
                    .append(Assignment.ASSIGNMENT)
                    .append(this.useNull ?
                            Keywords.NULL :
                            this.useDefault ?
                                    Keywords.DEFAULT :
                                    this.expression)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.variable)
                    .append(Assignment.ASSIGNMENT)
                    .append(this.expression)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.variable)
                    .append(Assignment.ASSIGNMENT)
                    .append(this.columnName)
                    .append(Assignment.ASSIGNMENT)
                    .append(this.expression)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.columnName)
                    .append(this.compound)
                    .append(this.expression)
                    .build();
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


        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.variable)
                    .append(this.compound)
                    .append(this.expression)
                    .build();
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

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(this.variable)
                    .append(Assignment.ASSIGNMENT)
                    .append(this.columnName)
                    .append(this.compound)
                    .append(this.expression)
                    .build();
        }
    }



}
