package com.xy.xsql.entity.core.dialect.type.config;

import com.xy.xsql.entity.api.dialect.type.ETypeMapper;
import com.xy.xsql.entity.core.dialect.type.mapper.PackagesTypeMapper;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
@ETypeMapper(
        packages = "com.xy.xsql.entity.core.dialect.type.entity.group1",
        mapper = PackagesTypeMapper.class)
public class PackagesTypeMapperConfiguration {

}
