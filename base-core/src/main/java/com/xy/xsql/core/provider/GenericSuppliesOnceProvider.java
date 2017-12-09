package com.xy.xsql.core.provider;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface GenericSuppliesOnceProvider<SUPPLIES> {

    /**
     * Provides generic product throw supplies
     * @param clazz product class
     * @param supplies Supplies
     * @param <PRODUCT> product
     * @return product
     */
    <PRODUCT> PRODUCT provide(Class<PRODUCT> clazz, SUPPLIES supplies);

}
