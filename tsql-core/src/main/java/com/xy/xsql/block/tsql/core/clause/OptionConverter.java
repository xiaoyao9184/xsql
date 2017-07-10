package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
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
        implements ReferenceBlockConverter<Option> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Option> builder =
            new BlockMetaBuilder<Void,Option>()
                    .overall("OPTION Clause")
                    .sub_keyword(Keywords.OPTION)
                    .sub_keyword(Other.GROUP_START)
                    .sub("query_hint")
                        .list()
                        .ref(QueryOptionConverter.class)
                        .data(Option::getQueryOption)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Option option) {
        return builder
                .data(option)
                .build();
    }


    public static class QueryOptionConverter
            implements ReferenceBlockConverter<Option.QueryOption> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Option.QueryOption> builder =
                new BlockMetaBuilder<Void,Option.QueryOption>()
                        .overall("query_option")
                        .czse_meta(d -> d instanceof Option.LabelQueryOption, LabelQueryOptionConverter.meta())
                        .czse(d -> d instanceof QueryHint, "query_hint")
                            .ref(QueryHintConverter.class)
                            .data(d -> d)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Option.QueryOption queryOption) {
            return builder
                    .data(queryOption)
                    .build();
        }
    }


    public static class LabelQueryOptionConverter
            implements ReferenceBlockConverter<Option.LabelQueryOption> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Option.LabelQueryOption> builder =
                new BlockMetaBuilder<Void,Option.LabelQueryOption>()
                        .sub_keyword(Keywords.Key.LABEL)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("label_name")
                            .data(Option.LabelQueryOption::getLabelName)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Option.LabelQueryOption labelQueryOption) {
            return builder
                    .data(labelQueryOption)
                    .build();
        }
    }


}
