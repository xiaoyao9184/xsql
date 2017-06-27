package com.xy.xsql.block.exception;

import com.xy.xsql.block.model.Block;

/**
 * Created by xiaoyao9184 on 2017/6/8.
 */
public class BlockMissException
        extends BlockException {
    private Class<?> target;
    private String missPart;

    public BlockMissException(Block block, Class<?> target, String missPart){
        super(block);
        this.target = target;
        this.missPart = missPart;
    }
}
