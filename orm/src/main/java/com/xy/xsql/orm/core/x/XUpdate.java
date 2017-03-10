package com.xy.xsql.orm.core.x;

import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;

import java.util.List;

/**
 * XUpdate
 * Created by xiaoyao9184 on 2016/11/12.
 */
public interface XUpdate<THIS> {

    /**
     * UPDATE
     * @return THIS
     */
    THIS update();

    /**
     * UPDATE table
     * @param table Table Name
     * @return THIS
     */
    THIS update(String table);

    /**
     * UPDATE table
     * @param table Table
     * @return THIS
     */
    THIS update(Table table);

    /**
     * table
     * @param table Table Name
     * @return THIS
     */
    THIS table(String table);

    /**
     * table
     * @param table Table
     * @return THIS
     */
    THIS table(Table table);

    /**
     * SET columns = ?[,columns = ?...]
     * @param columns Column Name Array
     * @return THIS
     */
    THIS set(String... columns);
    
    /**
     * SET columns = ?
     * @param column Column
     * @return THIS
     */
    THIS set(Column column);

    /**
     * SET columns = ?[,columns = ?...]
     * @param columnList Column List
     * @return THIS
     */
    THIS set(List<Column> columnList);

    /**
     * SET columns = valueExpression
     * @param column Column
     * @param value Value
     * @return THIS
     */
    THIS set(Column column, Value value);

    /**
     * SET columns = valueExpression[,columns = valueExpression...]
     * @param columnList Column List
     * @param valueList Value List
     * @return THIS
     */
    THIS set(List<Column> columnList, List<Value> valueList);

}
