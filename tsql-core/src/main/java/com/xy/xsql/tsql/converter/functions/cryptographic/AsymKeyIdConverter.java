package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AsymKeyIdConverter
        implements ModelMetaBlockConverter<AsymKey_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AsymKey_Id>()
                    .overall("ASYMKEY_ID")
                    .sub_keyword(Function.Keywords.ASYMKEY_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'Asym_Key_Name'")
                        .scope(d -> d.getAsymKeyName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
