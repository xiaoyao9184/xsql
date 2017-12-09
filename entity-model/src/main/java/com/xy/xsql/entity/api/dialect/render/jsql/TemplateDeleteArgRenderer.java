package com.xy.xsql.entity.api.dialect.render.jsql;


import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * 单表 条件删除
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateDeleteArgRenderer {

    /**
     * 参数查询
     * @param entityInfo 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJSql getDeleteByArgsSql(EntityInfo entityInfo, Object... args);

}
