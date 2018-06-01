package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Seed;
import com.xy.xsql.tsql.model.functions.datatype.Identity;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IdentityConverter
        implements ModelMetaBlockConverter<Identity> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Identity>()
                    .overall("IDENTITY")
                    .sub_keyword(Function.Keywords.IDENTITY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("data_type")
                        .scope(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional(d -> d.getIncrement() == null && d.getSeed() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("seed")
                            .scope(d -> d.getSeed())
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("increment")
                            .scope(d -> d.getIncrement())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub_keyword(Keywords.AS)
                    .sub("column_name")
                        .scope(d -> d.getColumn_name())
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
