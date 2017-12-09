package com.xy.xsql.entity.api.dialect.type;

/**
 * some time must map JAVA type to JDBC type
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface TypeMapper<T,E> {

    /**
     * check this T type can be mapping with this Mapper
     * @param t T type
     * @return yes/no
     */
    Boolean isSupport(T t);

    /**
     * map it
     * @param t T
     * @return map type
     */
    E mapType(T t);

    /**
     * check this T type need length
     * @param t T
     * @return yes/no
     */
    Boolean isSupportLength(T t);


    /**
     * T type default length
     * @param t T
     * @return length
     */
    Integer defaultLength(T t);

}
