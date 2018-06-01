package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Error_Severity;
import com.xy.xsql.tsql.model.functions.system.Get_Filestream_Transaction_Context;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GetFilestreamTransactionContextConverter
        implements ModelMetaBlockConverter<Get_Filestream_Transaction_Context> {

    public static BlockMeta meta = noParam(Function.Keywords.GET_FILESTREAM_TRANSACTION_CONTEXT);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
