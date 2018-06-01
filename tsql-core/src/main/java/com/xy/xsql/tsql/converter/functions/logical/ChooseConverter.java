package com.xy.xsql.tsql.converter.functions.logical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.json.IsJson;
import com.xy.xsql.tsql.model.functions.logical.Choose;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ChooseConverter
        implements ModelMetaBlockConverter<Choose> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Choose>()
                    .overall("CHOOSE")
                    .sub_keyword(Function.Keywords.CHOOSE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("index")
                        .scope(d -> d.getIndex())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("val")
                        .list()
                        .scope(d -> d.getValList())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
