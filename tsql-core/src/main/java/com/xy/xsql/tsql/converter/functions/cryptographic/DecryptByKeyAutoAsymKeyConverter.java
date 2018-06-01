package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKey;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoAsymKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByKeyAutoAsymKeyConverter
        implements ModelMetaBlockConverter<DecryptByKeyAutoAsymKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByKeyAutoAsymKey>()
                    .overall("DecryptByKeyAutoAsymKey")
                    .sub_keyword(Function.Keywords.DecryptByKeyAutoAsymKey)
                    .sub_keyword(Other.GROUP_START)
                    .sub("akey_ID")
                        .scope(d -> d.getAkeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("akey_password")
                        .scope(d -> d.getAkeyPassword())
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
                        .optional(d -> d.getAddAuthenticator() == null
                                && d.getAddAuthenticatorVariable() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getAddAuthenticator() != null, "add_authenticator")
                                .scope(d -> d.getAddAuthenticator())
                                .and()
                            .czse(d -> d.getAddAuthenticatorVariable() != null, "@add_authenticator")
                                .scope(d -> d.getAddAuthenticatorVariable())
                                .and()
                            .syntax_required()
                            .and()
                        .sub()
                            .optional(d -> d.getAuthenticator() == null
                                    && d.getAuthenticatorVariable() == null)
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
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
