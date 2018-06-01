package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.systemstatistical.$$Packet_Errors;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PacketErrorsConverter
        implements ModelMetaBlockConverter<$$Packet_Errors> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$PACKET_ERRORS);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
