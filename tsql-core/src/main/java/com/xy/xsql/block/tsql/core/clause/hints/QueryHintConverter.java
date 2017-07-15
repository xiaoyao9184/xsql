package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class QueryHintConverter
        implements ModelMetaBlockConverter<QueryHint> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,QueryHint>()
                    .overall("query_hint")
                    .czse(d ->
                            QueryHint.Type.HASH_GROUP.equals(d.getType()) ||
                            QueryHint.Type.ORDER_GROUP.equals(d.getType())
                    )
                        .description("HASH/ORDER GROUP")
                        .sub()
                            .style_required()
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
                        .description("CONCAT/HASH/MERGE UNION")
                        .sub()
                            .style_required()
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
                        .description("LOOP/MERGE/HASH JOIN")
                        .sub()
                            .style_required()
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
                            .scope(QueryHint::getNumberRows)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.FORCE_ORDER.equals(d.getType()))
                        .sub_keyword(QueryHint.Type.FORCE_ORDER)
                        .and()
                    .czse(d ->
                            QueryHint.Type.FORCE_EXTERNALPUSHDOWN.equals(d.getType()) ||
                            QueryHint.Type.DISABLE_EXTERNALPUSHDOWN.equals(d.getType())
                    )
                        .description("FORCE/DISABLE EXTERNALPUSHDOWN")
                        .sub()
                            .style_required()
                            .czse(d -> QueryHint.Type.FORCE_EXTERNALPUSHDOWN.equals(d.getType()))
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
                            .scope(QueryHint::getPercent)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MIN_GRANT_PERCENT.equals(d.getType()))
                        .description("MIN_GRANT_PERCENT = percent")
                        .sub_keyword(QueryHint.Type.MIN_GRANT_PERCENT)
                        .sub_keyword(Assignment.ASSIGNMENT)
                        .sub("percent")
                            .scope(QueryHint::getPercent)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MAXDOP.equals(d.getType()))
                        .description("MAXDOP number_of_processors")
                        .sub_keyword(QueryHint.Type.MAXDOP)
                        .sub("number_of_processors")
                            .scope(QueryHint::getNumberOfProcessors)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.MAXRECURSION.equals(d.getType()))
                        .description("MAXRECURSION number")
                        .sub_keyword(QueryHint.Type.MAXRECURSION)
                        .sub("number")
                            .scope(QueryHint::getNumber)
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
                            .description("optimize for's attribute list")
                            .list()
                            .ref(OptimizeForConverter.meta)
                            .scope(QueryHint::getOptimizeFor)
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
                        .description("PARAMETERIZATION SIMPLE/FORCED")
                        .sub_keyword(Keywords.Key.PARAMETERIZATION)
                        .sub()
                            .style_required()
                            .czse(d -> QueryHint.Type.PARAMETERIZATION_SIMPLE.equals(d.getType()))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .czse(d -> QueryHint.Type.PARAMETERIZATION_FORCED.equals(d.getType()))
                                .keyword(Keywords.Key.SIMPLE)
                                .and()
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.RECOMPILE.equals(d.getType()))
                        .keyword(QueryHint.Type.RECOMPILE)
                        .and()
                    .czse(d -> QueryHint.Type.ROBUST_PLAN.equals(d.getType()))
                        .keyword(QueryHint.Type.ROBUST_PLAN)
                        .and()
                    .czse(d -> QueryHint.Type.USE_HINT.equals(d.getType()))
                        .description("USE HINT ( '<hint_name>' [ , ...n ] )")
                        .sub_keyword(QueryHint.Type.USE_HINT)
                        .sub_keyword(Other.GROUP_START)
                        .sub("'<hint_name>'")
                            .list()
                            .scope(QueryHint::getHintNameList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .czse(d -> QueryHint.Type.USE_PLAN.equals(d.getType()))
                        .description("USE PLAN N'xml_plan'")
                        .sub_keyword(QueryHint.Type.USE_PLAN)
                        .sub("N'xml_plan'")
                            .scope(QueryHint::getXmlPlan)
                            .and()
                        .and()
                    .czse(d -> QueryHint.Type.TABLE_HINT.equals(d.getType()))
                        .description("TABLE HINT ( exposed_object_name [ , <table_hint> [ [, ]...n ] ] )")
                        .sub_keyword(QueryHint.Type.TABLE_HINT)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .description("exposed_object_name and table_hint")
                            .sub("exposed_object_name")
                                .scope(QueryHint::getExposedObjectName)
                                .and()
                            .sub()
                                .description(", <table_hint> [ [, ]...n ]")
                                .optional(d -> d.getTableHintList() == null)
                                .sub_keyword(Other.DELIMITER)
                                .sub("table_hint")
                                    .list()
                                    .repeat()
                                    .ref(TableHintConverter.class)
                                    .scope(QueryHint::getTableHintList)
                                    .and()
                                .and()
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .style_sub_line_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class OptimizeForConverter
            implements ModelMetaBlockConverter<QueryHint.OptimizeFor> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,QueryHint.OptimizeFor>()
                        .description("optimize for's attribute item")
                        .sub("@variable_name")
                            .scope(QueryHint.OptimizeFor::getVariableName)
                            .and()
                        .sub()
                            .style_required()
                            .czse(QueryHint.OptimizeFor::isUseUnknown)
                                .keyword(Keywords.Key.UNKNOWN)
                                .and()
                            .czse(d -> !d.isUseUnknown())
                                .sub_keyword(Assignment.ASSIGNMENT)
                                .sub("literal_constant")
                                    .scope(QueryHint.OptimizeFor::getLiteralConstant)
                                    .and()
                                .and()
                            .and()
                    .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
