package com.xy.xsql.hsweb.ezorm.rdb.render.dialect;

import com.xy.xsql.hsweb.ezorm.render.support.tsql.*;
import org.hsweb.ezorm.rdb.render.SqlRender;
import org.hsweb.ezorm.rdb.render.dialect.AbstractRDBDatabaseMetaData;
import org.hsweb.ezorm.rdb.render.dialect.Dialect;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public class TSqlRDBDatabaseMetaData extends AbstractRDBDatabaseMetaData {
    private static final String DEFAULT_NAME = "tsql";

    private String name;

    @Override
    public void init() {
        super.init();
        renderMap.put(SqlRender.TYPE.META_CREATE, new TsqlMetaCreateRender());
        renderMap.put(SqlRender.TYPE.META_ALTER, new TsqlMetaAlterRender());
//        renderMap.put(SqlRender.TYPE.META_DROP, new TsqlMeta);
        renderMap.put(SqlRender.TYPE.SELECT, new TsqlSelectSqlRender());
        renderMap.put(SqlRender.TYPE.SELECT_TOTAL, new TsqlSelectTotalSqlRender());
        renderMap.put(SqlRender.TYPE.DELETE, new TsqlDeleteSqlRender());
        renderMap.put(SqlRender.TYPE.UPDATE, new TsqlUpdateSqlRender());
        renderMap.put(SqlRender.TYPE.INSERT, new TsqlInsertSqlRender());
    }

    public TSqlRDBDatabaseMetaData() {
        super(Dialect.MSSQL);
        name = DEFAULT_NAME;
        init();
    }

    @Override
    public String getName() {
        return name;
    }
}
