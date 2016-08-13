package com.xy.xsql.orm.mapping;

import com.xy.xsql.orm.annotation.EntityColumn;
import com.xy.xsql.orm.annotation.EntitySql;
import com.xy.xsql.orm.model.BaseRowNumberList;
import com.xy.xsql.orm.util.CheckUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.springframework.jdbc.support.JdbcUtils;
//import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

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
//        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
//        ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
//        ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
    }

    /**
     * Map -> BaseListVo
     * @param map Map
     * @param clazz VO Class
     * @return T
     */
    public static <T> T build(Map<String, Object> map, Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        T baseListVo = buildObjectByTClass(map, clazz, null);
        buildObjectByTClass(map, BaseRowNumberList.class, baseListVo);
        return baseListVo;
    }

    /**
     *
     * @param map Map
     * @param clazz VO Class
     * @param instance VO Object
     * @param <T> T
     * @return T
     */
    public static <T> T buildObjectByTClass(Map<String, Object> map, Class<? extends T> clazz, T instance) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T newInstance = instance;

        if(instance == null){
            newInstance = clazz.newInstance();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            String mapName = null;
            Object mapValue = null;
            EntitySql entitySql = clazz.getAnnotation(EntitySql.class);
            if (entitySql == null) {
                if(field.getAnnotations().length > 0){
                    EntityColumn entityColumn = field.getAnnotation(EntityColumn.class);
                    if(entityColumn != null){
                        mapName = entityColumn.name();
                    }
                }
            }
            if(CheckUtil.isNullOrEmpty(mapName)){
                mapName = field.getName().toLowerCase();
            }
            if(map.containsKey(mapName)){
                mapValue = map.get(mapName);
            }
//            ReflectionUtils.setField(field,newInstance,mapValue);
            BeanUtils.copyProperty(newInstance, field.getName(), mapValue);
        }

        return newInstance;
    }

    /**
     * ResultSet -> BaseListVo
     * @param clazz Class
     * @param rs ResultSet
     * @return BaseListVo
     */
    public static BaseRowNumberList build(ResultSet rs, Class<?> clazz) throws IllegalAccessException, SQLException, InstantiationException, InvocationTargetException {
        BaseRowNumberList baseListVo = (BaseRowNumberList) buildObjectByTClass(rs, clazz, null);
        return buildObjectByTClass(rs, BaseRowNumberList.class, baseListVo);
    }

    /**
     * ResultSet -> T
     * @param rs ResultSet
     * @param clazz Class
     * @param instance Object
     * @param <T> T
     * @return T
     */
    public static <T> T buildObjectByTClass(ResultSet rs, Class<? extends T> clazz, T instance) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException {
        T newInstance = instance;
        if(instance == null){
            newInstance = clazz.newInstance();
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            String rsName = null;
            Object rsValue = null;
            EntitySql entitySql = clazz.getAnnotation(EntitySql.class);
            if (entitySql == null) {
                if(field.getAnnotations().length > 0){
                    EntityColumn entityColumn = field.getAnnotation(EntityColumn.class);
                    if(entityColumn != null){
                        rsName = entityColumn.name();
                    }
                }
            }
            if(CheckUtil.isNullOrEmpty(rsName)){
                rsName = field.getName().toLowerCase();
            }
            // TODO: 2016/6/3 不支持List字段
            int index = -1;
            try {
                index = rs.findColumn(rsName);
            } catch (SQLException e) {
                //not exists
            }
            if(index != -1){
                rsValue = JdbcUtils.getResultSetValue(rs, index, field.getType());
                //if(!CheckUtil.isNull(rsValue)){
//                ReflectionUtil.setField(field,newInstance,rsValue);
                BeanUtils.copyProperty(newInstance, field.getName(), rsValue);
                //}
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
}
