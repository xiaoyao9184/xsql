package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.HashBytes;
import com.xy.xsql.tsql.model.functions.cryptographic.Is_ObjectSigned;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsObjectSignedConverter
        implements ModelMetaBlockConverter<Is_ObjectSigned> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Is_ObjectSigned>()
                    .overall("IS_OBJECTSIGNED")
                    .sub_keyword(Function.Keywords.IS_OBJECTSIGNED)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'OBJECT'")
                        .scope(d -> "'OBJECT'")
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("@object_id")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("@class")
                        .scope(d -> d.getClazz())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("@thumbprint")
                        .scope(d -> d.getThumbprint())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
