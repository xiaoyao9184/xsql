package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByAsymKey;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SignByCertConverter
        implements ModelMetaBlockConverter<SignByCert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SignByCert>()
                    .overall("SignByCert")
                    .sub_keyword(Function.Keywords.SignByCert)
                    .sub_keyword(Other.GROUP_START)
                    .sub("certificate_ID")
                        .scope(d -> d.getCertificateId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("@cleartext")
                        .scope(d -> d.getCleartext())
                        .and()
                    .sub()
                        .optional(d -> d.getPassword() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'password'")
                            .scope(d -> d.getPassword())
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
