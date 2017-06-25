package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
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
                    .czse(d ->
                            QueryHint.Type.HASH_GROUP.equals(d.getType()) ||
                            QueryHint.Type.ORDER_GROUP.equals(d.getType())
                    )
                        .description("{ HASH | ORDER } GROUP")
                        .sub()
                            .required()
                            .czse(d -> QueryHint.Type.HASH_GROUP.equals(d.getType()))
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .czse(d -> QueryHint.Type.ORDER_GROUP.equals(d.getType()))
                                .keyword(Keywords.ORDER)
                                .and()
                            .and()
                        .sub_keyword(Keywords.GROUP)
                        .and()
                    .czse(d ->
                            QueryHint.Type.CONCAT_UNION.equals(d.getType()) ||
                            QueryHint.Type.HASH_UNION.equals(d.getType()) ||
                            QueryHint.Type.MERGE_UNION.equals(d.getType())
                    )
                        .description("{ CONCAT | HASH | MERGE } UNION")
                        .sub()
                            .required()
                            .czse(d -> QueryHint.Type.CONCAT_UNION.equals(d.getType()))
                                .keyword(Keywords.Key.CONCAT)
                                .and()
                            .czse(d -> QueryHint.Type.HASH_UNION.equals(d.getType()))
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .czse(d -> QueryHint.Type.MERGE_UNION.equals(d.getType()))
                                .keyword(Keywords.Key.MERGE)
                                .and()
                            .and()
                        .sub_keyword(Keywords.UNION)
                        .and()
                    .czse(d ->
                            QueryHint.Type.LOOP_JOIN.equals(d.getType()) ||
                            QueryHint.Type.MERGE_JOIN.equals(d.getType()) ||
                            QueryHint.Type.HASH_JOIN.equals(d.getType())
                    )
                        .description("{ LOOP | MERGE | HASH } JOIN")
                        .sub()
                            .required()
                            .czse(d -> QueryHint.Type.LOOP_JOIN.equals(d.getType()))
                                .keyword(Keywords.Key.LOOP)
                                .and()
                            .czse(d -> QueryHint.Type.MERGE_JOIN.equals(d.getType()))
                                .keyword(Keywords.Key.MERGE)
                                .and()
                            .czse(d -> QueryHint.Type.HASH_JOIN.equals(d.getType()))
                                .keyword(Keywords.Key.HASH)
                                .and()
                            .and()
                        .sub_keyword(Keywords.JOIN)
                        .and()
                    .czse(d -> QueryHint.Type.EXPAND_VIEWS.equals(d.getType()))
                        .sub_keyword(QueryHint.Type.EXPAND_VIEWS)
                        .and()
                    .czse(d -> QueryHint.Type.FAST.equals(d.getType()))
                        .description("FAST number_rows")
                        .sub_keyword(QueryHint.Type.FAST)
                        .sub("number_rows")
                            .data(QueryHint::getNumberRows)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.FORCE_ORDER.equals(d.getType()))
                        .sub_keyword(QueryHint.Type.FORCE_ORDER)
                        .and()
                    .czse(d ->
                            QueryHint.Type.DISABLE_EXTERNALPUSHDOWN.equals(d.getType()) ||
                            QueryHint.Type.DISABLE_EXTERNALPUSHDOWN.equals(d.getType())
                    )
                        .description("{ FORCE | DISABLE } EXTERNALPUSHDOWN")
                        .sub()
                            .required()
                            .czse(d -> QueryHint.Type.DISABLE_EXTERNALPUSHDOWN.equals(d.getType()))
                                .keyword(Keywords.Key.FORCE)
                                .and()
                            .czse(d -> QueryHint.Type.DISABLE_EXTERNALPUSHDOWN.equals(d.getType()))
                                .keyword(Keywords.Key.DISABLE)
                                .and()
                            .and()
                        .sub_keyword(Keywords.EXTERNALPUSHDOWN)
                        .and()
                    .czse(d -> QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX.equals(d.getType()))
                        .keyword(QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX)
                        .and()
                    .czse(d -> QueryHint.Type.KEEP_PLAN.equals(d.getType()))
                        .keyword(QueryHint.Type.KEEP_PLAN)
                        .and()
                    .czse(d -> QueryHint.Type.KEEPFIXED_PLAN.equals(d.getType()))
                        .keyword(QueryHint.Type.KEEPFIXED_PLAN)
                        .and()
                    .czse(d -> QueryHint.Type.MAX_GRANT_PERCENT.equals(d.getType()))
                        .description("MAX_GRANT_PERCENT = percent")
                        .sub_keyword(QueryHint.Type.MAX_GRANT_PERCENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("percent")
                            .data(QueryHint::getPercent)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MIN_GRANT_PERCENT.equals(d.getType()))
                        .description("MIN_GRANT_PERCENT = percent")
                        .sub_keyword(QueryHint.Type.MIN_GRANT_PERCENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("percent")
                            .data(QueryHint::getPercent)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MAXDOP.equals(d.getType()))
                        .description("MAXDOP number_of_processors")
                        .sub_keyword(QueryHint.Type.MAXDOP)
                        .sub("number_of_processors")
                            .data(QueryHint::getNumberOfProcessors)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MAXRECURSION.equals(d.getType()))
                        .description("MAXRECURSION number")
                        .sub_keyword(QueryHint.Type.MAXRECURSION)
                        .sub("number")
                            .data(QueryHint::getNumber)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.NO_PERFORMANCE_SPOOL.equals(d.getType()))
                        .sub_keyword(QueryHint.Type.NO_PERFORMANCE_SPOOL)
                        .and()
                    .czse(d -> QueryHint.Type.OPTIMIZE_FOR.equals(d.getType()))
                        .description("OPTIMIZE FOR ( @variable_name { UNKNOWN | = literal_constant } [ , ...n ] )")
                        .sub_keyword(QueryHint.Type.OPTIMIZE_FOR)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list(OptimizeForConverter.meta())
                            .more()
                            .data(QueryHint::getHintNameList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .czse(d -> QueryHint.Type.OPTIMIZE_FOR_UNKNOWN.equals(d.getType()))
                        .description("OPTIMIZE FOR UNKNOWN")
                        .keyword(QueryHint.Type.OPTIMIZE_FOR_UNKNOWN)
                        .and()
                    .czse(d ->
                            QueryHint.Type.PARAMETERIZATION_FORCED.equals(d.getType()) ||
                            QueryHint.Type.PARAMETERIZATION_SIMPLE.equals(d.getType())
                    )
                        .description("PARAMETERIZATION { SIMPLE | FORCED }")
                        .sub_keyword(Keywords.Key.PARAMETERIZATION)
                        .sub()
                            .required()
                            .czse(d -> QueryHint.Type.PARAMETERIZATION_SIMPLE.equals(d.getType()))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .czse(d -> QueryHint.Type.PARAMETERIZATION_FORCED.equals(d.getType()))
                                .filter(d -> d.getType().equals(QueryHint.Type.PARAMETERIZATION_FORCED))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MAXDOP.equals(d.getType()))
                        .keyword(QueryHint.Type.RECOMPILE)
                        .and()
                    .czse(d -> QueryHint.Type.ROBUST_PLAN.equals(d.getType()))
                        .keyword(QueryHint.Type.ROBUST_PLAN)
                        .and()
                    .czse(d -> QueryHint.Type.USE_HINT.equals(d.getType()))
                        .description("USE HINT ( '<hint_name>' [ , ...n ] )")
                        .sub_keyword(QueryHint.Type.USE_HINT)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list("'<hint_name>'")
                            .more()
                            .data(QueryHint::getHintNameList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .czse(d -> QueryHint.Type.USE_PLAN.equals(d.getType()))
                        .description("USE PLAN N'xml_plan'")
                        .sub_keyword(QueryHint.Type.USE_PLAN)
                        .sub("N'xml_plan'")
                            .data(QueryHint::getXmlPlan)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.TABLE_HINT.equals(d.getType()))
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
                                        .list("<table_hint>")
                                        .repeat()
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
                            .data(QueryHint.OptimizeFor::getVariableName)
                            .and()
                        .sub()
                            .required()
                            .czse(d -> !d.isUseUnknown())
                                .keyword(Keywords.Key.UNKNOWN)
                                .and()
                            .czse(QueryHint.OptimizeFor::isUseUnknown)
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("literal_constant")
                                    .data(QueryHint.OptimizeFor::getLiteralConstant)
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
