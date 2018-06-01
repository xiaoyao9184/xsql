package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKeyProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByCert;
import com.xy.xsql.tsql.model.functions.cryptographic.SymKeyProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SymKeyPropertyConverter
        implements ModelMetaBlockConverter<SymKeyProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SymKeyProperty>()
                    .overall("SYMKEYPROPERTY")
                    .sub_keyword(Function.Keywords.SYMKEYPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("Key_ID")
                        .scope(d -> d.getKeyId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> AsymKeyProperty.Properties.algorithm_desc.equals(d.getProperties()), "'algorithm_desc'")
                            .scope(d -> d.getProperties().toString())
                            .and()
                        .czse(d -> AsymKeyProperty.Properties.string_sid.equals(d.getProperties()), "'string_sid'")
                            .scope(d -> d.getProperties().toString())
                            .and()
                        .czse(d -> AsymKeyProperty.Properties.sid.equals(d.getProperties()), "'sid'")
                            .scope(d -> d.getProperties().toString())
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
