package com.xy.xsql.block.core.converter;

import com.xy.xsql.block.model.Block;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface BlockConverter<T,B extends Block>
        extends BaseBuilder<T,B> {

    @Override
    default B build(T t){
        return convert(t);
    }

    B convert(T t);

}
