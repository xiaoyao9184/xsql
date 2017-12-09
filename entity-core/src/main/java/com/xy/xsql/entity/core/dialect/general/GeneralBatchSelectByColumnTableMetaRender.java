package com.xy.xsql.entity.core.dialect.general;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.BatchSelectByColumnTableMetaRender;
import com.xy.xsql.entity.api.meta.BaseMeta;
import com.xy.xsql.entity.core.util.StringUtil;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public class GeneralBatchSelectByColumnTableMetaRender implements BatchSelectByColumnTableMetaRender {

    @Override
    public <C extends EntityColumnMeta, T extends EntityTableMeta<?, C>> PlaceholderJSql rendering(T tableMeta, Collection<C> columns, Integer count) {
        String cString = tableMeta.getColumns().stream()
                .map(BaseMeta::getName)
                .collect(Collectors.joining("\n,"));

        String wString = null;
        if(columns.size() == 1){
            StringBuilder wSb = columns.stream()
                    .map(BaseMeta::getName)
                    .findFirst()
                    .map(StringBuilder::new)
                    .orElseThrow(UnsupportedOperationException::new);
            wSb.append(" IN (")
                    .append(StringUtil.fillJoin("?", count, ","))
                    .append(" )");
            wString = wSb.toString();
        }else{
            String oneEntity = columns.stream()
                    .map(BaseMeta::getName)
                    .collect(Collectors.joining(" = ? \n AND "));
            StringBuilder sbOne = new StringBuilder()
                    .append(" ( ")
                    .append(oneEntity)
                    .append(" ) ");
            wString = StringUtil.fillJoin(sbOne.toString(), count, "\n OR ");
        }

        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("SELECT")
                .append("\n")
                .append(cString)
                .append("\n")
                .append("FROM")
                .append("\n")
                .append(tableMeta.getName())
                .append("\n")
                .append("WHERE")
                .append("\n")
                .append(wString)
                .append("\n");

        return new PlaceholderJSql().withSql(sb.toString());
    }
}
