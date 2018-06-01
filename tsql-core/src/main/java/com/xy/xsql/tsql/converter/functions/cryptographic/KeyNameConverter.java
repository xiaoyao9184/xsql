package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class KeyNameConverter
        implements ModelMetaBlockConverter<Key_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Key_Name>()
                    .overall("KEY_NAME")
                    .sub_keyword(Function.Keywords.KEY_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getCiphertext() != null, "ciphertext")
                            .scope(d -> d.getCiphertext())
                            .and()
                        .czse(d -> d.getKeyGuid() != null, "key_guid")
                            .scope(d -> d.getKeyGuid())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
