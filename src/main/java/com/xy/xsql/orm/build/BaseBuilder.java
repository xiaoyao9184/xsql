package com.xy.xsql.orm.build;

/**
 * Base Builder
 * for all builder
 * Created by xiaoyao9184 on 2016/10/15.
 */
public interface BaseBuilder<SrcType, TarType> {

    /**
     * build
     * @param srcType Src
     * @return Tar
     */
    TarType build(SrcType srcType);
}
