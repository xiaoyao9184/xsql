package com.xy.xsql.entity.api.dialect.render.jsql.meta;

import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface InsertTableMetaRender {

    <C extends EntityColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta);

}
