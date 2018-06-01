package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByCert;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByKeyConverter
        implements ModelMetaBlockConverter<DecryptByKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByKey>()
                    .overall("DecryptByKey")
                    .sub_keyword(Function.Keywords.DecryptByKey)
                    .sub_keyword(Other.GROUP_START)
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
                        .optional(d -> d.getAddAuthenticator() == null
                                && d.getAuthenticator() == null
                                && d.getAuthenticatorVariable() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("add_authenticator")
                            .scope(d -> d.getAddAuthenticator())
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getAuthenticator() != null, "authenticator")
                                .scope(d -> d.getAuthenticator())
                                .and()
                            .czse(d -> d.getAuthenticatorVariable() != null, "@authenticator")
                                .scope(d -> d.getAuthenticatorVariable())
                                .and()
                            .syntax_required()
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
