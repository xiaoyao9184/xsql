package com.xy.xsql.block.core;

import com.xy.xsql.block.model.Block;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface BlockConverter<T>
        extends BaseBuilder<T,Block> {

    @Override
    default Block build(T t){
        return convert(t);
    }

    Block convert(T t);

}
