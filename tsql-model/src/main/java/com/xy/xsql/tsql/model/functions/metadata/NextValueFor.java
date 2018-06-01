package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;
import com.xy.xsql.tsql.model.queries.select.Over;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class NextValueFor
        implements MetaDataFunction, Function.InternalFunction {

    private TableName sequenceName;
    private Over over;

    public TableName getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(TableName sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }
}
