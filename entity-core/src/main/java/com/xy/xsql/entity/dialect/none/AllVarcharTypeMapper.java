package com.xy.xsql.entity.dialect.none;

import com.xy.xsql.entity.api.dialect.TypeMapper;

/**
 * all JAVA type to JDBC VARCHAR
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AllVarcharTypeMapper implements TypeMapper<Class<?>,String> {

    @Override
    public Boolean isSupport(Class<?> aClass) {
        return true;
    }

    @Override
    public String mapType(Class<?> aClass) {
        return "VARCHAR";
    }

    @Override
    public Boolean isSupportLength(Class<?> aClass) {
        return true;
    }

    @Override
    public Integer defaultLength(Class<?> aClass) {
        return 255;
    }


}
