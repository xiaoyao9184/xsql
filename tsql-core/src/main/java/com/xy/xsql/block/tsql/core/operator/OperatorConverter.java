package com.xy.xsql.block.tsql.core.operator;

import com.xy.xsql.block.core.converter.BlockConverter;
import com.xy.xsql.block.model.KeywordBlock;
import com.xy.xsql.tsql.model.elements.operators.Operator;

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
