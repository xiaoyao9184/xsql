package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
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
        implements MetaContextBlockConverter<Option> {

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
    public MetaContextBlock convert(Option context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class QueryOptionConverter
            implements MetaContextBlockConverter<Option.QueryOption> {

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
        public MetaContextBlock convert(Option.QueryOption context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }


    public static class LabelQueryOptionConverter
            implements MetaContextBlockConverter<Option.LabelQueryOption> {

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
        public MetaContextBlock convert(Option.LabelQueryOption context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }


}
