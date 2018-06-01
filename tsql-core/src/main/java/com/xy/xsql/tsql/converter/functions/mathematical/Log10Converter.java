package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ACos;
import com.xy.xsql.tsql.model.functions.mathematical.Log10;

import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramFloatExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class Log10Converter
        implements ModelMetaBlockConverter<Log10> {

    public static BlockMeta meta = paramFloatExpression(Function.Keywords.LOG10);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
