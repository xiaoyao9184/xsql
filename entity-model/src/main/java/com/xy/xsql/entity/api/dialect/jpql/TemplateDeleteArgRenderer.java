package com.xy.xsql.entity.api.dialect.jpql;


import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 单表 条件删除
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateDeleteArgRenderer {

    /**
     * 参数查询
     * @param entityTemplate 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJSql getDeleteByArgsSql(EntityTemplate entityTemplate, Object... args);

}
