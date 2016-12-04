package com.xy.xsql.orm.core.entity.sql.agreement;


import com.xy.xsql.orm.data.entity.EntityTemplate;

/**
 * 表结构
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntityTableManage {

    /**
     * 表数据
     * @param entityData 实体信息
     * @return SQL
     */
    String getTableCountSql(EntityTemplate entityData);

    /**
     * 创建表
     * @param entityData 实体信息
     * @return SQL
     */
    String getCreateTableSql(EntityTemplate entityData);

    /**
     * 删除表
     * @param entityData 实体信息
     * @return SQL
     */
    String getDropTableSql(EntityTemplate entityData);

    /**
     * 修改表字段
     * @param entityDataOld 实体信息
     * @param entityDataNew 实体信息
     * @return SQL
     */
    String getAlterTableSql(EntityTemplate entityDataOld, EntityTemplate entityDataNew);
}
