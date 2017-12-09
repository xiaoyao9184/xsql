package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.model.template.EntityInfo;

/**
 * 多表 ID查询
 * Created by xiaoyao9184 on 2016/12/21.
 */
public interface TemplateSearchIdRenderer {

    /**
     * 多表 ID查询
     * @param entityInfo 实体 信息
     * @return SQL
     */
    String getSelectJoinByIdSql(EntityInfo entityInfo);

    /**
     * 多表 ID查询
     * @param entityInfo 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getSelectJoinByIdsSql(EntityInfo entityInfo, int idCount);

}
