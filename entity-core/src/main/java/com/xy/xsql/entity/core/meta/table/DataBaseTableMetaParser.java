package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.api.meta.TableMetaBuilder;

/**
 * Created by xiaoyao9184 on 2017/9/21.
 */
public interface DataBaseTableMetaParser<T extends TableMeta>
        extends TableMetaBuilder<String,T> {

    default T build(String name) {
        return parse(name);
    }

    T parse(String name);
}
