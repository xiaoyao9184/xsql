package com.xy.xsql.entity.core.dialect.type.config;

import com.xy.xsql.entity.api.dialect.type.ETypeMapper;
import com.xy.xsql.entity.core.dialect.type.entity.group1.Entity002;
import com.xy.xsql.entity.core.dialect.type.mapper.EntitiesTypeMapper;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
@ETypeMapper(
        entities = { Entity002.class },
        mapper = EntitiesTypeMapper.class)
public class EntitysTypeMapperConfiguration {

}
