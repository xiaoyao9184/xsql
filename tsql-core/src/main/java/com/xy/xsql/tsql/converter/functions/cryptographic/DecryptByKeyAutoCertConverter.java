package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoAsymKey;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByKeyAutoCertConverter
        implements ModelMetaBlockConverter<DecryptByKeyAutoCert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByKeyAutoCert>()
                    .overall("DecryptByKeyAutoCert")
                    .sub_keyword(Function.Keywords.DecryptByKeyAutoCert)
                    .sub_keyword(Other.GROUP_START)
                    .sub("cert_ID")
                        .scope(d -> d.getCertId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("cert_password")
                        .scope(d -> d.getCertPassword())
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
