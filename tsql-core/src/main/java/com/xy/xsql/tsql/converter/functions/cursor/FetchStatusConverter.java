package com.xy.xsql.tsql.converter.functions.cursor;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cursor.$$Fetch_Status;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FetchStatusConverter
        implements ModelMetaBlockConverter<$$Fetch_Status> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$FETCH_STATUS);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
