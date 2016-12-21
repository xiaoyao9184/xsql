package com.xy.xsql.orm.core.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplate;

/**
 * 单/多数据 多表 查询
 * Created by xiaoyao9184 on 2016/12/21.
 */
public interface SqlEntitySearchLinkId {

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
