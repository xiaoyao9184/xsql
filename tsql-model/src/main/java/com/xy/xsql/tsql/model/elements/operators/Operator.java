package com.xy.xsql.tsql.model.elements.operators;


/**
 * Comparison
 * Created by xiaoyao9184 on 2017/3/14.
 */
public interface Operator {

    /**
     *
     * @return
     */
    String toString();

    /**
     * @return
     */
    //TODO return enum
    String getKeyword();

    /**
     *
     * @return
     */
    @Deprecated
    Type getType();
}
