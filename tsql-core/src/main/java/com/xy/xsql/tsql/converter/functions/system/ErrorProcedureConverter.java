package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Error_Number;
import com.xy.xsql.tsql.model.functions.system.Error_Procedure;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ErrorProcedureConverter
        implements ModelMetaBlockConverter<Error_Procedure> {

    public static BlockMeta meta = noParam(Function.Keywords.ERROR_PROCEDURE);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
