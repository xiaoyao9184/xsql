package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.App_Name;
import com.xy.xsql.tsql.model.functions.metadata.Original_Db_Name;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OriginalDbNameConverter
        implements ModelMetaBlockConverter<Original_Db_Name> {

    public static BlockMeta meta = noParam(Function.Keywords.ORIGINAL_DB_NAME);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
