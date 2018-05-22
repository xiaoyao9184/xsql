package com.xy.xsql.tsql.model.datatypes.constants;

/**
 * Specifying Negative and Positive
 * Created by xiaoyao9184 on 2018/5/22.
 */
public interface NegativePositive {

    boolean isUseNegative();

    default boolean isUsePositive(){
        return !isUseNegative();
    }
}
