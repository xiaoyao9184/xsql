package com.xy.xsql.core.provider;

import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface OptionalGenericSuppliesOnceProvider<SUPPLIES> {

    /**
     * Provides generic product throw supplies
     * @param clazz product class
     * @param supplies Supplies
     * @param <PRODUCT> product
     * @return product
     */
    <PRODUCT> Optional<PRODUCT> provide(Class<PRODUCT> clazz, SUPPLIES supplies);

}
