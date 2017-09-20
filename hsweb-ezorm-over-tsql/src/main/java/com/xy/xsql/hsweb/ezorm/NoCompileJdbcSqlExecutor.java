package com.xy.xsql.hsweb.ezorm;

import org.hsweb.ezorm.rdb.executor.AbstractJdbcSqlExecutor;
import org.hsweb.ezorm.rdb.executor.SQL;

/**
 * Created by xiaoyao9184 on 2017/9/14.
 */
public abstract class NoCompileJdbcSqlExecutor extends AbstractJdbcSqlExecutor {

    @Override
    public SQLInfo compileSql(SQL sql) {
        if(!sql.getSql().contains("?")){
            return super.compileSql(sql);
        }
        SQLInfo sqlInfo = new SQLInfo();
        sqlInfo.setSql(sql.getSql());

        if(sql.getParams() instanceof Object[]){
            sqlInfo.setParam((Object[]) sql.getParams());
        }else{
            sqlInfo.setParam(new Object[]{sql.getParams()});
        }
        return sqlInfo;
    }
}
