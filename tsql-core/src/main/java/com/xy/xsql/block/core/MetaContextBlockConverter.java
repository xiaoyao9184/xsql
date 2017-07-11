package com.xy.xsql.block.core;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface MetaContextBlockConverter<CONTEXT>
        extends BlockConverter<CONTEXT,MetaContextBlock> {

    @Override
    default MetaContextBlock build(CONTEXT context){
        return convert(context);
    }

    MetaContextBlock convert(CONTEXT context);

    BlockMeta meta();

}
