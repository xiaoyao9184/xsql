package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.expressions.ScalarExpression;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms177564.aspx
 *

 <OUTPUT_CLAUSE> ::=
 {
 [ OUTPUT <dml_select_list> INTO { @table_variable | output_table } [ ( column_list ) ] ]
 [ OUTPUT <dml_select_list> ]
 }
 <dml_select_list> ::=
 { <column_name> | scalar_expression } [ [AS] column_alias_identifier ]
 [ ,...n ]

 <column_name> ::=
 { DELETED | INSERTED | from_table_name } . { * | column_name }
 | $action

 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Output implements Clause {

    //<dml_select_list>
    private List<DmlSelect> dmlSelectList;

    //{ @table_variable | output_table }
    private LocalVariable tableVariable;
    private TableName outputTable;

    //[ ( column_list ) ]
    //TODO mybe just string name
    private List<com.xy.xsql.tsql.model.element.ColumnName> columnList;

    //[ OUTPUT <dml_select_list> ] 
    private List<DmlSelect> outputDmlSelectList;

    public List<DmlSelect> getDmlSelectList() {
        return dmlSelectList;
    }

    public void setDmlSelectList(List<DmlSelect> dmlSelectList) {
        this.dmlSelectList = dmlSelectList;
    }

    public LocalVariable getTableVariable() {
        return tableVariable;
    }

    public void setTableVariable(LocalVariable tableVariable) {
        this.tableVariable = tableVariable;
    }

    public TableName getOutputTable() {
        return outputTable;
    }

    public void setOutputTable(TableName outputTable) {
        this.outputTable = outputTable;
    }

    public List<com.xy.xsql.tsql.model.element.ColumnName> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<com.xy.xsql.tsql.model.element.ColumnName> columnList) {
        this.columnList = columnList;
    }

    public List<DmlSelect> getOutputDmlSelectList() {
        return outputDmlSelectList;
    }

    public void setOutputDmlSelectList(List<DmlSelect> outputDmlSelectList) {
        this.outputDmlSelectList = outputDmlSelectList;
    }


    /**
     * name from <dml_select_list>
     *
     * { <column_name> | scalar_expression } [ [AS] column_alias_identifier ]
     */
    public static class DmlSelect {

        private ColumnName columnName;
        private ScalarExpression scalarExpression;

        private boolean useAs;
        private String columnAliasIdentifier;

        public DmlSelect(){}

        public DmlSelect(ColumnName columnName){
            this.columnName = columnName;
        }

        public DmlSelect(ScalarExpression scalarExpression){
            this.scalarExpression = scalarExpression;
        }

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

        public ScalarExpression getScalarExpression() {
            return scalarExpression;
        }

        public void setScalarExpression(ScalarExpression scalarExpression) {
            this.scalarExpression = scalarExpression;
        }

        public boolean isUseAs() {
            return useAs;
        }

        public void setUseAs(boolean useAs) {
            this.useAs = useAs;
        }

        public String getColumnAliasIdentifier() {
            return columnAliasIdentifier;
        }

        public void setColumnAliasIdentifier(String columnAliasIdentifier) {
            this.columnAliasIdentifier = columnAliasIdentifier;
        }

    }

    /**
     * <column_name>
     */
    public static class ColumnName implements Expression {

        //{ DELETED | INSERTED | from_table_name }
        private boolean useDeleted;
        private boolean useInserted;
        private String fromTableName;

        //{ * | column_name }
        private boolean useAll;
        private String columnName;

        //$action
        private boolean $action;

        public ColumnName() {

        }

        public ColumnName(String name) {
            this.columnName = name;
        }

        public boolean isUseDeleted() {
            return useDeleted;
        }

        public void setUseDeleted(boolean useDeleted) {
            this.useDeleted = useDeleted;
        }

        public boolean isUseInserted() {
            return useInserted;
        }

        public void setUseInserted(boolean useInserted) {
            this.useInserted = useInserted;
        }

        public String getFromTableName() {
            return fromTableName;
        }

        public void setFromTableName(String fromTableName) {
            this.fromTableName = fromTableName;
        }

        public boolean isUseAll() {
            return useAll;
        }

        public void setUseAll(boolean useAll) {
            this.useAll = useAll;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public boolean is$action() {
            return $action;
        }

        public void set$action(boolean $action) {
            this.$action = $action;
        }

    }
}
