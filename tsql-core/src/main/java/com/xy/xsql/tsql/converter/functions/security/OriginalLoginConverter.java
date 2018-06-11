package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Original_Login;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class OriginalLoginConverter
        implements ModelMetaBlockConverter<Original_Login> {

    public static BlockMeta meta = noParam(Function.Keywords.ORIGINAL_LOGIN);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
