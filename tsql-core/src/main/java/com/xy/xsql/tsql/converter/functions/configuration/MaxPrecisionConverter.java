package com.xy.xsql.tsql.converter.functions.configuration;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.configuration.$$Max_Precision;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class MaxPrecisionConverter
        implements ModelMetaBlockConverter<$$Max_Precision> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$MAX_PRECISION);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
