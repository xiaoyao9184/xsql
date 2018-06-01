package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.Crypt_Gen_Random;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByAsymKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByAsymKeyConverter
        implements ModelMetaBlockConverter<DecryptByAsymKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByAsymKey>()
                    .overall("DecryptByAsymKey")
                    .sub_keyword(Function.Keywords.DecryptByAsymKey)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Asym_Key_ID")
                        .scope(d -> d.getAsymKeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> d.getCiphertext() != null, "'ciphertext'")
                            .scope(d -> d.getCiphertext())
                            .and()
                        .czse(d -> d.getCiphertextVariable() != null, "@ciphertext")
                            .scope(d -> d.getCiphertextVariable())
                            .and()
                        .syntax_required()
                        .and()
                    .sub()
                        .optional(d -> d.getAsymKeyPassword() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'Asym_Key_Password'")
                            .scope(d -> d.getAsymKeyPassword())
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
