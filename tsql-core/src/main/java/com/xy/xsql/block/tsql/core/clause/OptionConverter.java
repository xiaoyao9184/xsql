package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.hints.QueryHintConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Option;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OptionConverter
        implements ModelMetaBlockConverter<Option> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Option>()
                    .overall("OPTION Clause")
                    .sub_keyword(Keywords.OPTION)
                    .sub_keyword(Other.GROUP_START)
                    .sub("query_hint")
                        .list()
                        .ref(QueryOptionConverter.class)
                        .scope(Option::getQueryOption)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class QueryOptionConverter
            implements ModelMetaBlockConverter<Option.QueryOption> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Option.QueryOption>()
                        .overall("query_option")
                        .czse_ref(d -> d instanceof Option.LabelQueryOption, LabelQueryOptionConverter.meta)
                        .czse(d -> d instanceof QueryHint, "query_hint")
                            .ref(QueryHintConverter.class)
                            .scope(d -> d)
                            .and()
                        .style_sub_line_delimiter()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class LabelQueryOptionConverter
            implements ModelMetaBlockConverter<Option.LabelQueryOption> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Option.LabelQueryOption>()
                        .sub_keyword(Keywords.Key.LABEL)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("label_name")
                            .scope(Option.LabelQueryOption::getLabelName)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


}
