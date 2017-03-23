package com.xy.xsql.entity.api.dialect.jpql;


import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 单表 增删改查
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateCRUDRenderer {

    String InsertSql = "getInsertSql";
    String InsertByEntityCountSql = "getInsertByEntityCountSql";
    String SelectByIdSql = "getSelectByIdSql";
    String SelectByIdsSql = "getSelectByIdsSql";
    String UpdateByIdSql = "getUpdateByIdSql";
    String UpdateByIdsSql = "getUpdateByIdsSql";
    String DeleteByIdSql = "getDeleteByIdSql";
    String DeleteByIdsSql = "getDeleteByIdsSql";


    /**
     * 插入
     * @param entityData 实体 信息
     * @return SQL
     */
    String getInsertSql(EntityTemplate entityData);

    /**
     * 插入
     * @param entityData 实体 信息
     * @param entityCount 实体 数量
     * @return SQL
     */
    String getInsertByEntityCountSql(EntityTemplate entityData, int entityCount);

    /**
     * 获取
     * @param entityData 实体 信息
     * @return SQL
     */
    String getSelectByIdSql(EntityTemplate entityData);

    /**
     * 获取
     * @param entityData 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getSelectByIdsSql(EntityTemplate entityData, int idCount);

    /**
     * 更新
     * @param entityData 实体 信息
     * @return SQL
     */
    String getUpdateByIdSql(EntityTemplate entityData);

    /**
     * 更新
     * @param entityData 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getUpdateByIdsSql(EntityTemplate entityData, int idCount);

    /**
     * 删除
     * @param entityData 实体 信息
     * @return SQL
     */
    String getDeleteByIdSql(EntityTemplate entityData);

    /**
     * 删除
     * @param entityData 实体信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getDeleteByIdsSql(EntityTemplate entityData, int idCount);

}
