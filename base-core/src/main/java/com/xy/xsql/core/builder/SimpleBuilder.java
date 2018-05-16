package com.xy.xsql.core.builder;

/**
 * Simple Builder
 * for all builder
 * Created by xiaoyao9184 on 2018/5/16.
 * @param <Target> build target
 */
public interface SimpleBuilder<Target> {

    /**
     * core
     * @return Tar
     */
    Target build();
}
