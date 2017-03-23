package com.xy.xsql.entity.api.dialect.jpql;

import com.xy.xsql.entity.model.jpql.PlaceholderJPql;
import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.entity.model.template.EntityTemplate;

/**
 * 多表 条件查询
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface TemplateSearchArgRenderer {

    /**
     * 多表 参数查询
     * @param entityData 实体 信息
     * @param args 参数
     * @return SQL + 参数
     */
    PlaceholderJPql getSelectJoinByArgsSql(EntityTemplate entityData, Object... args);

    /**
     * 多表 参数查询
     * @param entityData 实体 信息
     * @param entityDataTreeArg 参数（树状）
     * @return SQL + 参数
     */
    PlaceholderJPql getSelectJoinByTreeArgSql(EntityTemplate entityData, EntityTemplateTreeArg entityDataTreeArg);
}
