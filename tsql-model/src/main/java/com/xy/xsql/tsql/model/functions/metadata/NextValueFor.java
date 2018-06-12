package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;
import com.xy.xsql.tsql.model.queries.select.Over;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class NextValueFor
        implements MetaDataFunction, Function.InternalFunction {

    private List<String> sequenceName;
    private Over over;

    public List<String>  getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(List<String> sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }

}
