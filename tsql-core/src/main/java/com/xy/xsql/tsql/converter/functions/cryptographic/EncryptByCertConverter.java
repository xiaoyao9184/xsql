package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByAsymKey;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByCert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class EncryptByCertConverter
        implements ModelMetaBlockConverter<EncryptByCert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,EncryptByCert>()
                    .overall("EncryptByCert")
                    .sub_keyword(Function.Keywords.EncryptByCert)
                    .sub("certificate_ID")
                        .scope(d -> d.getCertificateId())
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
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
