package com.xy.xsql.block.exception;

import com.xy.xsql.block.model.Block;

/**
 * Created by xiaoyao9184 on 2017/6/27.
 */
public class BlockException extends Exception {

    public BlockException(Block block){
        super(block.toString());
    }
}
