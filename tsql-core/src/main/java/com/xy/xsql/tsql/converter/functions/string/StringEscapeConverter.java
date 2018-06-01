package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Replace;
import com.xy.xsql.tsql.model.functions.string.String_Escape;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StringEscapeConverter
        implements ModelMetaBlockConverter<String_Escape> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,String_Escape>()
                    .overall("STRING_ESCAPE")
                    .sub_keyword(Function.Keywords.STRING_ESCAPE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("text")
                        .scope(d -> d.getText())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("type")
                        .scope(d -> d.getType())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
