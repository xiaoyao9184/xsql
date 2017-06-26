package com.xy.xsql.block.core;

import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface ReferenceBlockConverter<T>
        extends BaseBuilder<T, ReferenceBlock> {

    @Override
    default ReferenceBlock build(T t){
        return convert(t);
    }

    ReferenceBlock convert(T t);

}
