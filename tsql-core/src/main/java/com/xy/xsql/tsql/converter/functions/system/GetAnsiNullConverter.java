package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.GetAnsiNull;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GetAnsiNullConverter
        implements ModelMetaBlockConverter<GetAnsiNull> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,GetAnsiNull>()
                    .overall("GETANSINULL")
                    .sub_keyword(Function.Keywords.GETANSINULL)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'database'")
                        .optional(d -> d.getDatabase() == null)
                        .scope(d -> d.getDatabase())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
