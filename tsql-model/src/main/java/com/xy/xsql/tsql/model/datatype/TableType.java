package com.xy.xsql.tsql.model.datatype;


import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class TableType {

    private List<?> list;


    public static class ColumnDefinition {
        //column_name scalar_data_type
        private String columnName;
        private String scalarDataType;
        //TODO [ COLLATE <collation_definition> ]
//        private CollationDefinition collationDefinition;
        //TODO [ [ DEFAULT constant_expression ] | IDENTITY [ ( seed , increment ) ] ]
//        private Expression constantExpression;

        //[ ROWGUIDCOL ]
        private boolean useRowGuidCol;
        //[ column_constraint ] [ ...n ]
        private List<ColumnConstraint> columnConstraint;
    }

    public static class ColumnConstraint {
        //[ NULL | NOT NULL ]
        private boolean useNull;
        private boolean useNotNull;
        //[ PRIMARY KEY | UNIQUE ]
        private boolean usePrimaryKey;
        private boolean useUique;
        //CHECK ( logical_expression )
        private Expression logicalExpression;
    }

    public static class table_constraint {
        //{ PRIMARY KEY | UNIQUE } ( column_name [ ,...n ] )
        private boolean usePrimaryKey;
        private boolean useUique;
        private List<String>  columnName;
        //CHECK ( logical_expression )
        private Expression logicalExpression;
    }
}
