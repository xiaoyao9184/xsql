package com.xy.xsql.orm.core.x;

/**
 * XCase
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface XCase<THIS> {

    /**
     * CASE
     * @param columnName Column Name
     * @return THIS
     */
    THIS caseStart(String columnName);

    /**
     * WHEN append THEN
     * @param count When Then Count
     * @return THIS
     */
    THIS whenThen(int count);

    /**
     * WHEN append THEN
     * @param whenValue When Value
     * @param thenValue Then Value
     * @return THIS
     */
    THIS whenThen(String whenValue, String thenValue);

    /**
     * END
     * @return THIS
     */
    THIS caseEnd();

}
