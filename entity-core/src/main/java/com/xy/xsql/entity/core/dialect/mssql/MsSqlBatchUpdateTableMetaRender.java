package com.xy.xsql.entity.core.dialect.mssql;

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
public class MsSqlBatchUpdateTableMetaRender implements BatchUpdateTableMetaRender {

    @Override
    public <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta, Collection<C> columnIds, Integer entityCount) {
        if(columnIds.size() == 1){
            return rendering(tableMeta,
                    columnIds.stream()
                            .findFirst()
                            .orElseThrow(UnsupportedOperationException::new),
                    entityCount);
        }

        String columnList = tableMeta.getColumns().stream()
                .map(BaseMeta::getName)
                .collect(Collectors.joining("\n,"));

        String sString = tableMeta.getColumns().stream()
                .map(c -> "t." + c.getName() + " = v." + c.getName())
                .collect(Collectors.joining("\n,"));

        //noinspection StringBufferReplaceableByString
        String oString = columnIds.stream()
                .map(c -> "t." + c.getName() + " = v." + c.getName())
                .collect(Collectors.joining("\n AND"));

        //noinspection StringBufferReplaceableByString
        String oneValues = new StringBuilder()
                .append("(")
                .append(StringUtil.fillJoin("?", tableMeta.getColumns().size(), ","))
                .append(")")
                .toString();
        String values = StringUtil.fillJoin(oneValues, entityCount, ",\n ");

        // @formatter:off
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("UPDATE")
                    .append("\n")
                    .append(tableMeta.getName())
                    .append("\n")
                .append("SET")
                    .append("\n")
                    .append(sString)
                    .append("\n")
                .append("FROM")
                    .append("\n")
                    .append(tableMeta.getName())
                    .append(" AS t")
                    .append("\n")
                .append("RIGHT JOIN")
                    .append("\n")
                    .append("( VALUES")
                    .append("\n")
                    .append(values)
                    .append("\n")
                    .append(") AS v(")
                    .append(columnList)
                    .append(")")
                    .append("\n")
                .append(oString);
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }

    public <C extends ColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta, C columnId, Integer entityCount) {
        String cString = tableMeta.getColumns().stream()
                .map(c -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(c.getName())
                            .append(" = ")
                            .append("CASE")
                            .append(" ")
                            .append(columnId.getName())
                            .append("\n")
                            .append(StringUtil.fillJoin("WHEN ? THEN ?",entityCount,"\n"))
                            .append("\n")
                            .append("END")
                            .append("\n");
                    return sb.toString();
                })
                .collect(Collectors.joining("\n,"));

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
                .append(columnId.getName())
                .append("\n")
                .append("IN (")
                .append(StringUtil.fillJoin("?", entityCount, ","))
                .append(")")
                .append("\n");;
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }
}
