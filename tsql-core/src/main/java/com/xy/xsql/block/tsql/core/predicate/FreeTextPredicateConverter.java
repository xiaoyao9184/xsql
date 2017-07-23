package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.FreeText;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FreeTextPredicateConverter
        implements ModelMetaBlockConverter<FreeText> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,FreeText>()
                    .overall("FREETEXT")
                    .sub_keyword(Keywords.FREETEXT)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .sub()
                            .syntax_required()
                            .czse(d -> d.getColumnName() != null, "column_name")
                                .scope(FreeText::getColumnName)
                                .and()
                            .czse(d -> d.getColumnList() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("column_list")
                                    .scope(FreeText::getColumnList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .czse(FreeText::isUseAllColumn, "*")
                                .and()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'freetext_string'")
                            .scope(FreeText::getFreetextString)
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub()
                            .optional(d -> true)
                            .sub_keyword(Keywords.Key.LANGUAGE)
                            .sub("language_term")
                                //TODO support language_term
                                .scope(null)
                                .and()
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
