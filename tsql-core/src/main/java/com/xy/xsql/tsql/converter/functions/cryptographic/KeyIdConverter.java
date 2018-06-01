package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_GUID;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class KeyIdConverter
        implements ModelMetaBlockConverter<Key_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Key_Id>()
                    .overall("Key_ID")
                    .sub_keyword(Function.Keywords.Key_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'Key_Name'")
                        .scope(d -> d.getKeyName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
