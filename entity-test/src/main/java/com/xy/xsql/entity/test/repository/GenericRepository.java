package com.xy.xsql.entity.test.repository;

import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;

/**
 * Created by xiaoyao9184 on 2017/9/21
 */
@Repository
public class GenericRepository<T> {

    public String getFirstTypeParameterName(){
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[0].getTypeName();
    }

}
