package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.AssemblyProperty;
import com.xy.xsql.tsql.model.functions.metadata.Col_Length;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ColLengthConverter
        implements ModelMetaBlockConverter<Col_Length> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Col_Length>()
                    .overall("COL_LENGTH")
                    .sub_keyword(Function.Keywords.COL_LENGTH)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'table'")
                        .scope(d -> d.getTable())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'column'")
                        .scope(d -> d.getColumn())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
