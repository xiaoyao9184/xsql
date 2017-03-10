package com.xy.xsql.orm.core.x;

import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;

import java.util.List;

/**
 * XInsert
 * Created by xiaoyao9184 on 2016/11/12.
 */
public interface XInsert<THIS> {
    
    /**
     * INSERT
     * @return THIS
     */
    THIS insert();

    /**
     * INTO
     * @return THIS
     */
    THIS into();

    /**
     * INSERT INTO table
     * @param tableName Table Name
     * @return THIS
     */
    THIS insertInto(String tableName);

    /**
     * INSERT INTO table
     * @param table Table
     * @return THIS
     */
    THIS insertInto(Table table);

    /**
     * table
     * @param tableName Table Name
     * @return THIS
     */
    THIS table(String tableName);

    /**
     * table
     * @param table Table
     * @return THIS
     */
    THIS table(Table table);

    /**
     * (column[,columns]...)
     * @param columns Column Name Array
     * @return THIS
     */
    THIS columns(String... columns);

    /**
     * (column[,columns]...)
     * @param columnList Column List
     * @return THIS
     */
    THIS columns(List<Column> columnList);

    /**
     * VALUES (?[,?]...)
     * @param valueCount Value Count
     * @return THIS
     */
    THIS values(int valueCount);

    /**
     * VALUES (?[,?]...)[ (?[,?]...)]...
     * @param valueCount Value Count
     * @param valueRepeatCount Value Repeat Count
     * @return THIS
     */
    THIS values(int valueCount, int valueRepeatCount);

    /**
     * VALUES (valueExpression[,valueExpression]...)
     * @param valueList Value List
     * @return THIS
     */
    THIS values(List<Value> valueList);

    /**
     * VALUES (valueExpression[,valueExpression]...)[ (valueExpression[,valueExpression]...)]...
     * @param valueRepeatList Value Repeat List
     * @return THIS
     */
    THIS values(List<Value>... valueRepeatList);

    /**
     * (column[,columns]...) VALUES (?[,?]...)
     * @param columns Column Name Array
     * @return THIS
     */
    THIS columnsValues(String... columns);

    /**
     * (column[,columns]...) VALUES (?[,?]...)
     * @param columnList Column List
     * @return THIS
     */
    THIS columnsValues(List<Column> columnList);

    /**
     * (column[,columns]...) VALUES (?[,?]...)[ (?[,?]...)]...
     * @param columnList Column List
     * @param valueRepeatCount Value Repeat Count
     * @return THIS
     */
    THIS columnsValues(List<Column> columnList, int valueRepeatCount);

    /**
     * (column[,columns]...) VALUES (valueExpression[,valueExpression]...)
     * @param columnList Column List
     * @param valueList Value List
     * @return THIS
     */
    THIS columnsValues(List<Column> columnList, List<Value> valueList);

    /**
     * (column[,columns]...) VALUES (valueExpression[,valueExpression]...)[ (valueExpression[,valueExpression]...)]...
     * @param columnList Column List
     * @param valueGroupList Value Repeat List
     * @return THIS
     */
    THIS columnsValues(List<Column> columnList, List<Value>... valueGroupList);

}
