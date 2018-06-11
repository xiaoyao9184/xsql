package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Session_User;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SessionUserConverter
        implements ModelMetaBlockConverter<Session_User> {

    public static BlockMeta meta = onlyName(Function.Keywords.SESSION_USER);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
