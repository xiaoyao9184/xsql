package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_INTO;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface into {

    static b_INTO INTO(TableName tableName){
        return new b_INTO(){
            {
                withNewTable(tableName);
            }
        };
    }

}
