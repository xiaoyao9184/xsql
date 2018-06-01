package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.systemstatistical.$$Pack_Sent;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PackSentConverter
        implements ModelMetaBlockConverter<$$Pack_Sent> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$PACK_SENT);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
