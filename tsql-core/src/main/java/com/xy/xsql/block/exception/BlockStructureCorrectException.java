package com.xy.xsql.block.exception;

import com.xy.xsql.block.model.Block;

/**
 * Created by xiaoyao9184 on 2017/6/27.
 */
public class BlockStructureCorrectException
        extends BlockException {

    private StructureCorrect correct;

    public BlockStructureCorrectException(Block block, StructureCorrect correct){
        super(block);
        this.correct = correct;
    }

    public enum StructureCorrect {

        OPTION_FILTER_MISS,
        OTHER;

    }
}
