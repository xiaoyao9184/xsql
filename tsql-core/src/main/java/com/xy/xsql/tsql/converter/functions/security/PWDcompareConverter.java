package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.PWDcompare;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class PWDcompareConverter
        implements ModelMetaBlockConverter<PWDcompare> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,PWDcompare>()
                    .overall("PWDCOMPARE")
                    .sub_keyword(Function.Keywords.PWDCOMPARE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'clear_text_password'")
                        .scope(d-> d.getClearTextPassword())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("password_hash")
                        .scope(d -> d.getPasswordHash())
                        .and()
                    .sub()
                        .optional(d -> d.getVersion() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("version")
                            .scope(d -> d.getVersion())
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
