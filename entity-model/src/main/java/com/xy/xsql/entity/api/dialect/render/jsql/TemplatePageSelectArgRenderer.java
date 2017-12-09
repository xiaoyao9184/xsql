package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityInfo;

/**
 * 单表 分页 条件查询
 * Created by xiaoyao9184 on 2016/12/29.
 */
public interface TemplatePageSelectArgRenderer {

    /**
     * Page Sql
     * @param entityInfo EntityInfo
     * @param pageStart Page Start with 1
     * @param pageSize Page Size
     * @param rowNumberName Row Number Name
     * @param args 参数
     * @return SQL
     */
    PlaceholderJSql getSelectArgsPageSql(EntityInfo entityInfo, Integer pageStart, Integer pageSize, String rowNumberName, Object... args);

    /**
     * Count Sql
     * @param entityInfo EntityInfo
     * @param args 参数
     * @return SQL
     */
    PlaceholderJSql getSelectArgsCountSql(EntityInfo entityInfo, Object... args);
}
