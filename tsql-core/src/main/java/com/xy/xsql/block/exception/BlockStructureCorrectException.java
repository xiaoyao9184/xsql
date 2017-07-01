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
        OTHER, CONTEXT_MISS, COLLECTION_CONTEXT_MISS, COLLECTION_META_AMOUNT_ERROR, COLLECTION_CONTEXT_MUST_LIST, NOTHING_PASS_EXCLUSIVE, NO_DATA, COLLECTION_DATA_CANT_FIND_BLOCK_META;

    }
}
