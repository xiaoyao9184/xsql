package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.CertEncoded;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class CertEncodedConverter
        implements ModelMetaBlockConverter<CertEncoded> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CertEncoded>()
                    .overall("CERTENCODED")
                    .sub_keyword(Function.Keywords.CERTENCODED)
                    .sub_keyword(Other.GROUP_START)
                    .sub("cert_id")
                        .scope(d -> d.getCertId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
