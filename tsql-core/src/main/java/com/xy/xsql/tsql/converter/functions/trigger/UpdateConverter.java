package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.text.TextPtr;
import com.xy.xsql.tsql.model.functions.trigger.Update;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class UpdateConverter
        implements ModelMetaBlockConverter<Update> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Update>()
                    .overall("UPDATE")
                    .sub_keyword(Function.Keywords.UPDATE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("column")
                        .scope(d -> d.getColumn())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
