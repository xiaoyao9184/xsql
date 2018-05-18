package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder;
import com.xy.xsql.tsql.core.statement.ddl.TruncateTableBuilder;
import com.xy.xsql.tsql.core.statement.ddl.alter.table.AlterTableBuilder;
import com.xy.xsql.tsql.core.statement.ddl.create.table.SimpleCreateTableBuilder;
import com.xy.xsql.tsql.core.statement.dml.*;

/**
 * Created by xiaoyao9184 on 2017/3/23.
 */
public class StatementBuilderFactory {

    /*
    DDL
     */

    public static AlterTableBuilder ALTER_TABLE(){
        return new AlterTableBuilder();
    }

    public static SimpleCreateTableBuilder CREATE_TABLE(){
        return new SimpleCreateTableBuilder();
    }


    public static ReNameBuilder RENAME(){
        return new ReNameBuilder();
    }

    public static TruncateTableBuilder TRUNCATE_TABLE(){
        return new TruncateTableBuilder();
    }


    /*
    DML
     */

    public static BulkInsertBuilder BULIK_INSERT(){
        return new BulkInsertBuilder();
    }

    public static DeleteBuilder DELETE(){
        return new DeleteBuilder();
    }

    public static InsertBuilder INSERT(){
        return new InsertBuilder();
    }

    public static MergeBuilder MERGE(){
        return new MergeBuilder();
    }

    public static SelectBuilder SELECT(){
        return new SelectBuilder();
    }

    public static UpdateBuilder UPDATE(){
        return new UpdateBuilder();
    }
}
