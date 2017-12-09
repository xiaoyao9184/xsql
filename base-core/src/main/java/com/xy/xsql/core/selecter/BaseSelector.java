package com.xy.xsql.core.selecter;

/**
 * Created by xiaoyao9184 on 2017/9/26
 */
public interface BaseSelector<Select,Result> {

    /**
     * Select result
     * @return result
     */
    Result select(Select select);
}
