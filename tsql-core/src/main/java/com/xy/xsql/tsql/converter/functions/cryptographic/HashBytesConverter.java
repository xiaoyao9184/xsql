package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByPassPhrase;
import com.xy.xsql.tsql.model.functions.cryptographic.HashBytes;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class HashBytesConverter
        implements ModelMetaBlockConverter<HashBytes> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,HashBytes>()
                    .overall("HASHBYTES")
                    .sub_keyword(Function.Keywords.HASHBYTES)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'<algorithm>'")
                        .scope(d -> d.getAlgorithm())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub()
                        .czse(d -> d.getInput() != null, "'input'")
                            .scope(d -> d.getInput())
                            .and()
                        .czse(d -> d.getInputVariable() != null, "@input")
                            .scope(d -> d.getInputVariable())
                            .and()
                        .syntax_required()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();

    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
