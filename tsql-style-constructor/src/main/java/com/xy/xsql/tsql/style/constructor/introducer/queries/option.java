package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_OPTION;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$query_hint;

import java.util.Arrays;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface option {

    static b_OPTION OPTION(b_OPTION.b$query_option... query_options){
        return new b_OPTION(){
            {
                Arrays.asList(query_options)
                        .forEach(query_option ->  withItem(query_option.build()));
            }
        };
    }


    static b_OPTION.b$query_option LABEL(String label_name){
        return new b_OPTION.b$query_option(label_name);
    }
    static b_OPTION.b$query_option LABEL(StringConstant label_name){
        return new b_OPTION.b$query_option(label_name);
    }
    static b_OPTION.b$query_option HASH_GROUP(){
        return new b_OPTION.b$query_option(b$query_hint.$HashGroup());
    }
    static b_OPTION.b$query_option ORDER_GROUP(){
        return new b_OPTION.b$query_option(b$query_hint.$OrderGroup());
    }

    static b_OPTION.b$query_option CONCAT_UNION(){
        return new b_OPTION.b$query_option(b$query_hint.$ConcatUnion());
    }
    static b_OPTION.b$query_option HASH_UNION(){
        return new b_OPTION.b$query_option(b$query_hint.$HashUnion());
    }
    static b_OPTION.b$query_option MERGE_UNION(){
        return new b_OPTION.b$query_option(b$query_hint.$MergeUnion());
    }

    static b_OPTION.b$query_option LOOP_JOIN(){
        return new b_OPTION.b$query_option(b$query_hint.$LoopJoin());
    }
    static b_OPTION.b$query_option MERGE_JOIN(){
        return new b_OPTION.b$query_option(b$query_hint.$MergeJoin());
    }
    static b_OPTION.b$query_option HASH_JOIN(){
        return new b_OPTION.b$query_option(b$query_hint.$HashJoin());
    }


    static b_OPTION.b$query_option EXPAND_VIEWS(){
        return new b_OPTION.b$query_option(b$query_hint.$ExpandViews());
    }
    static b_OPTION.b$query_option FAST(Integer number_rows){
        return new b_OPTION.b$query_option(b$query_hint.$Fast(number_rows));
    }
    static b_OPTION.b$query_option FORCE_ORDER(){
        return new b_OPTION.b$query_option(b$query_hint.$ForceOrder());
    }
    static b_OPTION.b$query_option FORCE_EXTERNALPUSHDOWN(){
        return new b_OPTION.b$query_option(b$query_hint.$ForceExternalpushdown());
    }
    static b_OPTION.b$query_option DISABLE_EXTERNALPUSHDOWN(){
        return new b_OPTION.b$query_option(b$query_hint.$DisableExternalpushdown());
    }
    static b_OPTION.b$query_option IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX(){
        return new b_OPTION.b$query_option(b$query_hint.$IgnoreNonclusteredColumnstoreIndex());
    }
    static b_OPTION.b$query_option KEEP_PLAN(){
        return new b_OPTION.b$query_option(b$query_hint.$KeepPlan());
    }
    static b_OPTION.b$query_option KEEPFIXED_PLAN(){
        return new b_OPTION.b$query_option(b$query_hint.$KeepfixedPlan());
    }
    static b_OPTION.b$query_option MAX_GRANT_PERCENT(Integer percent){
        return new b_OPTION.b$query_option(b$query_hint.$MaxGrantPercent(percent));
    }
    static b_OPTION.b$query_option MIN_GRANT_PERCENT(Integer percent){
        return new b_OPTION.b$query_option(b$query_hint.$MinGrantPercent(percent));
    }
    static b_OPTION.b$query_option MAXDOP(Integer number_of_processors){
        return new b_OPTION.b$query_option(b$query_hint.$Maxdop(number_of_processors));
    }
    static b_OPTION.b$query_option MAXRECURSION(Integer number){
        return new b_OPTION.b$query_option(b$query_hint.$Maxrecursion(number));
    }
    static b_OPTION.b$query_option NO_PERFORMANCE_SPOOL(){
        return new b_OPTION.b$query_option(b$query_hint.$NoPerformanceSpool());
    }
    static b_OPTION.b$query_option OPTIMIZE_FOR(String variableName, String literalConstant){
        return new b_OPTION.b$query_option(b$query_hint.$OptimizeFor(variableName, literalConstant));
    }
    static b_OPTION.b$query_option OPTIMIZE_FOR(String variableName, b$query_hint.b_OPTIMIZE_FOR.k_UNKNOWN unknown){
        return new b_OPTION.b$query_option(b$query_hint.$OptimizeFor(variableName));
    }
    static b_OPTION.b$query_option OPTIMIZE_FOR(b$query_hint.b_OPTIMIZE_FOR $){
        return new b_OPTION.b$query_option(b$query_hint.$OptimizeFor($.build()));
    }
    static b_OPTION.b$query_option OPTIMIZE_FOR_UNKNOWN(){
        return new b_OPTION.b$query_option(b$query_hint.$OptimizeForUnknown());
    }
    static b_OPTION.b$query_option PARAMETERIZATION_SIMPLE(){
        return new b_OPTION.b$query_option(b$query_hint.$ParameterizationSimple());
    }
    static b_OPTION.b$query_option PARAMETERIZATION_FORCED(){
        return new b_OPTION.b$query_option(b$query_hint.$ParameterizationForced());
    }
    static b_OPTION.b$query_option RECOMPILE(){
        return new b_OPTION.b$query_option(b$query_hint.$Recompile());
    }
    static b_OPTION.b$query_option ROBUST_PLAN(){
        return new b_OPTION.b$query_option(b$query_hint.$RobustPlan());
    }
    static b_OPTION.b$query_option USE_HINT(String... hintName){
        return new b_OPTION.b$query_option(b$query_hint.$UseHint(hintName));
    }
    static b_OPTION.b$query_option USE_PLAN(String xml_plan){
        return new b_OPTION.b$query_option(b$query_hint.$UsePlan(xml_plan));
    }
    static b_OPTION.b$query_option TABLE_HINT(String exposedObjectName, TableHint... tableHints){
        return new b_OPTION.b$query_option(b$query_hint.$TableHint(exposedObjectName, tableHints));
    }


    static b$query_hint.b_OPTIMIZE_FOR.k_UNKNOWN UNKNOWN(){
        return null;
    }

    static b$query_hint.b_OPTIMIZE_FOR $(String variableName, String literalConstant){
        return new b$query_hint.b_OPTIMIZE_FOR(){
            {
                withItem()
                        .withVariableName(variableName)
                        .withLiteralConstant(literalConstant);
            }
        };
    }
    static b$query_hint.b_OPTIMIZE_FOR $(String variableName, b$query_hint.b_OPTIMIZE_FOR.k_UNKNOWN unknown){
        return new b$query_hint.b_OPTIMIZE_FOR(){
            {
                withItem()
                        .withVariableName(variableName)
                        .withUnknown();
            }
        };
    }

}
