package com.xy.xsql.spring.template.simple;

/**
 * Created by xiaoyao9184 on 2017/9/22.
 */
public interface ClassEntityManageTemplate {

    Boolean checkTable(Class<?> entityClass);

    void createTable(Class<?> entityClass);

    void dropTable(Class<?> entityClass);
}
