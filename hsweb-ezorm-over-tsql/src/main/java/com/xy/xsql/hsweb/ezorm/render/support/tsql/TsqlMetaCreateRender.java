package com.xy.xsql.hsweb.ezorm.render.support.tsql;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;
import org.hsweb.ezorm.rdb.executor.SQL;
import org.hsweb.ezorm.rdb.meta.RDBColumnMetaData;
import org.hsweb.ezorm.rdb.meta.RDBTableMetaData;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.support.simple.SimpleSQL;

import java.util.Set;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._user_defined;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.statements.create.Creates.$CreateTable;

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
                        builder.$PrimaryKey();
                    }

                    //TODO comment
                    return builder.build();
                })
                .toArray(ColumnDefinition[]::new);

        SimpleCreateTable create = $CreateTable()
                .$(t(table.getName()))
                .$(cds)
                .build();

        String sqlString = BlockManager.INSTANCE.print(create);
        return new SimpleSQL(sqlString, param);
    }
}
