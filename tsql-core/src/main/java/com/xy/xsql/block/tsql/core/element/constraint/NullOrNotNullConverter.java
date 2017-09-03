package com.xy.xsql.block.tsql.core.element.constraint;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.constraint.NullOrNotNull;
import com.xy.xsql.tsql.model.element.constraint.PrimaryUnique;

/**
 * Created by xiaoyao9184 on 2017/9/3.
 */
public class NullOrNotNullConverter
        implements ModelMetaBlockConverter<NullOrNotNull> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,NullOrNotNull>()
                    .czse_keyword(NullOrNotNull::isUseNull,Keywords.NULL)
                    .czse(d -> !d.isUseNull())
                        .sub_keyword(Keywords.NOT)
                        .sub_keyword(Keywords.NULL)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
