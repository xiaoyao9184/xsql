package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.MultipartNamesConverter;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.IndexKey_Property;
import com.xy.xsql.tsql.model.functions.metadata.Index_Col;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IndexKeyPropertyConverter
        implements ModelMetaBlockConverter<IndexKey_Property> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IndexKey_Property>()
                    .overall("INDEXKEY_PROPERTY")
                    .sub_keyword(Function.Keywords.INDEXKEY_PROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("object_ID")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("index_ID")
                        .scope(d -> d.getIndexId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("key_ID")
                        .scope(d -> d.getKeyId())
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
