package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.SymKeyProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class VerifySignedByCertConverter
        implements ModelMetaBlockConverter<VerifySignedByCert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,VerifySignedByCert>()
                    .overall("VerifySignedByCert")
                    .sub_keyword(Function.Keywords.VerifySignedByCert)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Cert_ID")
                        .scope(d -> d.getCertId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("signed_data")
                        .scope(d -> d.getSignedData())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("signed_data")
                        .scope(d -> d.getSignature())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
