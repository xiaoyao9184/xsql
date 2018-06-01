package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ASin;

import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramFloatExpression;
import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramNumericExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ATanConverter
        implements ModelMetaBlockConverter<ASin> {

    public static BlockMeta meta = paramFloatExpression(Function.Keywords.ATAN);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
