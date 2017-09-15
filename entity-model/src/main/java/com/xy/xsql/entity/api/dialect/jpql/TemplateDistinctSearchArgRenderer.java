package com.xy.xsql.entity.api.dialect.jpql;

import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 多数据 多表 除重 查询
 * Created by xiaoyao9184 on 2017/1/3.
 */
@SuppressWarnings("Duplicates")
public interface TemplateDistinctSearchArgRenderer {

    /**
     * 多表查询+主表参数过滤
     * @param entityTemplate 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJSql getDistinctJoinByArgsSql(EntityTemplate entityTemplate, Object... args);
}
