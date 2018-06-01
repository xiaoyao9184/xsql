package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByAsymKey;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DecryptByCertConverter
        implements ModelMetaBlockConverter<DecryptByCert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DecryptByCert>()
                    .overall("DecryptByCert")
                    .sub_keyword(Function.Keywords.DecryptByCert)
                    .sub_keyword(Other.GROUP_START)
                    .sub("certificate_ID")
                        .scope(d -> d.getCertificateId())
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
                        .optional(d -> d.getCertPassword() == null && d.getCertPasswordVariable() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .czse(d -> d.getCertPassword() != null, "'cert_password'")
                                .scope(d -> d.getCertPassword())
                                .and()
                            .czse(d -> d.getCiphertextVariable() != null, "@cert_password ")
                                .scope(d -> d.getCiphertextVariable())
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
