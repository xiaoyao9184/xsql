package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Incr;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Seed;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IdentSeedConverter
        implements ModelMetaBlockConverter<Ident_Seed> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Ident_Seed>()
                    .overall("IDENT_SEED")
                    .sub_keyword(Function.Keywords.IDENT_SEED)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'table_or_view'")
                        .scope(d -> d.getTableOrView())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
