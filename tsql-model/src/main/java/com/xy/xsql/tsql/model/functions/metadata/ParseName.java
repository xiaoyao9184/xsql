package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class ParseName
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant objectName;
    private Expression objectPiece;

    public StringConstant getObjectName() {
        return objectName;
    }

    public void setObjectName(StringConstant objectName) {
        this.objectName = objectName;
    }

    public Expression getObjectPiece() {
        return objectPiece;
    }

    public void setObjectPiece(Expression objectPiece) {
        this.objectPiece = objectPiece;
    }
}
