package com.xy.xsql.entity.core.dialect.mssql;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.QueryCountTableMetaRender;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * Created by xiaoyao9184 on 2017/10/23.
 */
public class MsSqlQueryCountTableMetaRender implements QueryCountTableMetaRender {
    @Override
    public <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta) {
        StringBuilder sb = new StringBuilder()
                .append("SELECT\n")
                .append("COUNT(name)\n")
                .append("FROM sysobjects\n")
                .append("WHERE name=\n")
                .append("'")
                .append(tableMeta.getName())
                .append("'")
                .append("\n")
                .append("AND type='u'");
        return new PlaceholderJSql().withSql(sb.toString());
    }
}
