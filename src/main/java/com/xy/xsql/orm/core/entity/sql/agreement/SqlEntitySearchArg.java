package com.xy.xsql.orm.core.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplate;
import com.xy.xsql.orm.data.param.ArgSql;

/**
 * 多数据
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntitySearchArg {

    /**
     * 参数查询
     * @param entityData 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    ArgSql getSelectByArgsSql(EntityTemplate entityData, Object... args);

}
