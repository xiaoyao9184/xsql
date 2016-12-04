package com.xy.xsql.orm.core.entity.sql.agreement;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public interface SqlPage {

    /**
     * 'RowNumber' column in Page Sql
     * @see #getPageSql(String, Integer, Integer)
     * @return Can be NULL if don't use 'RowNumber'
     */
    String getPageSqlRowNumberName();

    /**
     * Page Sql
     * @param selectSql a normal Sql
     * @param pageStart start with 1
     * @param pageSize Page Size
     * @return SQL
     */
    String getPageSql(String selectSql, Integer pageStart, Integer pageSize);

    /**
     * Count Sql
     * @param selectSql a normal Sql
     * @return SQL
     */
    String getCountSql(String selectSql);
}
