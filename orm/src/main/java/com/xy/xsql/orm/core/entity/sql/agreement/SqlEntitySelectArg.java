package com.xy.xsql.orm.core.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;

/**
 * 单表 条件查询
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntitySelectArg {

    /**
     * 参数查询
     * @param entityData 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    ArgSql getSelectByArgsSql(EntityTemplate entityData, Object... args);

}
