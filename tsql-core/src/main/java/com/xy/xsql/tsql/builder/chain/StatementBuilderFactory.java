package com.xy.xsql.tsql.builder.chain;

import com.xy.xsql.tsql.builder.chain.queries.SelectBuilder;
import com.xy.xsql.tsql.builder.chain.queries.UpdateBuilder;
import com.xy.xsql.tsql.builder.chain.statements.BulkInsertBuilder;
import com.xy.xsql.tsql.builder.chain.statements.DeleteBuilder;
import com.xy.xsql.tsql.builder.chain.statements.InsertBuilder;
import com.xy.xsql.tsql.builder.chain.statements.MergeBuilder;
import com.xy.xsql.tsql.builder.chain.statements.ReNameBuilder;
import com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder;
import com.xy.xsql.tsql.builder.chain.statements.alter.table.AlterTableBuilder;
import com.xy.xsql.tsql.builder.chain.statements.create.table.SimpleCreateTableBuilder;

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
