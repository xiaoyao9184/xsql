package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.CharIndex;
import com.xy.xsql.tsql.model.functions.string.Concat;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ConcatConverter
        implements ModelMetaBlockConverter<Concat> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Concat>()
                    .overall("CONCAT")
                    .sub_keyword(Function.Keywords.CONCAT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string_value")
                        .list()
                        .scope(d -> d.getStringValueList())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
