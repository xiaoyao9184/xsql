package com.xy.xsql.spring.template;

import com.xy.xsql.entity.model.template.param.EntityTemplateTreeArg;
import com.xy.xsql.model.page.PageQuery;
import com.xy.xsql.model.page.PageResult;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface EntityHolderSearchTemplate<Result> {

    List<Result> searchListByArg(Object... args);

    PageResult<Result> searchPageByArg(PageQuery<Result> pageQuery, Object... args);

    List<Result> searchListByTreeArg(EntityTemplateTreeArg entityTemplateTreeArg);

    PageResult<Result> searchPageByTreeArg(PageQuery<Result> pageQuery, EntityTemplateTreeArg entityTemplateTreeArg);

}
