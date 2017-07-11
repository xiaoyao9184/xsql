package com.xy.xsql.block.core;

import com.xy.xsql.block.model.Block;

import java.io.Writer;

/**
 * Created by xiaoyao9184 on 2017/7/11.
 */
public interface BlockPrinter<B extends Block,W extends Writer> {

    W print(B t);

}
