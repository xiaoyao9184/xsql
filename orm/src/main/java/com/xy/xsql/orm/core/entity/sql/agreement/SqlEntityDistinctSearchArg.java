package com.xy.xsql.orm.core.entity.sql.agreement;


import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;

/**
 * 多数据 多表 除重 查询
 * Created by xiaoyao9184 on 2017/1/3.
 */
@SuppressWarnings("Duplicates")
public interface SqlEntityDistinctSearchArg {

    /**
     * 多表查询+主表参数过滤
     * @param entityTemplate 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    ArgSql getDistinctJoinByArgsSql(EntityTemplate entityTemplate, Object... args);
}
