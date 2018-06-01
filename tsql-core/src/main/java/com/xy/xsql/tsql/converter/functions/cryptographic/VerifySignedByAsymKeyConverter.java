package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByAsymKey;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class VerifySignedByAsymKeyConverter
        implements ModelMetaBlockConverter<VerifySignedByAsymKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,VerifySignedByAsymKey>()
                    .overall("VerifySignedByAsymKey")
                    .sub_keyword(Function.Keywords.VerifySignedByAsymKey)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Asym_Key_ID")
                        .scope(d -> d.getAsymKeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("clear_text")
                        .scope(d -> d.getCleartext())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("signature")
                        .scope(d -> d.getSignature())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
