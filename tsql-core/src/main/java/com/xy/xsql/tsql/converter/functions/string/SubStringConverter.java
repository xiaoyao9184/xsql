package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Stuff;
import com.xy.xsql.tsql.model.functions.string.SubString;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SubStringConverter
        implements ModelMetaBlockConverter<SubString> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SubString>()
                    .overall("SUBSTRING")
                    .sub_keyword(Function.Keywords.SUBSTRING)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("start")
                        .scope(d -> d.getStart())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("length")
                        .scope(d -> d.getLength())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
