package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.conversion.Parse;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ParseConverter
        implements ModelMetaBlockConverter<Parse> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Parse>()
                    .overall("PARSE")
                    .sub_keyword(Function.Keywords.PARSE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string_value")
                        .scope(d -> d.getStringValue())
                        .and()
                    .sub_keyword(Keywords.AS)
                    .sub("data_type")
                        .scope(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional(d -> d.getCulture() == null)
                        .sub_keyword(Keywords.Key.USING)
                        .sub("culture")
                            .scope(d -> d.getCulture())
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
