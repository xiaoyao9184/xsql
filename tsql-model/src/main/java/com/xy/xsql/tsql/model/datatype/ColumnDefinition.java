package com.xy.xsql.tsql.model.datatype;


import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.collation.Collate;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms175010.aspx
 *
 * <column_definition> ::=
 column_name scalar_data_type
 [ COLLATE <collation_definition> ]
 [ [ DEFAULT constant_expression ] | IDENTITY [ ( seed , increment ) ] ]
 [ ROWGUIDCOL ]
 [ column_constraint ] [ ...n ]

 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ColumnDefinition
        extends ColumnName
        implements TableTypeDefinition.Item {

    //<data_type>
    //scalar_data_type
    private DataType dataType;

    //TODO [ FILESTREAM ]
    //[ COLLATE collation_name ]
    //[ COLLATE <collation_definition> ]
    private Collate collationName;
    //[ SPARSE ]
    private boolean useSparse;
    //TODO [ MASKED WITH ( FUNCTION = ' mask_function ') ]
    //TODO [ CONSTRAINT constraint_name ] DEFAULT constant_expression ]
    private Expression constantExpression;
    //[ IDENTITY [ ( seed,increment ) ]


    //[ ROWGUIDCOL ]
    private boolean useRowGuidCol;
    //[ column_constraint ] [ ...n ]
    private List<ColumnConstraint> columnConstraint;
    //[ <column_index> ]


    public ColumnDefinition(String name){
        super(name);
    }

    @Deprecated
    public ColumnDefinition(String name,String type){
        super(name);
    }

    @Deprecated
    public ColumnDefinition(String name, String type, Integer len) {
        super(name);
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Collate getCollationName() {
        return collationName;
    }

    public void setCollationName(Collate collationName) {
        this.collationName = collationName;
    }

    public boolean isUseSparse() {
        return useSparse;
    }

    public void setUseSparse(boolean useSparse) {
        this.useSparse = useSparse;
    }


    /**
     * <column_constraint>
     *
     *
     * https://msdn.microsoft.com/en-us/library/ms175010.aspx
     *
     * <column_constraint> ::=
     { [ NULL | NOT NULL ]
     | [ PRIMARY KEY | UNIQUE ]
     | CHECK ( logical_expression )
     }
     *
     *
     *
     * --Disk-Based CREATE TABLE Syntax
     * <column_constraint> ::=
     [ CONSTRAINT constraint_name ]
     {     { PRIMARY KEY | UNIQUE }
     [ CLUSTERED | NONCLUSTERED ]
     [
     WITH FILLFACTOR = fillfactor
     | WITH ( < index_option > [ , ...n ] )
     ]
     [ ON { partition_scheme_name ( partition_column_name )
     | filegroup | "default" } ]

     | [ FOREIGN KEY ]
     REFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]
     [ ON DELETE { NO ACTION | CASCADE | SET NULL | SET DEFAULT } ]
     [ ON UPDATE { NO ACTION | CASCADE | SET NULL | SET DEFAULT } ]
     [ NOT FOR REPLICATION ]

     | CHECK [ NOT FOR REPLICATION ] ( logical_expression )
     }
     *
     *
     * --Memory optimized CREATE TABLE Syntax
     * <column_constraint> ::=
     [ CONSTRAINT constraint_name ]
     {
     { PRIMARY KEY | UNIQUE }
     {   NONCLUSTERED
     | NONCLUSTERED HASH WITH (BUCKET_COUNT = bucket_count)
     }
     | [ FOREIGN KEY ]
     REFERENCES [ schema_name . ] referenced_table_name [ ( ref_column ) ]
     | CHECK ( logical_expression )
     }
     */
    public static class ColumnConstraint {
        //CONSTRAINT constraint_name
        private String constraint_name;
        //


        //[ NULL | NOT NULL ]
        private boolean useNull;
        private boolean useNotNull;
        //[ PRIMARY KEY | UNIQUE ]
        private boolean usePrimaryKey;
        private boolean useUique;
        //CHECK ( logical_expression )
        private Expression logicalExpression;
    }
}
