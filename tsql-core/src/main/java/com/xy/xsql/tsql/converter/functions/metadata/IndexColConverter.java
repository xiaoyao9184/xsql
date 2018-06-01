package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.MultipartNamesConverter;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.FullTextServiceProperty;
import com.xy.xsql.tsql.model.functions.metadata.Index_Col;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IndexColConverter
        implements ModelMetaBlockConverter<Index_Col> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Index_Col>()
                    .overall("INDEX_COL")
                    .sub_keyword(Function.Keywords.INDEX_COL)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .ref(MultipartNamesConverter.TableNameConverter.meta)
                        .scope(d -> d.getTableName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("index_id")
                        .scope(d -> d.getIndexId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("key_id")
                        .scope(d -> d.getKeyId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
