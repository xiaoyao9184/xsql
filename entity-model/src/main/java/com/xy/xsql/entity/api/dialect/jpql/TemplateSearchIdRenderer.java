package com.xy.xsql.entity.api.dialect.jpql;

import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 多表 ID查询
 * Created by xiaoyao9184 on 2016/12/21.
 */
public interface TemplateSearchIdRenderer {

    /**
     * 多表 ID查询
     * @param entityTemplate 实体 信息
     * @return SQL
     */
    String getSelectJoinByIdSql(EntityTemplate entityTemplate);

    /**
     * 多表 ID查询
     * @param entityTemplate 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getSelectJoinByIdsSql(EntityTemplate entityTemplate, int idCount);

}
