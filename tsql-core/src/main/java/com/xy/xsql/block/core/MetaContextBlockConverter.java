package com.xy.xsql.block.core;

import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface MetaContextBlockConverter<CONTEXT>
        extends BaseBuilder<CONTEXT, MetaContextBlock> {

    @Override
    default MetaContextBlock build(CONTEXT context){
        return convert(context);
    }

    MetaContextBlock convert(CONTEXT context);

}
