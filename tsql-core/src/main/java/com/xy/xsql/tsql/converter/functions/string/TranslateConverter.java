package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.SubString;
import com.xy.xsql.tsql.model.functions.string.Translate;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TranslateConverter
        implements ModelMetaBlockConverter<Translate> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Translate>()
                    .overall("TRANSLATE")
                    .sub_keyword(Function.Keywords.TRANSLATE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("inputString")
                        .scope(d -> d.getInputString())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("characters")
                        .scope(d -> d.getCharacters())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("translations")
                        .scope(d -> d.getTranslations())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
