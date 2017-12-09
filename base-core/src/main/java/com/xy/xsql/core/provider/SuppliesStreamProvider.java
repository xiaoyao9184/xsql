package com.xy.xsql.core.provider;

import java.util.function.Consumer;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface SuppliesStreamProvider<PRODUCT,SUPPLIES>
        extends StreamProvider<PRODUCT> {


    boolean provide(SUPPLIES supplies);

    /**
     * Registered Consumer for consumption if the Provider provides the product
     * @param consumer Consumer for consumption
     * @param supplies Supplies
     * @return success
     */
    default boolean provide(Consumer<PRODUCT> consumer,SUPPLIES supplies){
        return provide(consumer) && provide(supplies);
    }
}
