package com.xy.xsql.entity.core.meta.column;

import com.xy.xsql.entity.api.meta.ColumnMeta;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/9/27.
 */
public interface ColumnCollectionIndexer<C extends ColumnMeta> {

    Collection<C> column();

}
