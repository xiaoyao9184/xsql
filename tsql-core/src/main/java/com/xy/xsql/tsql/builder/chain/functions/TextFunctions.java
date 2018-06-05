package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.text.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface TextFunctions {

    @Deprecated
    static TextPtr f_textptr(
            ColumnName column){
        TextPtr f = new TextPtr();
        f.setColumn(column);
        return f;
    }

    @Deprecated
    static TextValid f_textvalid(
            StringConstant column,
            Expression textPtr){
        TextValid f = new TextValid();
        f.setColumn(column);
        f.setTextPtr(textPtr);
        return f;
    }
}
