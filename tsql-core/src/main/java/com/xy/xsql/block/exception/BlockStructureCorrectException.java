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

    @Override
    public String getMessage() {
        return super.getMessage() + ": " + correct.name();
    }

    public enum StructureCorrect {

        OPTION_FILTER_MISS,
        OTHER, CONTEXT_MISS;

    }
}
