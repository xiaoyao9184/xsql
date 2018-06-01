package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Context_Info;
import com.xy.xsql.tsql.model.functions.system.Current_Request_Id;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CurrentRequestIdConverter
        implements ModelMetaBlockConverter<Current_Request_Id> {

    public static BlockMeta meta = noParam(Function.Keywords.CURRENT_REQUEST_ID);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
