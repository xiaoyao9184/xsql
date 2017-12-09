package com.xy.xsql.spring.dialect.h2;

import com.xy.xsql.entity.api.dialect.type.TypeMapper;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2016/10/13.
 */
public class H2TypeMapper implements TypeMapper<Class<?>,String> {

//    private Class<Enum> javaTypeClass = null;
//    private List<Class> supportClass;
    private Map<Class,String> mappingSqlType;
    private Map<Class,Integer> mappingSqlLength;


    public H2TypeMapper(){
        try {
//            javaTypeClass = (Class<Enum>) Class.forName("com.microsoft.sqlserver.jdbc.JavaType");
            this.initSupport();
            this.initMapping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化映射
     */
    private void initMapping() {
        mappingSqlType = DefaultMapper.javaMapSql();
        mappingSqlLength = DefaultMapper.lengthMap();
    }

    /**
     * 初始化支持
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void initSupport() throws NoSuchFieldException, IllegalAccessException {
//        supportClass = new ArrayList<>();
//        if(javaTypeClass == null){
//           return;
//        }
//        Object[] values = javaTypeClass.getEnumConstants();
//        Field javaClassField = javaTypeClass.getDeclaredField("javaClass");
//        javaClassField.setAccessible(true);
//        for (Object value: values) {
//            Class javaClass = (Class) javaClassField.get(value);
//            supportClass.add(javaClass);
//        }
    }


    @Override
    public Boolean isSupport(Class<?> clazz) {
        return mappingSqlType.containsKey(clazz);
//        return this.supportClass.contains(simple);
    }

    @Override
    public String mapType(Class<?> clazz) {
        return this.mappingSqlType.get(clazz);
    }

    @Override
    public Boolean isSupportLength(Class<?> clazz) {
        return this.mappingSqlLength.containsKey(clazz);
    }

    @Override
    public Integer defaultLength(Class<?> clazz) {
        return this.mappingSqlLength.containsKey(clazz) ?
                this.mappingSqlLength.get(clazz) :
                -1;
    }


    public enum DefaultMapper {
        UNKNOWN("unknown", InputStream.class),
        TINYINT("tinyint", Byte.class),
        BIT("bit", Boolean.class),
        SMALLINT("smallint", Short.class),
        INTEGER("int", Integer.class),
        BIGINT("bigint", Long.class),
        FLOAT("float", Float.class),
        DOUBLE("float", Double.class),
        DATETIME("datetime", java.util.Date.class),
        DATE("date", java.sql.Date.class),
        TIME("time", java.sql.Time.class),
        VARCHAR("varchar", String.class, 255),
        BINARY("binary", byte[].class),
        DECIMAL("decimal",BigDecimal.class),
        TIMESTAMP("timestamp",java.sql.Timestamp.class);

        private String sqlType;
        private Integer length;
        private Class javaType;
        
        DefaultMapper(String sqlType,Class javaType){
            this.javaType = javaType;
            this.sqlType = sqlType;
            this.length = -1;
        }
        DefaultMapper(String sqlType,Class javaType, Integer length){
            this.javaType = javaType;
            this.sqlType = sqlType;
            this.length = length;
        }

        public static Map<Class,String> javaMapSql(){
            Map<Class,String> result = new HashMap<>();
            for (DefaultMapper dm: DefaultMapper.values()) {
                result.put(dm.javaType,dm.sqlType);
            }
            return result;
        }

        public static Map<Class,Integer> lengthMap() {
            Map<Class,Integer> result = new HashMap<>();
            for (DefaultMapper dm: DefaultMapper.values()) {
                if(dm.length > 0 ){
                    result.put(dm.javaType,dm.length);
                }
            }
            return result;
        }
    }


}
