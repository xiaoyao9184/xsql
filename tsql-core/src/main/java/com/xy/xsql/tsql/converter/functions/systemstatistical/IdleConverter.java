package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.systemstatistical.$$Idle;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IdleConverter
        implements ModelMetaBlockConverter<$$Idle> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$IDLE);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
