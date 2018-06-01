package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Error_Message;
import com.xy.xsql.tsql.model.functions.system.Error_Number;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ErrorNumberConverter
        implements ModelMetaBlockConverter<Error_Number> {

    public static BlockMeta meta = noParam(Function.Keywords.ERROR_NUMBER);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
