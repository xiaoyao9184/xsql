package com.xy.xsql.tsql.converter.functions.systemstatistical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.systemstatistical.$$Total_Write;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TotalWriteConverter
        implements ModelMetaBlockConverter<$$Total_Write> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$TOTAL_WRITE);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
