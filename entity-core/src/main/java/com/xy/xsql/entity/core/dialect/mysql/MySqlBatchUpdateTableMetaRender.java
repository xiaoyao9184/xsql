package com.xy.xsql.entity.core.dialect.mysql;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.BatchUpdateTableMetaRender;
import com.xy.xsql.entity.api.meta.BaseMeta;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.util.StringUtil;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public class MySqlBatchUpdateTableMetaRender implements BatchUpdateTableMetaRender {

    @Override
    public <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta, Collection<C> columnIds, Integer entityCount) {
        String cString = tableMeta.getColumns().stream()
                .map(BaseMeta::getName)
                .collect(Collectors.joining("\n,"));

        //noinspection StringBufferReplaceableByString
        String vString = new StringBuilder()
                .append("(")
                .append("\n")
                .append(StringUtil.fillJoin("?", tableMeta.getColumns().size(), ","))
                .append("\n")
                .append(")")
                .append("\n")
                .toString();

        // @formatter:off
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("REPLACE")
                .append("\n")
                .append(tableMeta.getName())
                .append("\n")
                .append("(")
                    .append("\n")
                    .append(cString)
                    .append("\n")
                .append(")")
                .append("\n")
                .append("VALUES")
                .append("\n")
                .append(StringUtil.fillJoin(vString, entityCount, ""));
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }

}
