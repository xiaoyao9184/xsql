package com.xy.xsql.entity.core.dialect.general;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.DropTableMetaRender;
import com.xy.xsql.entity.api.dialect.render.jsql.meta.JSqlTableMetaRender;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public class GeneralDropTableMetaRender implements DropTableMetaRender {

    @Override
    public <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta) {
        // @formatter:off
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("DROP TABLE")
                .append("\n")
                .append(tableMeta.getName())
                .append("\n");
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }
}
