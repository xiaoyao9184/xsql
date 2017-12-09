package com.xy.xsql.entity.api.meta;

/**
 * Created by xiaoyao9184 on 2017/9/20.
 */
public interface ColumnMeta<T extends TableMeta> extends BaseMeta {

    Class getJavaType();

    //
    T getTableMeta();

}
