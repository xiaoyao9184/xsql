package com.xy.xsql.spring.mapping;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.springframework.jdbc.support.JdbcUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.util.ReflectionUtils;

/**
 * 映射
 * Created by xiaoyao9184 on 2016/6/6.
 */
public class BaseMapper {

    static {
        //useDefault
        ConvertUtils.register(new BigDecimalConverter(BigDecimal.ZERO), BigDecimal.class);
//        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
//
//        ConvertUtils.register(new SqlTimestampConverter(null), java.entitySql.Timestamp.class);
//        ConvertUtils.register(new SqlDateConverter(null), java.entitySql.Date.class);
//        ConvertUtils.register(new SqlTimeConverter(null), java.entitySql.Time.class);
    }

    /**
     * ResultSet -> T By
     *
     * @param rs ResultSet
     * @param fieldNameMap Field Name Field Map
     * @param clazz Class
     * @param instance Object
     * @param <T> T
     * @return T
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("Duplicates")
    public static <T> T buildObjectByTFieldNameMap(ResultSet rs, Map<String,Field> fieldNameMap, Class<T> clazz, T instance) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException {
        T newInstance = instance;
        if (instance == null) {
            newInstance = clazz.newInstance();
        }

        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String rsName = meta.getColumnName(i);
            if(fieldNameMap.containsKey(rsName)){
                Field field = fieldNameMap.get(rsName);
                Object rsValue = JdbcUtils.getResultSetValue(rs, i, field.getType());
                BeanUtils.copyProperty(newInstance, field.getName(), rsValue);
            }
        }

        return newInstance;
    }

    /**
     * ResultSet -> Object
     * @param rs
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object buildObjectByClass(ResultSet rs, Class<?> clazz) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object newInstance = null;
        if (rs == null){
            return null;
        }
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();
        if (colCount == 0){
            return null;
        }
        newInstance = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 1; i <= colCount; i++) {
            Object rsValue = rs.getObject(i);
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(
                        metaData.getColumnLabel(i).replaceAll("_", ""))) {//按别名获取
                    if (rsValue != null) {
                        if (rsValue instanceof Timestamp) {
                            Timestamp temp = (Timestamp) rsValue;
                            rsValue = new Date(temp.getTime());
                        }
                        if (rsValue instanceof Clob) {
                            Clob cl = (Clob) rsValue;
                            rsValue = cl.getSubString(1, (int) cl.length());
                        }

//                        ReflectionUtils.setField(field,newInstance,rsValue);
                        BeanUtils.copyProperty(newInstance, field.getName(), rsValue);
                    }
                }
            }
        }
        return newInstance;
    }


    /**
     * ResultSet -> Map
     * @param rs ResultSet
     * @param rowIndex Row Index
     * @param pageRowNumberName Row Index
     * @return Map
     */
    public static Map<String,Object> build(ResultSet rs, int rowIndex, String pageRowNumberName) throws SQLException {
        Map<String,Object> result = new HashMap<>();
        if(pageRowNumberName != null && !pageRowNumberName.isEmpty()){
            result.put(pageRowNumberName,rowIndex+1);
        }

        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String key = meta.getColumnName(i);
            Object value = rs.getObject(key);
            result.put(key, value);
        }

        return result;
    }
}
