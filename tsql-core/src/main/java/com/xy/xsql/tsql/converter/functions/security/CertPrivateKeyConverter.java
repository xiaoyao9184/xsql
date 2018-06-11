package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.CertPrivateKey;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class CertPrivateKeyConverter
        implements ModelMetaBlockConverter<CertPrivateKey> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CertPrivateKey>()
                    .overall("CERTPRIVATEKEY")
                    .sub_keyword(Function.Keywords.CERTPRIVATEKEY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("cert_ID")
                        .scope(d -> d.getCertId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("' encryption_password '")
                        .scope(d -> d.getEncryptionPassword())
                        .and()
                    .sub()
                        .optional(d -> d.getDecryptionPassword() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("' decryption_password '")
                            .scope(d -> d.getCertId())
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
