package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Current_Request_Id;
import com.xy.xsql.tsql.model.functions.system.Error_Line;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ErrorLineConverter
        implements ModelMetaBlockConverter<Error_Line> {

    public static BlockMeta meta = noParam(Function.Keywords.ERROR_LINE);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
