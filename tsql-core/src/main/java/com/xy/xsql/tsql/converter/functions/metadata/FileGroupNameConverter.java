package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.FileGroup_Name;
import com.xy.xsql.tsql.model.functions.metadata.File_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FileGroupNameConverter
        implements ModelMetaBlockConverter<FileGroup_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FileGroup_Name>()
                    .overall("FILEGROUP_NAME")
                    .sub_keyword(Function.Keywords.FILEGROUP_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("filegroup_id")
                        .scope(d -> d.getFilegroupId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
