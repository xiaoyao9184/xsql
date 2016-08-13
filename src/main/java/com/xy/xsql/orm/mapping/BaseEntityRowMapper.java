package com.xy.xsql.orm.mapping;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 基本 映射
 * Created by xiaoyao9184 on 2016/4/22.
 */
public class BaseEntityRowMapper<T> implements RowMapper<T> {

    private Class<T> clazz;

    public BaseEntityRowMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T mapRow(ResultSet rs, int arg1) throws SQLException {
        try {
            return BaseRomNumberListMapRowMapper.buildObjectByTClass(rs,clazz,null);
        } catch (Exception ex){
            throw new SQLException(ex);
        }
    }

}
