package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.TruncateTable;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_RENAME;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_TRUNCATE_TABLE;

import static com.xy.xsql.tsql.builder.chain.statements.TruncateTableBuilder.e_pn;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface rename {

    //

    static b_RENAME RENAME_DATABASE(
            String dbName,
            String newName){
        return new b_RENAME(){
            {
                withDBName(dbName);
                withNewName(newName);
            }
        };
    }
    static b_RENAME RENAME_OBJECT(
            TableName table_name,
            String newName){
        return new b_RENAME(){
            {
                withTableName(table_name);
                withNewName(newName);
            }
        };
    }
}
