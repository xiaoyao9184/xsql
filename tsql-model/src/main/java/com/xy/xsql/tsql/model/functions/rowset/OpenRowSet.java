package com.xy.xsql.tsql.model.functions.rowset;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.statements.BulkInsert;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class OpenRowSet
        implements RowsetFunction, Function.InternalFunction {

    private Expression providerName;
    //'datasource' ; 'user_id' ; 'password'
    private StringConstant datasource;
    private StringConstant userId;
    private StringConstant password;
    //provider_string
    private StringConstant provider_string;

    //
    private List<String> object;
    private StringConstant query;

    private BulkInsert bulkInsert;
    private Single single;

    public Expression getProviderName() {
        return providerName;
    }

    public void setProviderName(Expression providerName) {
        this.providerName = providerName;
    }

    public StringConstant getDatasource() {
        return datasource;
    }

    public void setDatasource(StringConstant datasource) {
        this.datasource = datasource;
    }

    public StringConstant getUserId() {
        return userId;
    }

    public void setUserId(StringConstant userId) {
        this.userId = userId;
    }

    public StringConstant getPassword() {
        return password;
    }

    public void setPassword(StringConstant password) {
        this.password = password;
    }

    public StringConstant getProviderString() {
        return provider_string;
    }

    public void setProvider_string(StringConstant provider_string) {
        this.provider_string = provider_string;
    }

    public List<String> getObject() {
        return object;
    }

    public void setObject(List<String> object) {
        this.object = object;
    }

    public StringConstant getQuery() {
        return query;
    }

    public void setQuery(StringConstant query) {
        this.query = query;
    }

    public BulkInsert getBulkInsert() {
        return bulkInsert;
    }

    public void setBulkInsert(BulkInsert bulkInsert) {
        this.bulkInsert = bulkInsert;
    }

    public Single getSingle() {
        return single;
    }

    public void setSingle(Single single) {
        this.single = single;
    }

    public enum Single {
        SINGLE_BLOB,
        SINGLE_CLOB,
        SINGLE_NCLOB
    }
}
