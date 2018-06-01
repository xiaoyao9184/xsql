package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Binary_Checksum;
import com.xy.xsql.tsql.model.functions.system.Checksum;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ChecksumConverter
        implements ModelMetaBlockConverter<Checksum> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Checksum>()
                    .overall("CHECKSUM")
                    .sub_keyword(Function.Keywords.CHECKSUM)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse_keyword(d -> d.getExpressionList() == null, Other.ASTERISK)
                        .czse(d -> d.getExpressionList() != null,"expression")
                            .list()
                            .scope(d -> d.getExpressionList())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
