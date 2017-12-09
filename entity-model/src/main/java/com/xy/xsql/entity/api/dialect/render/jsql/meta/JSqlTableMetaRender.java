package com.xy.xsql.entity.api.dialect.render.jsql.meta;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public interface JSqlTableMetaRender {

    <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta);
}
