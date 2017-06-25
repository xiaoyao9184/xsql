package com.xy.xsql.block.core;

import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface BlockConverter<T>
        extends BaseBuilder<T,Block> {

    default ReferenceBlock convert(){
        //TODO
        return (ReferenceBlock) convert(null);
    }

    Block convert(T t);

    @Override
    default Block build(T t){
        return convert(t);
    }

}
