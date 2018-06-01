package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ColumnProperty;
import com.xy.xsql.tsql.model.functions.metadata.FileGroupProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FileGroupPropertyConverter
        implements ModelMetaBlockConverter<FileGroupProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FileGroupProperty>()
                    .overall("FILEGROUPPROPERTY")
                    .sub_keyword(Function.Keywords.FILEGROUPPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("filegroup_name")
                        .scope(d -> d.getFilegroupName())
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
