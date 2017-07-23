package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.meta.BlockMetaBuilder;
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
                            .syntax_required()
                            .czse(d -> d.getColumnName() != null, "column_name")
                                .scope(Contains::getColumnName)
                                .and()
                            .czse(d -> d.getColumnList() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("column_list")
                                    .scope(Contains::getColumnList)
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
                                        .scope(Contains::getColumnName)
                                        .syntax_required()
                                        .and()
                                    .sub_keyword(Other.DELIMITER)
                                    .sub("'property_name'")
                                        .scope(Contains::getPropertyName)
                                        .and()
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .sub_keyword(Other.DELIMITER)
                        .sub("'<contains_search_condition>'")
                            .scope(Contains::getContainsSearchCondition)
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
