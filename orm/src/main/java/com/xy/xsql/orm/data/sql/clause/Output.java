package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.element.variable.VariableString;

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
public class Output implements ElementList {

    //<dml_select_list>
    private List<DmlSelect> dmlSelectList;

    //{ @table_variable | output_table }
    private VariableString tableVariable;
    private TableName outputTable;

    //[ ( column_list ) ]
    //TODO mybe just string name
    private List<Column> columnList;

    public List<DmlSelect> getDmlSelectList() {
        return dmlSelectList;
    }

    public void setDmlSelectList(List<DmlSelect> dmlSelectList) {
        this.dmlSelectList = dmlSelectList;
    }

    public VariableString getTableVariable() {
        return tableVariable;
    }

    public void setTableVariable(VariableString tableVariable) {
        this.tableVariable = tableVariable;
    }

    public TableName getOutputTable() {
        return outputTable;
    }

    public void setOutputTable(TableName outputTable) {
        this.outputTable = outputTable;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE);

        b.append(GrammarEnum.OUTPUT)
                    .append(dmlSelectList);

        if(tableVariable != null){
            b.append(GrammarEnum.INTO)
                    .append(tableVariable);

            if(columnList != null && !columnList.isEmpty()){
                b.append(OtherEnum.GROUP_START)
                        .append(columnList)
                        .append(OtherEnum.GROUP_END);
            }
        } else if(outputTable != null) {
            b.append(GrammarEnum.INTO)
                    .append(outputTable);

            if(columnList != null && !columnList.isEmpty()){
                b.append(OtherEnum.GROUP_START)
                        .append(columnList)
                        .append(OtherEnum.GROUP_END);
            }
        }

        return b.build();
    }


    /**
     * <dml_select_list>
     */
    @Deprecated
    public static class DmlSelectList implements ElementList {

        private List<DmlSelect> dmlSelectList;

        public List<DmlSelect> getDmlSelectList() {
            return dmlSelectList;
        }

        public void setDmlSelectList(List<DmlSelect> dmlSelectList) {
            this.dmlSelectList = dmlSelectList;
        }

        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .withDelimiter(OtherEnum.DELIMITER)
                    .append(dmlSelectList)
                    .build();
        }
    }

    /**
     * { <column_name> | scalar_expression } [ [AS] column_alias_identifier ]
     */
    public static class DmlSelect implements ElementList {

        private ColumnName columnName;
        private Expression scalarExpression;

        private boolean useAs;
        private String columnAliasIdentifier;

        public ColumnName getColumnName() {
            return columnName;
        }

        public void setColumnName(ColumnName columnName) {
            this.columnName = columnName;
        }

        public Expression getScalarExpression() {
            return scalarExpression;
        }

        public void setScalarExpression(Expression scalarExpression) {
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


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);

            if(columnName != null){
                b.append(columnName.toElementList());
            }else {
                b.append(scalarExpression);
            }

            if(columnAliasIdentifier != null){
                b.append(useAs ? GrammarEnum.AS : null)
                        .append(columnAliasIdentifier);
            }

            return b.build();
        }
    }

    /**
     * <column_name>
     */
    public static class ColumnName implements ElementList {

        //{ DELETED | INSERTED | from_table_name }
        private boolean useDeleted;
        private boolean useInserted;
        private String fromTableName;

        //{ * | column_name }
        private boolean useAll;
        private String columnName;

        //$action
        private boolean $action;

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

        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE);

            if(useDeleted){
                b.append(GrammarEnum.DELETED);
            } else if(useInserted){
                b.append(GrammarEnum.INSERTED);
            } else{
                b.append(fromTableName);
            }

            b.append(".");
            if(useAll){
                b.append("*");
            } else{
                b.append(columnName);
            }

            return b.build();
        }
    }
}
