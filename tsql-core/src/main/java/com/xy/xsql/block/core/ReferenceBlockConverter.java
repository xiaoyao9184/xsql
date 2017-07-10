package com.xy.xsql.block.core;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface ReferenceBlockConverter<T>
        extends BaseBuilder<T, BlockMeta> {

    @Override
    default BlockMeta build(T t){
        return convert(t);
    }

    BlockMeta convert(T t);

}
