package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.Exists;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExistsPredicateConverter
        implements ModelMetaBlockConverter<Exists> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Exists>()
                    .overall("EXISTS")
                    .sub_keyword(Keywords.EXISTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("subquery")
                        .scope(Exists::getSubquery)
                        .format_indentation_right()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
