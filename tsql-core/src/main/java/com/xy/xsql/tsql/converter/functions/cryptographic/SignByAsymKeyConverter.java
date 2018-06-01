package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Name;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByAsymKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SignByAsymKeyConverter
        implements ModelMetaBlockConverter<SignByAsymKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SignByAsymKey>()
                    .overall("SignByAsymKey")
                    .sub_keyword(Function.Keywords.SignByAsymKey)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Asym_Key_ID")
                        .scope(d -> d.getAsymKeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("@plaintext")
                        .scope(d -> d.getPlaintext())
                        .and()
                    .sub()
                        .optional(d -> d.getPassword() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'password'")
                            .scope(d -> d.getPassword())
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
