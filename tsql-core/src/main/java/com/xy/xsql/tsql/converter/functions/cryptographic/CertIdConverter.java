package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.CertProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CertIdConverter
        implements ModelMetaBlockConverter<Cert_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Cert_Id>()
                    .overall("Cert_ID")
                    .sub_keyword(Function.Keywords.Cert_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'cert_name'")
                        .scope(d -> d.getCertName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
