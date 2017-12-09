package com.xy.xsql.entity.api.dialect.render.jsql.meta;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface BatchInsertTableMetaRender extends JSqlTableMetaRender {

    @Override
    default <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta) {
        return rendering(tableMeta, 1);
    }

    <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta, Integer entityCount);
}
