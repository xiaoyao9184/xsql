package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Ascii;
import com.xy.xsql.tsql.model.functions.string.Lower;

import static com.xy.xsql.tsql.converter.functions.string.StringConverters.paramCharacterExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LowerConverter
        implements ModelMetaBlockConverter<Lower> {

    public static BlockMeta meta = paramCharacterExpression(Function.Keywords.LOWER);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
