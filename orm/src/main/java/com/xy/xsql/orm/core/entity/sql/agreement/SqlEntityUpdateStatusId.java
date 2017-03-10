package com.xy.xsql.orm.core.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplate;

/**
 * 单表 Status字段 ID更新
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntityUpdateStatusId {

    /**
     * 更新状态字段
     * @param entityData 实体 信息
     * @return SQL
     */
    String getUpdateStatusByIdSql(EntityTemplate entityData);

    /**
     * 更新状态字段
     * @param entityData 实体 信息
     * @param idCount ID数量
     * @return SQL
     */
    String getUpdateStatusByIdsSql(EntityTemplate entityData, int idCount);

}
