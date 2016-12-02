package com.xy.xsql.orm.build.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplateData;

/**
 * 单/多条数据 Status字段 更新
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntityStatusUpdate {
    /**
     * 更新状态字段
     * @param entityData 实体 信息
     * @return SQL
     */
    String getUpdateStatusByIdSql(EntityTemplateData entityData);

    /**
     * 更新状态字段
     * @param entityData 实体 信息
     * @param idCount ID数量
     * @return SQL
     */
    String getUpdateStatusByIdsSql(EntityTemplateData entityData, int idCount);
}
