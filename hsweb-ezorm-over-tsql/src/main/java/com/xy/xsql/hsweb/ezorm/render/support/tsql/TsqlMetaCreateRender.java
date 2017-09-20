package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.core.element.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.Set;

import static com.xy.xsql.tsql.core.datatype.DataTypes._user_defined;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.statement.ddl.create.table.SimpleCreateTableBuilder.CREATE_TABLE;

/**
 * sqlServer 表结构创建 sql渲染器,用于渲染sqlServer创建表的sql
 */
public class TsqlMetaCreateRender implements SqlRender {

    @Override
    public SQL render(RDBTableMetaData table, Object param) {
        Set<RDBColumnMetaData> RDBColumnMetaDatas = table.getColumns();
        if (RDBColumnMetaDatas.isEmpty()) throw new UnsupportedOperationException("未指定任何字段");

        ColumnDefinition[] cds = RDBColumnMetaDatas.stream()
                .map(column -> {
                    ColumnDefinitionBuilder builder = new ColumnDefinitionBuilder<>()
                            .withColumnName(c(column.getName()))
                            .withDataType(_user_defined(column.getDataType()))
                            .withUseNotNull(column.isNotNull());

                    if(column.isPrimaryKey()){
                        builder.$PRIMARY_KEY();
                    }

                    //TODO comment
                    return builder.build();
                })
                .toArray(ColumnDefinition[]::new);

        SimpleCreateTable create = CREATE_TABLE()
                .$(t(table.getName()))
                .$(cds)
                .build();

        String sqlString = BlockManager.INSTANCE.print(create);
        return new SimpleSQL(sqlString, param);
    }
}
