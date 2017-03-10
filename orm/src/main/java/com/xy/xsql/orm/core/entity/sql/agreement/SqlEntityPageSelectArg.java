package com.xy.xsql.orm.core.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;

/**
 * 单表 分页 条件查询
 * Created by xiaoyao9184 on 2016/12/29.
 */
public interface SqlEntityPageSelectArg {

    /**
     * Page Sql
     * @param entityTemplate EntityTemplate
     * @param pageStart Page Start with 1
     * @param pageSize Page Size
     * @param rowNumberName Row Number Name
     * @param args 参数
     * @return SQL
     */
    ArgSql getSelectArgsPageSql(EntityTemplate entityTemplate, Integer pageStart, Integer pageSize, String rowNumberName, Object... args);

    /**
     * Count Sql
     * @param entityTemplate EntityTemplate
     * @param args 参数
     * @return SQL
     */
    ArgSql getSelectArgsCountSql(EntityTemplate entityTemplate, Object... args);
}
