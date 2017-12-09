package com.xy.xsql.entity.api.dialect.render.jsql.meta;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.Collection;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface BatchUpdateTableMetaRender {

    <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta, Collection<C> columnIds, Integer entityCount);
}
