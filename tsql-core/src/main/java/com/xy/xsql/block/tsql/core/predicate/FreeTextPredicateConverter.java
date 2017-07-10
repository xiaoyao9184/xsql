package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.FreeText;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FreeTextPredicateConverter
        implements MetaContextBlockConverter<FreeText> {

    // @formatter:off
    private static BlockMetaBuilder<Void,FreeText> builder =
            new BlockMetaBuilder<Void,FreeText>()
                    .overall("FREETEXT")
                    .sub_keyword(Keywords.FREETEXT)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .sub()
                            .required()
                            .czse(d -> d.getColumnName() != null, "column_name")
                                .data(FreeText::getColumnName)
                                .and()
                            .czse(d -> d.getColumnList() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("column_list")
                                    .data(FreeText::getColumnList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .czse(FreeText::isUseAllColumn, "*")
                                .and()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'freetext_string'")
                            .data(FreeText::getFreetextString)
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .optional(d -> true)
                            .sub_keyword(Keywords.Key.LANGUAGE)
                            .sub("language_term")
                                //TODO support language_term
                                .data(null)
                                .and()
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(FreeText context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
