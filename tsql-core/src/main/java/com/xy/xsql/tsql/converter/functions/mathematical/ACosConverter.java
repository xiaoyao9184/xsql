package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ACos;
import com.xy.xsql.tsql.model.functions.mathematical.Abs;

import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramFloatExpression;
import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramNumericExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ACosConverter
        implements ModelMetaBlockConverter<ACos> {

    public static BlockMeta meta = paramFloatExpression(Function.Keywords.ACOS);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
