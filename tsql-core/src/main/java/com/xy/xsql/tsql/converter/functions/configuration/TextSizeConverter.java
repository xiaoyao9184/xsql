package com.xy.xsql.tsql.converter.functions.configuration;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.configuration.$$TextSize;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TextSizeConverter
        implements ModelMetaBlockConverter<$$TextSize> {

    public static BlockMeta meta = onlyName(Function.Keywords.$$TEXTSIZE);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
