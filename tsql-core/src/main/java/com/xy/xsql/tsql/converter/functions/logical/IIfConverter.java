package com.xy.xsql.tsql.converter.functions.logical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.logical.Choose;
import com.xy.xsql.tsql.model.functions.logical.IIf;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IIfConverter
        implements ModelMetaBlockConverter<IIf> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IIf>()
                    .overall("IIF")
                    .sub_keyword(Function.Keywords.IIF)
                    .sub_keyword(Other.GROUP_START)
                    .sub("boolean_expression")
                        .scope(d -> d.getBooleanExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("true_value")
                        .scope(d -> d.getTrueValue())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("false_value")
                        .scope(d -> d.getFalseValue())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
