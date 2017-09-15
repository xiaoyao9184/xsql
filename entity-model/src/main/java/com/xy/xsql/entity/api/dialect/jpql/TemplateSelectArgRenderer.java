package com.xy.xsql.entity.api.dialect.jpql;

import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.EntityTemplate;

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
    PlaceholderJSql getSelectByArgsSql(EntityTemplate entityData, Object... args);

}
