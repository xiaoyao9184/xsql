package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Translate;
import com.xy.xsql.tsql.model.functions.string.Trim;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TrimConverter
        implements ModelMetaBlockConverter<Trim> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Trim>()
                    .overall("TRIM")
                    .sub_keyword(Function.Keywords.TRIM)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(d -> d.getCharacters() == null)
                        .sub("characters")
                            .scope(d -> d.getCharacters())
                            .and()
                        .sub_keyword(Keywords.FROM)
                        .and()
                    .sub("string")
                        .scope(d -> d.getString())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
