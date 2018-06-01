package com.xy.xsql.tsql.model.functions.metadata;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SecurityFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class IndexProperty
        implements MetaDataFunction, Function.InternalFunction {

    private Expression objectId;
    private Expression indexOrStatisticsName ;
    private Expression property;

    public Expression getObjectId() {
        return objectId;
    }

    public void setObjectId(Expression objectId) {
        this.objectId = objectId;
    }

    public Expression getIndexOrStatisticsName() {
        return indexOrStatisticsName;
    }

    public void setIndexOrStatisticsName(Expression indexOrStatisticsName) {
        this.indexOrStatisticsName = indexOrStatisticsName;
    }

    public Expression getProperty() {
        return property;
    }

    public void setProperty(Expression property) {
        this.property = property;
    }
}
