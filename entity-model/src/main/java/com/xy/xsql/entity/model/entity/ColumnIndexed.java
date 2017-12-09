package com.xy.xsql.entity.model.entity;

/**
 * Created by xiaoyao9184 on 2017/9/27.
 */
public interface ColumnIndexed<INDEX,C extends EntityColumnMeta> {

    C index(INDEX index);
}
