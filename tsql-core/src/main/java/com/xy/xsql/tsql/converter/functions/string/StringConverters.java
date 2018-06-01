package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.StringFunction;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public interface StringConverters {

    static BlockMeta paramCharacterExpression(Function.Keywords keyword){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,StringFunction.CharacterExpressionParam>()
                        .overall(keyword.toString())
                        .sub_keyword(keyword)
                        .sub_keyword(Other.GROUP_START)
                        .sub("character_expression")
                            .scope(d -> d.getCharacterExpression())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }


}
