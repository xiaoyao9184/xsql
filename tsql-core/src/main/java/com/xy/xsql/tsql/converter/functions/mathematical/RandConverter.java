package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Power;
import com.xy.xsql.tsql.model.functions.mathematical.Rand;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RandConverter
        implements ModelMetaBlockConverter<Rand> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Rand>()
                    .overall("RAND")
                    .sub_keyword(Function.Keywords.RAND)
                    .sub_keyword(Other.GROUP_START)
                    .sub("seed")
                        .optional(d -> d.getSeed() == null)
                        .scope(d -> d.getSeed())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
