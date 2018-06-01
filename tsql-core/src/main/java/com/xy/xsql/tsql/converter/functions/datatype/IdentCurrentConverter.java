package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.DataLength;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Current;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IdentCurrentConverter
        implements ModelMetaBlockConverter<Ident_Current> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Ident_Current>()
                    .overall("IDENT_CURRENT")
                    .sub_keyword(Function.Keywords.IDENT_CURRENT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'table_name'")
                        .scope(d -> d.getTableName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
