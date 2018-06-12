package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.FileGroup_Id;
import com.xy.xsql.tsql.model.functions.metadata.File_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FileGroupIdConverter
        implements ModelMetaBlockConverter<FileGroup_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FileGroup_Id>()
                    .overall("FILEGROUP_ID")
                    .sub_keyword(Function.Keywords.FILEGROUP_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'filegroup_name'")
                        .scope(d -> d.getFilegroupName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
