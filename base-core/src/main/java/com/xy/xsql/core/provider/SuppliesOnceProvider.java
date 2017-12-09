package com.xy.xsql.core.provider;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface SuppliesOnceProvider<PRODUCT,SUPPLIES>
        extends OnceProvider<PRODUCT> {

    /**
     * Provides product throw supplies
     * @param supplies Supplies
     * @return product
     */
    PRODUCT provide(SUPPLIES supplies);

}
