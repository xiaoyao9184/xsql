package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.$$Error;
import com.xy.xsql.tsql.model.functions.system.Context_Info;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ContextInfoConverter
        implements ModelMetaBlockConverter<Context_Info> {

    public static BlockMeta meta = noParam(Function.Keywords.CONTEXT_INFO);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
