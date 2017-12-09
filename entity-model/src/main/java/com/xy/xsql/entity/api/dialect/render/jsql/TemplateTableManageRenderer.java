package com.xy.xsql.entity.api.dialect.render.jsql;

import com.xy.xsql.entity.model.template.EntityInfo;

/**
 * 表结构
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateTableManageRenderer {

    /**
     * 表数据
     * @param entityData 实体信息
     * @return SQL
     */
    String getTableCountSql(EntityInfo entityData);

    /**
     * 创建表
     * @param entityData 实体信息
     * @return SQL
     */
    String getCreateTableSql(EntityInfo entityData);

    /**
     * 删除表
     * @param entityData 实体信息
     * @return SQL
     */
    String getDropTableSql(EntityInfo entityData);

    /**
     * 修改表字段
     * @param entityDataOld 实体信息
     * @param entityDataNew 实体信息
     * @return SQL
     */
    String getAlterTableSql(EntityInfo entityDataOld, EntityInfo entityDataNew);
}
