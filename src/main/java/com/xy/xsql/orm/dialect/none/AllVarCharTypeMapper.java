package com.xy.xsql.orm.dialect.none;

import com.xy.xsql.orm.mapping.type.TypeMapper;

/**
 * all JAVA type to JDBC VARCHAR
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class AllVarCharTypeMapper implements TypeMapper<Class<?>,String> {

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
