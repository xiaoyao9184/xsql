package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.FileProperty;
import com.xy.xsql.tsql.model.functions.metadata.FullTextCatalogProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FullTextCatalogPropertyConverter
        implements ModelMetaBlockConverter<FullTextCatalogProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FullTextCatalogProperty>()
                    .overall("FULLTEXTCATALOGPROPERTY")
                    .sub_keyword(Function.Keywords.FULLTEXTCATALOGPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'catalog_name'")
                        .scope(d -> d.getCatalogName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'property'")
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
