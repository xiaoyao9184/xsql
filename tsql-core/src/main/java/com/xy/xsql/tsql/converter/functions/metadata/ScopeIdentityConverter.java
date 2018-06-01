package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Original_Db_Name;
import com.xy.xsql.tsql.model.functions.metadata.Scope_Identity;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ScopeIdentityConverter
        implements ModelMetaBlockConverter<Scope_Identity> {

    public static BlockMeta meta = noParam(Function.Keywords.SCOPE_IDENTITY);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
