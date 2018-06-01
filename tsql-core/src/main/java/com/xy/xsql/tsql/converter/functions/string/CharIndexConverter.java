package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Char;
import com.xy.xsql.tsql.model.functions.string.CharIndex;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CharIndexConverter
        implements ModelMetaBlockConverter<CharIndex> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CharIndex>()
                    .overall("CHARINDEX")
                    .sub_keyword(Function.Keywords.CHARINDEX)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expressionToFind")
                        .scope(d -> d.getExpressionToFind())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("expressionToSearch")
                        .scope(d -> d.getExpressionToSearch())
                        .and()
                    .sub()
                        .optional(d -> d.getStartLocation() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("start_location")
                            .scope(d -> d.getStartLocation())
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
