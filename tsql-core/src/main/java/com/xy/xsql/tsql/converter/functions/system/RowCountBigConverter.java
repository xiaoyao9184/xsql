package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.NewSequentialId;
import com.xy.xsql.tsql.model.functions.system.RowCount_Big;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RowCountBigConverter
        implements ModelMetaBlockConverter<RowCount_Big> {

    public static BlockMeta meta = noParam(Function.Keywords.ROWCOUNT_BIG);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
