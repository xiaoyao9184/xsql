package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.IndexKey_Property;
import com.xy.xsql.tsql.model.functions.metadata.IndexProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IndexPropertyConverter
        implements ModelMetaBlockConverter<IndexProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IndexProperty>()
                    .overall("INDEXPROPERTY")
                    .sub_keyword(Function.Keywords.INDEXPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("object_ID")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("index_or_statistics_name")
                        .scope(d -> d.getIndexOrStatisticsName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("property")
                        .scope(d -> d.getProperty())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
