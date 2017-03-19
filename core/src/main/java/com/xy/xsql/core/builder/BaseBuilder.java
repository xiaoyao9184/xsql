package com.xy.xsql.core.builder;

/**
 * Base Builder
 * for all builder
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface BaseBuilder<SrcType, TarType> {

    /**
     * core
     * @param srcType Src
     * @return Tar
     */
    TarType build(SrcType srcType);
}
