package com.xy.xsql.block.tsql.core.operator;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.model.KeywordBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.operator.Operator;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OperatorConverter
        implements BlockConverter<Operator,KeywordBlock> {

    @Override
    public KeywordBlock convert(Operator operator) {
        return new KeywordBlock(operator.getKeyword());
    }

}
