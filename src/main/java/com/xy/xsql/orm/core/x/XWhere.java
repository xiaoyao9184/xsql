package com.xy.xsql.orm.core.x;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.info.*;

import java.util.List;

/**
 * XWhere
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface XWhere<THIS> {

    /**
     * WHERE
     * @return THIS
     */
    THIS where();

    /**
     * WHERE append COLUMN relationship and ?
     * @param columnName Column Name
     * @param relationship Relationship
     * @return THIS
     */
    THIS where(String columnName, String relationship);

    /**
     * WHERE append COLUMN relationship and ?
     * @param column Column
     * @param relationship Relationship
     * @return THIS
     */
    THIS where(Column column, OperatorEnum relationship);

    /**
     * WHERE append PARAM
     * @param paramList Param List
     * @return THIS
     */
    THIS where(List<Param> paramList);



    /**
     * IN append COLUMN append ?*{valueCount}
     * 字段后置
     * @see #where()
     * @param columnName Column Name
     * @param valueCount Value Count
     * @return THIS
     */
    THIS in(String columnName, int valueCount);

    /**
     * IN append COLUMN append ?*{valueCount}
     * 字段后置
     * @see #where()
     * @param column Column
     * @param valueCount Value Count
     * @return THIS
     */
    THIS in(Column column, int valueCount);

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @return THIS
     */
    THIS and(String columnName, String relationship);

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param column Column
     * @param relationship Relationship
     * @return THIS
     */
    THIS and(Column column, OperatorEnum relationship);

    /**
     * AND
     * @see #where()
     * @see #leftjoin()
     * @param columnName Column Name
     * @param relationship Relationship
     * @param value Value
     * @return THIS
     */
    THIS and(String columnName, String relationship, String value);

    /**
     * AND
     * @see #where()
     * @param columnName Column Name
     * @param relationship Relationship
     * @return THIS
     */
    THIS or(String columnName, String relationship);

    /**
     * AND
     * @see #where()
     * @param columnName Column Name
     * @param relationship Relationship
     * @param value Value
     * @return THIS
     */
    THIS or(String columnName, String relationship, String value);

}
