package com.xy.xsql.entity.api.dialect.render.jsql.meta;

import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.Collection;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface BatchSelectByColumnTableMetaRender {

    <C extends EntityColumnMeta,T extends EntityTableMeta<?,C>> PlaceholderJSql rendering(T tableMeta, Collection<C> columns, Integer count);

}
