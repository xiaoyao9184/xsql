package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.CertProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.Crypt_Gen_Random;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CryptGenRandomConverter
        implements ModelMetaBlockConverter<Crypt_Gen_Random> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Crypt_Gen_Random>()
                    .overall("CRYPT_GEN_RANDOM")
                    .sub_keyword(Function.Keywords.CRYPT_GEN_RANDOM)
                    .sub_keyword(Other.GROUP_START)
                    .sub("length")
                        .scope(d -> d.getLength())
                        .and()
                    .sub()
                        .optional(d -> d.getSeed() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("seed")
                            .scope(d -> d.getSeed())
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
