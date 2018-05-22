package com.xy.xsql.tsql.model.queries.select;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.Clause;
import com.xy.xsql.tsql.model.datatypes.table.TableName;

/**
 *
 *

 [ INTO new_table ]

 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Into implements Clause {

    private TableName newTable;
    private Expression fileGroup;


    public TableName getNewTable() {
        return newTable;
    }

    public void setNewTable(TableName newTable) {
        this.newTable = newTable;
    }

    public Expression getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(Expression fileGroup) {
        this.fileGroup = fileGroup;
    }

}
