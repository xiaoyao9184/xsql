package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Current_Request_Id;
import com.xy.xsql.tsql.model.functions.system.Current_Transaction_Id;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CurrentTransactionIdConverter
        implements ModelMetaBlockConverter<Current_Transaction_Id> {

    public static BlockMeta meta = noParam(Function.Keywords.CURRENT_TRANSACTION_ID);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
