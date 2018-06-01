package com.xy.xsql.tsql.converter.functions.replication;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Pi;
import com.xy.xsql.tsql.model.functions.replication.PublishingServerName;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PublishingServerNameConverter
        implements ModelMetaBlockConverter<PublishingServerName> {

    public static BlockMeta meta = noParam(Function.Keywords.PUBLISHINGSERVERNAME);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
