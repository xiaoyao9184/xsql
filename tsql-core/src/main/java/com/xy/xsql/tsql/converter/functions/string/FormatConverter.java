package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Difference;
import com.xy.xsql.tsql.model.functions.string.Format;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FormatConverter
        implements ModelMetaBlockConverter<Format> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Format>()
                    .overall("FORMAT")
                    .sub_keyword(Function.Keywords.FORMAT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("value")
                        .scope(d -> d.getValue())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("format")
                        .scope(d -> d.getFormat())
                        .and()
                    .sub()
                        .optional(d -> d.getCulture() == null)
                        .sub_keyword(Other.DELIMITER)
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
