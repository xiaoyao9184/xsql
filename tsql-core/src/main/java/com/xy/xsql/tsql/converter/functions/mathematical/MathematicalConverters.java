package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.MathematicalFunction;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public interface MathematicalConverters {

    static BlockMeta paramNumericExpression(Function.Keywords keyword){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,MathematicalFunction.NumericExpressionParam>()
                        .overall(keyword.toString())
                        .sub_keyword(keyword)
                        .sub_keyword(Other.GROUP_START)
                        .sub("numeric_expression")
                            .scope(d -> d.getNumericExpression())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }

    static BlockMeta paramFloatExpression(Function.Keywords keyword){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,MathematicalFunction.FloatExpressionParam>()
                        .overall(keyword.toString())
                        .sub_keyword(keyword)
                        .sub_keyword(Other.GROUP_START)
                        .sub("float_expression")
                            .scope(d -> d.getFloatExpression())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }

}
