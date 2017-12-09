package com.xy.xsql.entity.api.meta;

import java.util.Collection;

/**
 * Created by xiaoyao9184 on 2017/9/20.
 */
public interface TableMeta<C extends ColumnMeta> extends BaseMeta {

    Class getJavaType();

    Collection<C> getColumns();

}
