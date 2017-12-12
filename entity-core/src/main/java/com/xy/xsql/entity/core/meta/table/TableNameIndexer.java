package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.TableMeta;

import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/9/27.
 */
public interface TableNameIndexer<T extends TableMeta> {

    Optional<T> table(String name);

}
