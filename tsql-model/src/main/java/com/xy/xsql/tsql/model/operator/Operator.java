package com.xy.xsql.tsql.model.operator;


import com.xy.xsql.tsql.model.Block;

/**
 * Operator
 * Created by xiaoyao9184 on 2017/3/14.
 */
public interface Operator extends Block {

    /**
     * @see Block#toString()
     * @return
     */
    java.lang.String getKeyword();

    /**
     *
     * @return
     */
    Type getType();
}
