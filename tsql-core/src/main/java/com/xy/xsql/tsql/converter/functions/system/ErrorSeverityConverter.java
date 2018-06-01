package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Error_Procedure;
import com.xy.xsql.tsql.model.functions.system.Error_Severity;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ErrorSeverityConverter
        implements ModelMetaBlockConverter<Error_Severity> {

    public static BlockMeta meta = noParam(Function.Keywords.ERROR_SEVERITY);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
