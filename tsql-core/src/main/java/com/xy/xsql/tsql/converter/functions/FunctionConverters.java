package com.xy.xsql.tsql.converter.functions;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.configuration.$$DbTs;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public interface FunctionConverters {

    // @formatter:off
    BlockMeta metaInternal =
            new BlockMetaBuilder<Void,Function.InternalFunction>()
                    .overall("Internal function")
                    .sub("name")
                        .scope(d -> d.keyword().toString())
                        .and()
                    .build();
    // @formatter:on



    static BlockMeta onlyName(String name){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,Function.InternalFunction>()
                        .overall(name)
                        .sub(name)
                            .scope(d -> name)
                            .and()
                        .build();
        // @formatter:on
    }

    static BlockMeta onlyName(Function.Keywords keyword){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,Function.InternalFunction>()
                        .overall(keyword.toString())
                        .sub_keyword(keyword)
                        .build();
        // @formatter:on
    }

    static BlockMeta noParam(Function.Keywords keyword){
        // @formatter:off
        return
                new BlockMetaBuilder<Void,Function.InternalFunction>()
                        .overall(keyword.toString())
                        .sub_keyword(keyword)
                        .sub_keyword(Other.GROUP_START)
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on
    }

}
