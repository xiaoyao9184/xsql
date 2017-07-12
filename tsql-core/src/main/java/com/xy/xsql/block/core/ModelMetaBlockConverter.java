package com.xy.xsql.block.core;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.ModelMetaBlock;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public interface ModelMetaBlockConverter<MODEL>
        extends BlockConverter<MODEL,ModelMetaBlock> {

    @Override
    default ModelMetaBlock build(MODEL model){
        ModelMetaBlock block = new ModelMetaBlock();
        block.setMeta(meta());
        block.setModel(model);
        return block;
    }

    default ModelMetaBlock convert(MODEL model){
        return build(model);
    }

    BlockMeta meta();

}
