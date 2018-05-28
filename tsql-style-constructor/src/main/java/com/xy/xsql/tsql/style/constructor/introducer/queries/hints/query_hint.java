package com.xy.xsql.tsql.style.constructor.introducer.queries.hints;

import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.style.constructor.builder.queries.hints.b$query_hint;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface query_hint {

    static b$query_hint HASH_GROUP(){
        return new b$query_hint(){
            {
                this.target = $HashGroup();
            }
        };
    }
    static b$query_hint ORDER_GROUP(){
        return new b$query_hint(){
            {
                this.target = $OrderGroup();
            }
        };
    }

    static b$query_hint CONCAT_UNION(){
        return new b$query_hint(){
            {
                this.target = $ConcatUnion();
            }
        };
    }
    static b$query_hint HASH_UNION(){
        return new b$query_hint(){
            {
                this.target = $HashUnion();
            }
        };
    }
    static b$query_hint MERGE_UNION(){
        return new b$query_hint(){
            {
                this.target = $MergeUnion();
            }
        };
    }

    static b$query_hint LOOP_JOIN(){
        return new b$query_hint(){
            {
                this.target = $LoopJoin();
            }
        };
    }
    static b$query_hint MERGE_JOIN(){
        return new b$query_hint(){
            {
                this.target = $MergeJoin();
            }
        };
    }
    static b$query_hint HASH_JOIN(){
        return new b$query_hint(){
            {
                this.target = $HashJoin();
            }
        };
    }


    static b$query_hint EXPAND_VIEWS(){
        return new b$query_hint(){
            {
                this.target = $ExpandViews();
            }
        };
    }
    static b$query_hint FAST(Integer number_rows){
        return new b$query_hint(){
            {
                this.target = $Fast(number_rows);
            }
        };
    }
    static b$query_hint FORCE_ORDER(){
        return new b$query_hint(){
            {
                this.target = $ForceOrder();
            }
        };
    }
    static b$query_hint FORCE_EXTERNALPUSHDOWN(){
        return new b$query_hint(){
            {
                this.target = $ForceExternalpushdown();
            }
        };
    }
    static b$query_hint DISABLE_EXTERNALPUSHDOWN(){
        return new b$query_hint(){
            {
                this.target = $DisableExternalpushdown();
            }
        };
    }
    static b$query_hint IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX(){
        return new b$query_hint(){
            {
                this.target = $IgnoreNonclusteredColumnstoreIndex();
            }
        };
    }
    static b$query_hint KEEP_PLAN(){
        return new b$query_hint(){
            {
                this.target = $KeepPlan();
            }
        };
    }
    static b$query_hint KEEPFIXED_PLAN(){
        return new b$query_hint(){
            {
                this.target = $KeepfixedPlan();
            }
        };
    }
    static b$query_hint MAX_GRANT_PERCENT(Integer percent){
        return new b$query_hint(){
            {
                this.target = $MaxGrantPercent(percent);
            }
        };
    }
    static b$query_hint MIN_GRANT_PERCENT(Integer percent){
        return new b$query_hint(){
            {
                this.target = $MinGrantPercent(percent);
            }
        };
    }
    static b$query_hint MAXDOP(Integer number_of_processors){
        return new b$query_hint(){
            {
                this.target = $Maxdop(number_of_processors);
            }
        };
    }
    static b$query_hint MAXRECURSION(Integer number){
        return new b$query_hint(){
            {
                this.target = $Maxrecursion(number);
            }
        };
    }
    static b$query_hint NO_PERFORMANCE_SPOOL(){
        return new b$query_hint(){
            {
                this.target = $NoPerformanceSpool();
            }
        };
    }
    static b$query_hint OPTIMIZE_FOR(String variableName, String literalConstant){
        return new b$query_hint(){
            {
                this.target = $OptimizeFor(variableName, literalConstant);
            }
        };
    }
    static b$query_hint OPTIMIZE_FOR(String variableName, b$query_hint.b_OPTIMIZE_FOR.k_UNKNOWN unknown){
        return new b$query_hint(){
            {
                this.target = $OptimizeFor(variableName);
            }
        };
    }
    static b$query_hint OPTIMIZE_FOR(b$query_hint.b_OPTIMIZE_FOR $){
        return new b$query_hint(){
            {
                this.target = $OptimizeFor($.build());
            }
        };
    }
    static b$query_hint OPTIMIZE_FOR_UNKNOWN(){
        return new b$query_hint(){
            {
                this.target = $OptimizeForUnknown();
            }
        };
    }
    static b$query_hint PARAMETERIZATION_SIMPLE(){
        return new b$query_hint(){
            {
                this.target = $ParameterizationSimple();
            }
        };
    }
    static b$query_hint PARAMETERIZATION_FORCED(){
        return new b$query_hint(){
            {
                this.target = $ParameterizationForced();
            }
        };
    }
    static b$query_hint RECOMPILE(){
        return new b$query_hint(){
            {
                this.target = $Recompile();
            }
        };
    }
    static b$query_hint ROBUST_PLAN(){
        return new b$query_hint(){
            {
                this.target = $RobustPlan();
            }
        };
    }
    static b$query_hint USE_HINT(String... hintName){
        return new b$query_hint(){
            {
                this.target = $UseHint(hintName);
            }
        };
    }
    static b$query_hint USE_PLAN(String xml_plan){
        return new b$query_hint(){
            {
                this.target = $UsePlan(xml_plan);
            }
        };
    }
    static b$query_hint TABLE_HINT(String exposedObjectName, TableHint... tableHints){
        return new b$query_hint(){
            {
                this.target = $TableHint(exposedObjectName,tableHints);
            }
        };
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
