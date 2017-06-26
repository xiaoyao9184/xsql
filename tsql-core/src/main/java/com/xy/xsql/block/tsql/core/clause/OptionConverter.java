package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
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
        implements BlockConverter<Option> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Option> builder =
            new ReferenceBlockBuilder<Void,Option>()
                    .overall("OPTION Clause")
                    .sub_keyword(Keywords.OPTION)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .description("<query_hint> [ ,...n ]")
                        .list()
                        .sub("query_hint")
                            .ref(QueryHintConverter.class)
                            .and()
                        .data(Option::getQueryOption)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Option option) {
        return builder
                .data(option)
                .build();
    }


    public static class OptionQueryOptionConverter
            implements BlockConverter<Option.QueryOption> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Option.QueryOption> builder =
                new ReferenceBlockBuilder<Void,Option.QueryOption>()
                        .overall("query_option")
                        .czse_meta(d -> d instanceof Option.LabelQueryOption, OptionLabelQueryOptionConverter.meta())
                        .czse(d -> d instanceof QueryHint, "query_hint")
                            .ref(QueryHintConverter.class)
                            .data(d -> d)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Option.QueryOption queryOption) {
            return builder
                    .data(queryOption)
                    .build();
        }
    }


    public static class OptionLabelQueryOptionConverter
            implements BlockConverter<Option.LabelQueryOption> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Option.LabelQueryOption> builder =
                new ReferenceBlockBuilder<Void,Option.LabelQueryOption>()
                        .sub_keyword(Keywords.Key.LABEL)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("label_name")
                            .data(Option.LabelQueryOption::getLabelName)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(Option.LabelQueryOption labelQueryOption) {
            return builder
                    .data(labelQueryOption)
                    .build();
        }
    }


}
