package com.xy.xsql.orm.build.entity.sql.agreement;


import com.xy.xsql.orm.data.entity.EntityTemplateData;

/**
 * 单/多数据 增删该查 ID操作
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntityCRUD {

    /**
     * 插入
     * @param entityData 实体 信息
     * @return SQL
     */
    String getInsertSql(EntityTemplateData entityData);

    /**
     * 插入
     * @param entityData 实体 信息
     * @param entityCount 实体 数量
     * @return SQL
     */
    String getInsertByEntityCountSql(EntityTemplateData entityData, int entityCount);

    /**
     * 获取
     * @param entityData 实体 信息
     * @return SQL
     */
    String getSelectByIdSql(EntityTemplateData entityData);

    /**
     * 获取
     * @param entityData 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getSelectByIdsSql(EntityTemplateData entityData, int idCount);

    /**
     * 更新
     * @param entityData 实体 信息
     * @return SQL
     */
    String getUpdateByIdSql(EntityTemplateData entityData);

    /**
     * 更新
     * @param entityData 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getUpdateByIdsSql(EntityTemplateData entityData, int idCount);

    /**
     * 删除
     * @param entityData 实体 信息
     * @return SQL
     */
    String getDeleteByIdSql(EntityTemplateData entityData);

    /**
     * 删除
     * @param entityData 实体信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getDeleteByIdsSql(EntityTemplateData entityData, int idCount);

}
