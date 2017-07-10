package com.xy.xsql.block.core;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.core.builder.BaseBuilder;

/**
 * Created by xiaoyao9184 on 2017/7/10.
 */
public class MetaContextBlockBuilder
        implements BaseBuilder<Object,MetaContextBlock> {

    private MetaContextBlock block = new MetaContextBlock();

    private MetaContextBlockBuilder $meta(BlockMeta meta){
        block.setMeta(meta);
        return this;
    }

    private MetaContextBlockBuilder $context(Object context){
        block.setContext(context);
        return this;
    }

    @Override
    public MetaContextBlock build(Object context) {
        $context(context);
        return block;
    }

    public static MetaContextBlockBuilder meta(BlockMeta meta){
        return new MetaContextBlockBuilder()
                .$meta(meta);
    }
}
