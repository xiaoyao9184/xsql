package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.Avg;
import com.xy.xsql.tsql.model.functions.aggregate.CheckSum_Agg;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CheckSumAggConverter
        implements ModelMetaBlockConverter<CheckSum_Agg> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CheckSum_Agg>()
                    .overall("CHECKSUM_AGG")
                    .sub_keyword(Function.Keywords.CHECKSUM_AGG)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                        .czse_keyword(d -> d.isUseAll(), Keywords.ALL)
                        .czse_keyword(d -> d.isUseDistinct(), Keywords.DISTINCT)
                        .and()
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
