package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.PWDencrypt;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class PWDencryptConverter
        implements ModelMetaBlockConverter<PWDencrypt> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,PWDencrypt>()
                    .overall("PWDENCRYPT")
                    .sub_keyword(Function.Keywords.PWDENCRYPT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'password'")
                        .scope(d-> d.getPassword())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
