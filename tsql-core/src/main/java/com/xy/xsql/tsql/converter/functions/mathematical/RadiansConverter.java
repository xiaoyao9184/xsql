package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Abs;
import com.xy.xsql.tsql.model.functions.mathematical.Radians;

import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramFloatExpression;
import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramNumericExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RadiansConverter
        implements ModelMetaBlockConverter<Radians> {

    public static BlockMeta meta = paramNumericExpression(Function.Keywords.RADIANS);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
