package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.Contains;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ContainsPredicateConverter
        implements ModelMetaBlockConverter<Contains> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Contains>()
                    .overall("CONTAINS")
                    .sub_keyword(Keywords.CONTAINS)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .sub()
                            .style_required()
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
                                    .sub("column_name")
                                        .data(Contains::getColumnName)
                                        .style_required()
                                        .and()
                                    .sub_keyword(Other.DELIMITER)
                                    .sub("'property_name'")
                                        .data(Contains::getPropertyName)
                                        .and()
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'<contains_search_condition>'")
                            .data(Contains::getContainsSearchCondition)
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
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
