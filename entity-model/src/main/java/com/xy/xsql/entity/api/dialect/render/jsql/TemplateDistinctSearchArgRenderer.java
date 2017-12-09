package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * 多数据 多表 除重 查询
 * Created by xiaoyao9184 on 2017/1/3.
 */
@SuppressWarnings("Duplicates")
public interface TemplateDistinctSearchArgRenderer {

    /**
     * 多表查询+主表参数过滤
     * @param entityInfo 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJSql getDistinctJoinByArgsSql(EntityInfo entityInfo, Object... args);
}
