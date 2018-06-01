package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Cot;
import com.xy.xsql.tsql.model.functions.mathematical.Exp;

import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramFloatExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExpConverter
        implements ModelMetaBlockConverter<Exp> {

    public static BlockMeta meta = paramFloatExpression(Function.Keywords.EXP);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
