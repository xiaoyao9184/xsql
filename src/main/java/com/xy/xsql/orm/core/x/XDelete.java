package com.xy.xsql.orm.core.x;

import com.xy.xsql.orm.data.sql.element.info.Table;

import java.util.List;

/**
 * XInsert
 * Created by xiaoyao9184 on 2016/11/12.
 */
public interface XDelete<THIS> {

    /**
     * DELETE table,table
     * default use alias name
     * @param tableNames Table Name Array
     * @return THIS
     */
    THIS delete(String... tableNames);
    
    /**
     * DELETE table,table
     * default use alias name
     * @param tables Table Array
     * @return THIS
     */
    THIS delete(Table... tables);

    /**
     * DELETE table,table
     * default use alias name
     * @param tableList Table List
     * @return THIS
     */
    THIS delete(List<Table> tableList);

    /**
     * FORM table
     * @param tableName Table Name
     * @return THIS
     */
    THIS from(String tableName);

    /**
     * FORM table
     * @param table Table
     * @return THIS
     */
    THIS from(Table table);

}
