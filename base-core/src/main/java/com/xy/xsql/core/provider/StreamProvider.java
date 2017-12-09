package com.xy.xsql.core.provider;

import java.util.function.Consumer;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public interface StreamProvider<PRODUCT> {

    /**
     * Registered Consumer for consumption if the Provider provides the product
     * @param consumer Consumer for consumption
     * @return success
     */
    boolean provide(Consumer<PRODUCT> consumer);

}
