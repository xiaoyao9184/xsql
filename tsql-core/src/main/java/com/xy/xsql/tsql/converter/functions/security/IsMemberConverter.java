package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Is_Member;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class IsMemberConverter
        implements ModelMetaBlockConverter<Is_Member> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Is_Member>()
                    .overall("IS_MEMBER")
                    .sub_keyword(Function.Keywords.IS_MEMBER)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getGroup() != null, "'group'")
                            .scope(d-> d.getGroup())
                            .and()
                        .czse(d -> d.getRole() != null, "'role'")
                            .scope(d-> d.getRole())
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
