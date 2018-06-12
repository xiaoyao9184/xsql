package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Index_Col
        implements MetaDataFunction, Function.InternalFunction {

    private StringConstant tableName;
    private Expression indexId;
    private Expression keyId;

    public StringConstant getTableName() {
        return tableName;
    }

    public void setTableName(StringConstant tableName) {
        this.tableName = tableName;
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
}
