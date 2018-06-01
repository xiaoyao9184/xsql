package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.$$Datefirst;
import com.xy.xsql.tsql.model.functions.datetime.Current_Timestamp;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CurrentTimestampConverter
        implements ModelMetaBlockConverter<Current_Timestamp> {

    public static BlockMeta meta = onlyName(Function.Keywords.CURRENT_TIMESTAMP);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
