package com.xy.xsql.core.provider;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface OnceProvider<PRODUCT> {

    /**
     * Provides product
     * @return product
     */
    PRODUCT provide();

}
