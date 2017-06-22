package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class QueryHintConverter
        implements BlockConverter<QueryHint> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,QueryHint> builder =
            new ReferenceBlockBuilder<Void,QueryHint>()
                    .overall("query_hint")
                    .oneOf()
                        .description("{ HASH | ORDER } GROUP")
                        .sub()
                            .required()
                            .oneOf()
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .oneOf()
                                .keyword(Keywords.ORDER)
                                .and()
                            .and()
                        .sub_keyword(Keywords.GROUP)
                        .and()
                    .oneOf()
                        .description("{ CONCAT | HASH | MERGE } UNION")
                        .sub()
                            .required()
                            .oneOf()
                                .keyword(Keywords.Key.CONCAT)
                                .and()
                            .oneOf()
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .oneOf()
                                .keyword(Keywords.Key.MERGE)
                                .and()
                            .and()
                        .sub_keyword(Keywords.UNION)
                        .and()
                    .oneOf("{ LOOP | MERGE | HASH } JOIN")
                        .sub()
                            .required()
                            .oneOf()
                                .keyword(Keywords.Key.LOOP)
                                .and()
                            .oneOf()
                                .keyword(Keywords.Key.MERGE)
                                .and()
                            .oneOf()
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .and()
                    .oneOf()
                        .sub_keyword(QueryHint.Type.EXPAND_VIEWS)
                        .and()
                    .oneOf()
                        .description("FAST number_rows")
                        .sub_keyword(QueryHint.Type.FAST)
                        .sub("number_rows")
                            .data(QueryHint::getNumberRows)
                            .and()
                        .and()
                    .oneOf()
                        .sub_keyword(QueryHint.Type.FORCE_ORDER)
                        .and()
                    .oneOf("{ FORCE | DISABLE } EXTERNALPUSHDOWN")
                        .sub()
                            .required()
                            .oneOf()
                                .keyword(Keywords.Key.FORCE)
                                .and()
                            .oneOf()
                                .keyword(Keywords.Key.DISABLE)
                                .and()
                            .and()
                        .sub_keyword(Keywords.EXTERNALPUSHDOWN)
                        .and()
                    .oneOf()
                        .keyword(QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX)
                        .and()
                    .oneOf()
                        .keyword(QueryHint.Type.KEEP_PLAN)
                        .and()
                    .oneOf()
                        .keyword(QueryHint.Type.KEEPFIXED_PLAN)
                        .and()
                    .oneOf()
                        .description("MAX_GRANT_PERCENT = percent")
                        .sub_keyword(QueryHint.Type.MAX_GRANT_PERCENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("percent")
                            .data(QueryHint::getPercent)
                            .and()
                        .and()
                    .oneOf()
                        .description("MIN_GRANT_PERCENT = percent")
                        .sub_keyword(QueryHint.Type.MIN_GRANT_PERCENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("percent")
                            .data(QueryHint::getPercent)
                            .and()
                        .and()
                    .oneOf()
                        .description("MAXDOP number_of_processors")
                        .sub_keyword(QueryHint.Type.MAXDOP)
                        .sub("number_of_processors")
                            .data(QueryHint::getNumberOfProcessors)
                            .and()
                        .and()
                    .oneOf()
                        .description("MAXRECURSION number")
                        .sub_keyword(QueryHint.Type.MAXRECURSION)
                        .sub("number")
                            .data(QueryHint::getNumber)
                            .and()
                        .and()
                    .oneOf()
                        .sub_keyword(QueryHint.Type.NO_PERFORMANCE_SPOOL)
                        .and()
                    .oneOf()
                        .description("OPTIMIZE FOR ( @variable_name { UNKNOWN | = literal_constant } [ , ...n ] )")
                        .sub_keyword(QueryHint.Type.OPTIMIZE_FOR)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list(OptimizeForConverter.meta())
                            .data(QueryHint::getHintNameList)
                            .more()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .oneOf()
                        .description("OPTIMIZE FOR UNKNOWN")
                        .keyword(QueryHint.Type.OPTIMIZE_FOR_UNKNOWN)
                        .and()
                    .oneOf()
                        .description("PARAMETERIZATION { SIMPLE | FORCED }")
                        .sub_keyword(Keywords.Key.PARAMETERIZATION)
                        .sub()
                            .required()
                            .oneOf()
                                .filter(d -> d.getType().equals(QueryHint.Type.PARAMETERIZATION_SIMPLE))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .oneOf()
                                .filter(d -> d.getType().equals(QueryHint.Type.PARAMETERIZATION_FORCED))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .and()
                        .and()
                    .oneOf()
                        .keyword(QueryHint.Type.RECOMPILE)
                        .and()
                    .oneOf()
                        .keyword(QueryHint.Type.ROBUST_PLAN)
                        .and()
                    .oneOf()
                        .description("USE HINT ( '<hint_name>' [ , ...n ] )")
                        .sub_keyword(QueryHint.Type.USE_HINT)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .sub("'<hint_name>'")
                                .and()
                            .data(QueryHint::getHintNameList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .oneOf()
                        .description("USE PLAN N'xml_plan'")
                        .sub_keyword(QueryHint.Type.USE_PLAN)
                        .sub("N'xml_plan'")
                            .data(QueryHint::getXmlPlan)
                            .and()
                        .and()
                    .oneOf()
                        .description("TABLE HINT (exposed_object_name [ , [ [, ]...n ] ] )")
                        .sub_keyword(QueryHint.Type.TABLE_HINT)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .sub()
                                .sub("exposed_object_name")
                                    .and()
                                .sub()
                                    .description(", <table_hint> [ [, ]...n ]")
                                    .optional()
                                    .sub_keyword(Other.DELIMITER)
                                    .sub()
                                        .list()
                                        .repeat()
                                        .sub("<table_hint>")
                                            .and()
                                        .more()
                                        .and()
                                    .and()
                                .and()
                            .data(QueryHint::getTableHintList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(QueryHint queryHint) {
        return builder
                .data(queryHint)
                .build();
    }


    public static class OptimizeForConverter
            implements BlockConverter<QueryHint.OptimizeFor> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,QueryHint.OptimizeFor> builder =
                new ReferenceBlockBuilder<Void,QueryHint.OptimizeFor>()
                        .sub("@variable_name")
                            .data(d -> d.getVariableName())
                            .and()
                        .sub()
                            .required()
                            .oneOf()
                                .filter(d -> !d.isUseUnknown())
                                .keyword(Keywords.Key.UNKNOWN)
                                .and()
                            .oneOf()
                                .filter(d -> d.isUseUnknown())
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("literal_constant")
                                    .data(d -> d.getLiteralConstant())
                                    .and()
                                .and()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(QueryHint.OptimizeFor optimizeFor) {
            return builder
                    .data(optimizeFor)
                    .build();
        }
    }

}
