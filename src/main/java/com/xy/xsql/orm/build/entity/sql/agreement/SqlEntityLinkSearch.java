package com.xy.xsql.orm.build.entity.sql.agreement;

import com.xy.xsql.orm.data.entity.EntityTemplateData;
import com.xy.xsql.orm.data.param.ArgSql;
import com.xy.xsql.orm.data.param.EntityTemplateDataArgTree;

/**
 * 多数据 多表 查询
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlEntityLinkSearch {

    /**
     * 多表 参数查询
     * @param entityData 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    ArgSql getSelectJoinByArgsSql(EntityTemplateData entityData, Object... args);

    /**
     * 多表 参数查询
     * @param entityData 实体 信息
     * @param entityDataTreeArg 参数（树状）
     * @return SQL + 参数
     */
    ArgSql getSelectJoinByTreeArgSql(EntityTemplateData entityData, EntityTemplateDataArgTree entityDataTreeArg);
}
