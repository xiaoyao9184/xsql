package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Current;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Incr;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IdentIncrConverter
        implements ModelMetaBlockConverter<Ident_Incr> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Ident_Incr>()
                    .overall("IDENT_INCR")
                    .sub_keyword(Function.Keywords.IDENT_INCR)
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
