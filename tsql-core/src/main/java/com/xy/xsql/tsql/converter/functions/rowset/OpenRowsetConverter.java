package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.rowset.OpenQuery;
import com.xy.xsql.tsql.model.functions.rowset.OpenRowSet;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class OpenRowsetConverter
        implements ModelMetaBlockConverter<OpenRowSet> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OpenRowSet>()
                    .overall("OPENROWSET")
                    .sub_keyword(Function.Keywords.OPENROWSET)
                    .sub_keyword(Other.GROUP_START)
                    //TODO
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
