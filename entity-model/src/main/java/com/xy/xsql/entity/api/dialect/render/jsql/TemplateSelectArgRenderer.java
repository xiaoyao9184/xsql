package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;

/**
 * 单表 条件查询
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateSelectArgRenderer {

    /**
     * 参数查询
     * @param entityData 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJSql getSelectByArgsSql(EntityInfo entityData, Object... args);

}
