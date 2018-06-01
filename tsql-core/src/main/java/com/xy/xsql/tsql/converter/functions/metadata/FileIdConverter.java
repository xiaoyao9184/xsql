package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Db_Id;
import com.xy.xsql.tsql.model.functions.metadata.File_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FileIdConverter
        implements ModelMetaBlockConverter<File_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,File_Id>()
                    .overall("FILE_ID")
                    .sub_keyword(Function.Keywords.FILE_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("file_name")
                        .scope(d -> d.getFileName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
