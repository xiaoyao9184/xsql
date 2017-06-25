package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.FreeText;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FreeTextPredicateConverter
        implements BlockConverter<FreeText> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,FreeText> builder =
            new ReferenceBlockBuilder<Void,FreeText>()
                    .overall("FREETEXT")
                    .sub_keyword(Keywords.FREETEXT)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list()
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
                        .sub("'freetext_string'")
                            .data(FreeText::getFreetextString)
                            .and()
                        .sub()
                            .optional()
                            .sub_keyword(Keywords.Key.LANGUAGE)
                            .sub("language_term")
                                //TODO
                                .data(null)
                                .and()
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(FreeText freeText) {
        return builder
                .data(freeText)
                .build();
    }
}
