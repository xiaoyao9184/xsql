package com.xy.xsql.orm.core.x;

import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.data.sql.element.info.*;

import java.util.List;
import java.util.Map;

/**
 * XCase
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface XSelect<THIS> {

    /**
     * SELECT
     * @return THIS
     */
    THIS select();

    /**
     * DISTINCT
     * @return THIS
     */
    THIS distinct();

    /**
     * TOP count [PERCENT]
     * @param count count or percent
     * @param percent true: useLink percent ;false:useLink count
     * @return THIS
     */
    THIS top(int count, boolean percent);

    /**
     * column[,columns]...
     * @param columns Column Name Array
     * @return THIS
     */
    THIS columns(String... columns);

    /**
     * column[,columns]...
     * @param columnList Column List
     * @return THIS
     */
    THIS columns(List<Column> columnList);

    /**
     * FORM
     * @return THIS
     */
    THIS from();

    /**
     * FORM table
     * @param tableName Table Name
     * @return THIS
     */
    THIS from(String tableName);

    /**
     * FORM table
     * default format to as entitySql
     * @param table Table
     * @return THIS
     */
    THIS from(Table table);

    /**
     * FORM sentence
     * default format to as entitySql, use subs entitySql
     * @param sentence Sentence
     * @return THIS
     */
    THIS from(Sentence sentence);

    /**
     * LEFT JOIN
     * @return THIS
     */
    THIS leftjoin();

    /**
     * LEFT JOIN table
     * @param tableName Table Name
     * @return THIS
     */
    THIS leftjoin(String tableName);

    /**
     * LEFT JOIN table
     * default format to as entitySql
     * @param table Table
     * @return THIS
     */
    THIS leftjoin(Table table);

    /**
     * LEFT Join append TABLE and PARAM
     * @param leftJoinParam Table Param Map
     * @return THIS
     */
    THIS leftjoin(Map<Table, List<Param>> leftJoinParam);

    /**
     * ON
     * @return THIS
     */
    THIS on();

    /**
     * ON columnName relationship ?
     * @param columnName Column Name
     * @param relationship Relationship String
     * @return THIS
     */
    THIS on(String columnName, String relationship);

    /**
     * ON column = leftColumn
     * @param column Main Column
     * @param leftColumn Left Column
     * @return THIS
     */
    THIS on(Column column, Column leftColumn);

    /**
     * ON column = valueExpression
     * @param column Main Column
     * @param value Value
     * @return THIS
     */
    THIS on(Column column, Value value);

    /**
     * ON column relationship valueExpression
     * @param param param
     * @return THIS
     */
    THIS on(Param param);

    /**
     * ORDER BY
     * @return THIS
     */
    THIS orderBy();

    /**
     * ORDER BY column[,column]...
     * @param columns Column Name Array
     * @return THIS
     */
    THIS orderBy(String... columns);

    /**
     * ORDER BY column AES/DESC[,column AES/DESC]...
     * @param orderList Order List
     * @return THIS
     */
    THIS orderBy(List<Order> orderList);

}
