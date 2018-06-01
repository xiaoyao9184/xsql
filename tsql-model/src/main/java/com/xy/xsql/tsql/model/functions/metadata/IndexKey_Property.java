package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class IndexKey_Property
        implements MetaDataFunction, Function.InternalFunction {

    private Expression objectId;
    private Expression indexId;
    private Expression keyId;
    private Expression property;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public Expression getIndexId() {
        return indexId;
    }

    public void setIndexId(Expression indexId) {
        this.indexId = indexId;
    }

    public Expression getKeyId() {
        return keyId;
    }

    public void setKeyId(Expression keyId) {
        this.keyId = keyId;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
