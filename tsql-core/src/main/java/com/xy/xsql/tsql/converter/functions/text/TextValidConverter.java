package com.xy.xsql.tsql.converter.functions.text;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.text.TextPtr;
import com.xy.xsql.tsql.model.functions.text.TextValid;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TextValidConverter
        implements ModelMetaBlockConverter<TextValid> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TextValid>()
                    .overall("TEXTVALID")
                    .sub_keyword(Function.Keywords.TEXTVALID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'table.column'")
                        .scope(d -> d.getColumn())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("text_ptr")
                        .scope(d -> d.getTextPtr())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
