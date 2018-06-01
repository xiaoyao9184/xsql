package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.queries.select.OverConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.CheckSum_Agg;
import com.xy.xsql.tsql.model.functions.aggregate.Count;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CountConverter
        implements ModelMetaBlockConverter<Count> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Count>()
                    .overall("COUNT")
                    .sub_keyword(Function.Keywords.COUNT)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getExpression() != null)
                            .sub()
                                .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                                .czse_keyword(d -> d.isUseAll(), Keywords.ALL)
                                .czse_keyword(d -> d.isUseDistinct(), Keywords.DISTINCT)
                                .and()
                            .sub("expression")
                                .scope(d -> d.getExpression())
                                .and()
                            .and()
                        .czse(d -> d.isUseAllCount())
                            .sub_keyword(Other.ASTERISK)
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .optional(d -> d.getOver() == null)
                        .ref(OverConverter.meta)
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
