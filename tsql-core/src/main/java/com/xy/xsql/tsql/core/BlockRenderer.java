package com.xy.xsql.tsql.core;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2017/5/13.
 */
public interface BlockRenderer<Target>
        extends BaseBuilder<Block, Target> {


    default Target build(Block block){
        return render(block);
    }

    Target render(Block block);
}
