package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.model.template.EntityInfo;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;

/**
 * 多表 分页 条件查询
 * Created by xiaoyao9184 on 2016/12/29.
 */
public interface TemplatePageSearchArgRenderer {

    /**
     * 多表 参数查询
     * @param entityInfo 实体 信息
     * @param pageStart Page Start with 1
     * @param pageSize Page Size
     * @param rowNumberName Row Number Name
     * @param entityTemplateTreeArg 参数（树状）
     * @return SQL + 参数
     */
    PlaceholderJSql getSelectJoinByTreeArgPageSql(EntityInfo entityInfo, Integer pageStart, Integer pageSize, String rowNumberName, EntityTemplateTreeArg entityTemplateTreeArg);

    /**
     * 多表 参数查询
     * @param entityInfo 实体 信息
     * @param entityTemplateTreeArg 参数（树状）
     * @return SQL + 参数
     */
    PlaceholderJSql getSelectJoinByTreeArgCountSql(EntityInfo entityInfo, EntityTemplateTreeArg entityTemplateTreeArg);
}
