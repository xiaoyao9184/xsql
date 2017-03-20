package com.xy.xsql.core.builder;

/**
 * Base Builder
 * for all builder
 * Created by xiaoyao9184 on 2016/10/15.
 * @param <Source> build source
 * @param <Target> build target
 */
public interface BaseBuilder<Source, Target> {

    /**
     * core
     * @param source build Source
     * @return Tar
     */
    Target build(Source source);
}
