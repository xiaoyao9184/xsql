package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Current_Timestamp;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ProcIdConverter
        implements ModelMetaBlockConverter<Current_Timestamp> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$PROCID);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
