package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Min_Active_Rowversion;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class MinActiveRowversionConverter
        implements ModelMetaBlockConverter<Min_Active_Rowversion> {

    public static BlockMeta meta = onlyName(Function.Keywords.MIN_ACTIVE_ROWVERSION);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
