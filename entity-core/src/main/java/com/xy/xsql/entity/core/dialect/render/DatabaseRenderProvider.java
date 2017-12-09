package com.xy.xsql.entity.core.dialect.render;

import com.xy.xsql.core.mapper.IndexClassMapper;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.core.provider.GenericSuppliesOnceProvider;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
public class DatabaseRenderProvider
        implements GenericSuppliesOnceProvider<String> {

    private String defaultDB;
    private SourceTargetMapper<String,IndexClassMapper<Class,Object>> map;

    @Override
    public <Render> Render provide(Class<Render> clazz, String database) {
        //TODO
        return null;
    }



}
