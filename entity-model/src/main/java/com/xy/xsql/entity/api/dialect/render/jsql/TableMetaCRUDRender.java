package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.api.meta.TableMeta;

/**
 * 单表 增删改查
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TableMetaCRUDRender {

    /**
     * 插入
     * @param tableMeta 实体 信息
     * @return SQL
     */
    String getInsertSql(TableMeta tableMeta);

    /**
     * 插入
     * @param tableMeta 实体 信息
     * @param entityCount 实体 数量
     * @return SQL
     */
    String getInsertByEntityCountSql(TableMeta tableMeta, int entityCount);

    /**
     * 获取
     * @param tableMeta 实体 信息
     * @return SQL
     */
    String getSelectByIdSql(TableMeta tableMeta);

    /**
     * 获取
     * @param tableMeta 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getSelectByIdsSql(TableMeta tableMeta, int idCount);

    /**
     * 更新
     * @param tableMeta 实体 信息
     * @return SQL
     */
    String getUpdateByIdSql(TableMeta tableMeta);

    /**
     * 更新
     * @param tableMeta 实体 信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getUpdateByIdsSql(TableMeta tableMeta, int idCount);

    /**
     * 删除
     * @param tableMeta 实体 信息
     * @return SQL
     */
    String getDeleteByIdSql(TableMeta tableMeta);

    /**
     * 删除
     * @param tableMeta 实体信息
     * @param idCount ID 数量
     * @return SQL
     */
    String getDeleteByIdsSql(TableMeta tableMeta, int idCount);

}
