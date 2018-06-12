package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.MultipartNamesConverter;
import com.xy.xsql.tsql.converter.queries.select.OverConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Index_Col;
import com.xy.xsql.tsql.model.functions.metadata.NextValueFor;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class NextValueForConverter
        implements ModelMetaBlockConverter<NextValueFor> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,NextValueFor>()
                    .overall("NEXT VALUE FOR")
                    .sub_keyword(Function.Keywords.NEXT_VALUE_FOR)
                    .sub()
                        .ref(MultipartNamesConverter.metaSequenceName)
                        .scope(d -> d.getSequenceName())
                        .and()
                    .sub()
                        .optional(d -> d.getOver() == null)
                        .ref(OverConverter.class)
                        .scope(d -> d.getOver())
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
