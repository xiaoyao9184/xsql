package com.xy.xsql.spring.template.simple;

import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface ClassEntitySearchTemplate {

    <Entity,Result> List<Result> searchListByArg(Class<Entity> entityClass, Class<Result> resultClass, Object... args);

    <Entity,Result> PageResult<Result> searchPageByArg(Class<Entity> entityClass, PageQuery<Result> pageQuery, Object... args);

    <Entity,Result> List<Result> searchListByTreeArg(Class<Entity> entityClass, Class<Result> resultClass, EntityTemplateTreeArg entityTemplateTreeArg);

    <Entity,Result> PageResult<Result> searchPageByTreeArg(Class<Entity> entityClass, PageQuery<Result> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg);
}
