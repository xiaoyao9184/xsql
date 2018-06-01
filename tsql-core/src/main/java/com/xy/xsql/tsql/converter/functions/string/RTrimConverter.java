package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.LTrim;
import com.xy.xsql.tsql.model.functions.string.RTrim;

import static com.xy.xsql.tsql.converter.functions.string.StringConverters.paramCharacterExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RTrimConverter
        implements ModelMetaBlockConverter<RTrim> {

    public static BlockMeta meta = paramCharacterExpression(Function.Keywords.RTRIM);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
