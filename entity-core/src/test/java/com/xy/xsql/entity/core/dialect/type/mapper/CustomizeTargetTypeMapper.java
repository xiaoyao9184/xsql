package com.xy.xsql.entity.core.dialect.type.mapper;

import com.xy.xsql.entity.api.dialect.type.EntityTypeMapperRegistrar;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.core.dialect.type.entity.Entity000;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
public class CustomizeTargetTypeMapper implements JavaTypeMapper,EntityTypeMapperRegistrar<Entity000> {

    @Override
    public Boolean isSupport(Class<?> aClass) {
        return null;
    }

    @Override
    public String mapType(Class<?> aClass) {
        return null;
    }

    @Override
    public Boolean isSupportLength(Class<?> aClass) {
        return null;
    }

    @Override
    public Integer defaultLength(Class<?> aClass) {
        return null;
    }
}
