package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms175010.aspx
 *
 * table_type_definition ::=
 TABLE ( { <column_definition> | <table_constraint> } [ ,...n ] )

 *
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableTypeDefinition {

    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }


    public interface Item {

    }

    /**
     *
     * <table_constraint> ::=
     { { PRIMARY KEY | UNIQUE } ( column_name [ ,...n ] )
     | CHECK ( logical_expression )
     }
     *
     */
    public static class TableConstraint implements Item {
        //{ PRIMARY KEY | UNIQUE } ( column_name [ ,...n ] )
        private boolean usePrimaryKey;
        private boolean useUnique;
        private List<ColumnName>  columnName;
        //CHECK ( logical_expression )
        private Expression logicalExpression;

        public boolean isUsePrimaryKey() {
            return usePrimaryKey;
        }

        public void setUsePrimaryKey(boolean usePrimaryKey) {
            this.usePrimaryKey = usePrimaryKey;
        }

        public boolean isUseUnique() {
            return useUnique;
        }

        public void setUseUnique(boolean useUnique) {
            this.useUnique = useUnique;
        }

        public List<ColumnName> getColumnName() {
            return columnName;
        }

        public void setColumnName(List<ColumnName> columnName) {
            this.columnName = columnName;
        }

        public Expression getLogicalExpression() {
            return logicalExpression;
        }

        public void setLogicalExpression(Expression logicalExpression) {
            this.logicalExpression = logicalExpression;
        }

    }

}
