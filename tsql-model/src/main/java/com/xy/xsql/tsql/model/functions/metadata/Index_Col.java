package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Index_Col
        implements MetaDataFunction, Function.InternalFunction {

    private TableName tableName;
    private Expression indexId;
    private Expression keyId;

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
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
