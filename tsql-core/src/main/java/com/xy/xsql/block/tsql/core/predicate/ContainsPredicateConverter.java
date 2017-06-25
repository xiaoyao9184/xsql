package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.Contains;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ContainsPredicateConverter
        implements BlockConverter<Contains> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Contains> builder =
            new ReferenceBlockBuilder<Void,Contains>()
                    .overall("CONTAINS")
                    .sub_keyword(Keywords.CONTAINS)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list()
                        .sub()
                            .required()
                            .czse(d -> d.getColumnName() != null, "column_name")
                                .data(Contains::getColumnName)
                                .and()
                            .czse(d -> d.getColumnList() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("column_list")
                                    .data(Contains::getColumnList)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .czse(Contains::isUseAllColumn, "*")
                                .and()
                            .czse(d -> d.getPropertyName() != null)
                                .sub_keyword(Keywords.Key.PROPERTY)
                                .sub_keyword(Other.GROUP_START)
                                .sub()
                                    .list()
                                    .sub("column_name")
                                        .data(Contains::getColumnName)
                                        .required()
                                        .and()
                                    .sub("'property_name'")
                                        .data(Contains::getPropertyName)
                                        .and()
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .sub("'<contains_search_condition>'")
                            .data(Contains::getContainsSearchCondition)
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
    public Block convert(Contains contains) {
        return builder
                .data(contains)
                .build();
    }
}
