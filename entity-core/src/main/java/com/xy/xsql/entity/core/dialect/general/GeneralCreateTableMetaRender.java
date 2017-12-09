package com.xy.xsql.entity.core.dialect.general;

import com.xy.xsql.entity.api.dialect.render.jsql.meta.InsertTableMetaRender;
import com.xy.xsql.entity.api.meta.BaseMeta;
import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.core.util.StringUtil;
import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.template.EntityColumn;
import com.xy.xsql.model.sql.PlaceholderJSql;

import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/10/17.
 */
public class GeneralCreateTableMetaRender implements InsertTableMetaRender {

    @Override
    public <C extends EntityColumnMeta> PlaceholderJSql rendering(TableMeta<C> tableMeta) {
        String cString = tableMeta.getColumns().stream()
                .map(c -> {
                    StringBuilder sb = new StringBuilder()
                            .append(c.getName())
                            .append(" ")
                            .append(c.getDbType());
                    if(c.getLength() > 0){
                        sb.append("(")
                                .append(c.getLength())
                                .append(")");
                    }else if(c.getPrecision() > 0 && c.getScale() > 0){
                        sb.append("(")
                                .append(c.getPrecision())
                                .append(",")
                                .append(c.getScale())
                                .append(")");
                    }

                    //constraint
                    if(c.isPrimary()){
                        sb.append(" PRIMARY KEY");
                    }else if(c.isForeign()){
                        sb.append(" FOREIGN KEY");
                    }else if(c.isUnique()){
                        sb.append(" UNIQUE");
                    }
                    if(c.isNullable()){
                        sb.append(" NULL");
                    }else{
                        sb.append(" NOT NULL");
                    }
                    return sb.toString();
                })
                .collect(Collectors.joining("\n,"));
        // @formatter:off
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder()
                .append("CREATE TABLE")
                .append("\n")
                .append(tableMeta.getName())
                .append("\n")
                .append("(")
                .append("\n")
                .append(cString)
                .append(")")
                .append("\n");
        // @formatter:on

        return new PlaceholderJSql().withSql(sb.toString());
    }
}
