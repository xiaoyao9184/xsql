package com.xy.xsql.entity.api.dialect.jpql;

import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 单表 分页 条件查询
 * Created by xiaoyao9184 on 2016/12/29.
 */
public interface TemplatePageSelectArgRenderer {

    /**
     * Page Sql
     * @param entityTemplate EntityTemplate
     * @param pageStart Page Start with 1
     * @param pageSize Page Size
     * @param rowNumberName Row Number Name
     * @param args 参数
     * @return SQL
     */
    PlaceholderJSql getSelectArgsPageSql(EntityTemplate entityTemplate, Integer pageStart, Integer pageSize, String rowNumberName, Object... args);

    /**
     * Count Sql
     * @param entityTemplate EntityTemplate
     * @param args 参数
     * @return SQL
     */
    PlaceholderJSql getSelectArgsCountSql(EntityTemplate entityTemplate, Object... args);
}
