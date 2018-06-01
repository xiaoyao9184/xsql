package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.$$Packet_Received;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PackReceivedConverter
        implements ModelMetaBlockConverter<$$Packet_Received> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$PACK_RECEIVED);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
