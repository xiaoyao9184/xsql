package com.xy.xsql.entity.core.dialect.general;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.UpdateByColumnTableMetaRender;
import com.xy.xsql.entity.api.meta.BaseMeta;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public class GeneralUpdateByColumnTableMetaRender implements UpdateByColumnTableMetaRender {

    @Override
    public <C extends EntityColumnMeta, T extends EntityTableMeta<?, C>> PlaceholderJSql rendering(T tableMeta, Collection<C> columns) {
        String cString = tableMeta.getColumns().stream()
                .map(BaseMeta::getName)
                .collect(Collectors.joining(" = ? \n,"));

        String wString = columns.stream()
                .map(BaseMeta::getName)
                .collect(Collectors.joining(" = ? \n AND"));
        // @formatter:off
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                .append("\n")
                .append(tableMeta.getName())
                .append("\n")
                .append("SET")
                .append("\n")
                .append(cString)
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(wString)
                .append("\n");
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }
}
