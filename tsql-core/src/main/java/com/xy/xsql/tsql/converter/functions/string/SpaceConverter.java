package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Len;
import com.xy.xsql.tsql.model.functions.string.Soundex;
import com.xy.xsql.tsql.model.functions.string.Space;

import static com.xy.xsql.tsql.converter.functions.string.StringConverters.paramCharacterExpression;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SpaceConverter
        implements ModelMetaBlockConverter<Space> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Space>()
                    .overall("SPACE")
                    .sub_keyword(Function.Keywords.SPACE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("integer_expression")
                        .scope(d -> d.getIntegerExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
