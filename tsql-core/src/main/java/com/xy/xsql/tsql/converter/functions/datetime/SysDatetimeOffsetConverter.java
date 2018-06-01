package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.SysDatetime;
import com.xy.xsql.tsql.model.functions.datetime.SysDatetimeOffset;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SysDatetimeOffsetConverter
        implements ModelMetaBlockConverter<SysDatetimeOffset> {

    public static BlockMeta meta = noParam(Function.Keywords.SYSDATETIMEOFFSET);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
