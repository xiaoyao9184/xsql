package com.xy.xsql.orm.build.entity.sql;

import com.xy.xsql.orm.data.param.EntitySiteParam;

import java.util.List;

/**
 * Entity Sql Builder
 * dialect if you want change default implementation
 * Created by xiaoyao9184 on 2016/1/13.
 */
public interface DialectMultiEntitySqlBuilder {

    String createFullSelectSql(List<EntitySiteParam> entitySiteParams);

    String createFullSelectCountSql(List<EntitySiteParam> entitySiteParams);

    String createFullDeleteSql(List<EntitySiteParam> entitySiteParams);

}
