package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByCert;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByKey;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class EncryptByKeyConverter
        implements ModelMetaBlockConverter<EncryptByKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,EncryptByKey>()
                    .overall("EncryptByKey")
                    .sub_keyword(Function.Keywords.EncryptByKey)
                    .sub_keyword(Other.GROUP_START)
                    .sub("key_GUID")
                        .scope(d -> d.getKeyGUID())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> d.getCleartext() != null, "'cleartext'")
                            .scope(d -> d.getCleartext())
                            .and()
                        .czse(d -> d.getCleartextVariable() != null, "@cleartext")
                            .scope(d -> d.getCleartextVariable())
                            .and()
                        .syntax_required()
                        .and()
                    .sub()
                        .optional(d -> d.getAddAuthenticator() == null
                                && d.getAddAuthenticatorVariable() == null
                                && d.getAuthenticator() == null
                                && d.getAuthenticatorVariable() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getAddAuthenticator() != null, "add_authenticator")
                                .scope(d -> d.getAddAuthenticator())
                                .and()
                            .czse(d -> d.getAddAuthenticatorVariable() != null, "@add_authenticator ")
                                .scope(d -> d.getAddAuthenticatorVariable())
                                .and()
                            .syntax_required()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getAddAuthenticator() != null, "authenticator")
                                .scope(d -> d.getAddAuthenticator())
                                .and()
                            .czse(d -> d.getAddAuthenticatorVariable() != null, "@authenticator")
                                .scope(d -> d.getAddAuthenticatorVariable())
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
