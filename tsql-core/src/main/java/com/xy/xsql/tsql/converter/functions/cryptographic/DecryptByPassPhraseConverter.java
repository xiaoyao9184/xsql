package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoCert;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByPassPhrase;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByPassPhraseConverter
        implements ModelMetaBlockConverter<DecryptByPassPhrase> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByPassPhrase>()
                    .overall("DecryptByPassPhrase")
                    .sub_keyword(Function.Keywords.DecryptByPassPhrase)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getPassphrase() != null, "'passphrase'")
                            .scope(d -> d.getPassphrase())
                            .and()
                        .czse(d -> d.getPassphraseVariable() != null, "@passphrase")
                            .scope(d -> d.getPassphraseVariable())
                            .and()
                        .syntax_required()
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
                                && d.getAddAuthenticatorVariable() == null
                                && d.getAuthenticator() == null
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
