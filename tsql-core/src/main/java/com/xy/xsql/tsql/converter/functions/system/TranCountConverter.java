package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.$$TranCount;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TranCountConverter
        implements ModelMetaBlockConverter<$$TranCount> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$TRANCOUNT);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
