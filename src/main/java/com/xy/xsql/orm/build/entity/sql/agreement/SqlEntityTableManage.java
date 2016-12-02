package com.xy.xsql.orm.build.entity.sql.agreement;


import com.xy.xsql.orm.data.entity.EntityTemplateData;

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
    String getTableCountSql(EntityTemplateData entityData);

    /**
     * 创建表
     * @param entityData 实体信息
     * @return SQL
     */
    String getCreateTableSql(EntityTemplateData entityData);

    /**
     * 删除表
     * @param entityData 实体信息
     * @return SQL
     */
    String getDropTableSql(EntityTemplateData entityData);

    /**
     * 修改表字段
     * @param entityDataOld 实体信息
     * @param entityDataNew 实体信息
     * @return SQL
     */
    String getAlterTableSql(EntityTemplateData entityDataOld, EntityTemplateData entityDataNew);
}
