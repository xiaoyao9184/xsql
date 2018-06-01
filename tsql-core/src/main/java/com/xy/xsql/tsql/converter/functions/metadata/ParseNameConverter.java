package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ObjectProperty;
import com.xy.xsql.tsql.model.functions.metadata.ParseName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ParseNameConverter
        implements ModelMetaBlockConverter<ParseName> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ParseName>()
                    .overall("PARSENAME")
                    .sub_keyword(Function.Keywords.PARSENAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'object_name'")
                        .scope(d -> d.getObjectName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("object_piece")
                        .scope(d -> d.getObjectPiece())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
