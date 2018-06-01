package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Error_Message;
import com.xy.xsql.tsql.model.functions.system.NewId;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class NewIdConverter
        implements ModelMetaBlockConverter<NewId> {

    public static BlockMeta meta = noParam(Function.Keywords.NEWID);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
