package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.LoginProperty;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class LoginPropertyConverter
        implements ModelMetaBlockConverter<LoginProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,LoginProperty>()
                    .overall("LOGINPROPERTY")
                    .sub_keyword(Function.Keywords.LOGINPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'login_name'")
                        .scope(d-> d.getLoginName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'property_name'")
                        .scope(d-> d.getPropertyName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
