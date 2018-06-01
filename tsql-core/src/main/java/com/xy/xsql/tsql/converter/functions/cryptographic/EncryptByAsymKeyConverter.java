package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByPassPhrase;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByAsymKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class EncryptByAsymKeyConverter
        implements ModelMetaBlockConverter<EncryptByAsymKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,EncryptByAsymKey>()
                    .overall("EncryptByAsymKey")
                    .sub_keyword(Function.Keywords.EncryptByAsymKey)
                    .sub("Asym_Key_ID")
                        .scope(d -> d.getAsymKeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> d.getPlaintext() != null, "'plaintext'")
                            .scope(d -> d.getPlaintext())
                            .and()
                        .czse(d -> d.getPlaintextVariable() != null, "@plaintext")
                            .scope(d -> d.getPlaintextVariable())
                            .and()
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
