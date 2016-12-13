package com.xy.xsql.orm.mapping.row;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 基本 映射
 * Created by xiaoyao9184 on 2016/4/22.
 */
public class BaseEntityRowMapper<T> implements RowMapper<T> {

    private Class<T> clazz;
    private String rowNumberName;
    private String pageRowNumberName;

    private Map<String,Field> cacheFieldNameMap;

    public BaseEntityRowMapper(Class<T> clazz) {
        this.clazz = clazz;
        initCacheFieldNameMap();
    }

    private void initCacheFieldNameMap(){
        cacheFieldNameMap = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            cacheFieldNameMap.put(field.getName(),field);
        }
    }

    public BaseEntityRowMapper<T> withRowNumberName(String rowNumberName) {
        this.rowNumberName = rowNumberName;
        return this;
    }

    public BaseEntityRowMapper<T> withPageRowNumberName(String pageRowNumberName) {
        this.pageRowNumberName = pageRowNumberName;
        return this;
    }

    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        if(Map.class.isAssignableFrom(this.clazz)){
            //noinspection unchecked
            return (T) BaseMapper.build(rs, rowNum, this.pageRowNumberName);
        }
        try {
            return BaseRomNumberListMapRowMapper.buildObjectByTFieldNameMap(rs, this.cacheFieldNameMap, this.clazz,null);
        } catch (Exception ex){
            throw new SQLException(ex);
        }
    }

}
