package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Abs;
import com.xy.xsql.tsql.model.functions.string.Ascii;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.noParam;
import static com.xy.xsql.tsql.converter.functions.mathematical.MathematicalConverters.paramNumericExpression;
import static com.xy.xsql.tsql.converter.functions.string.StringConverters.paramCharacterExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AsciiConverter
        implements ModelMetaBlockConverter<Ascii> {

    public static BlockMeta meta = paramCharacterExpression(Function.Keywords.ASCII);

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
