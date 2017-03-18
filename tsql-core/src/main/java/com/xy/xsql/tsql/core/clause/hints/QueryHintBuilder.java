package com.xy.xsql.tsql.core.clause.hints;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.datatype.StringConstant;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class QueryHintBuilder<ParentBuilder>
        extends CodeTreeBuilder<QueryHintBuilder<ParentBuilder>,ParentBuilder,QueryHint> {

    public QueryHintBuilder() {
        super(new QueryHint());
    }

    public QueryHintBuilder(QueryHint queryHint) {
        super(queryHint);
    }


    public QueryHintBuilder<ParentBuilder> withType(QueryHint.Type type){
        tar.setType(type);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumberRows(Integer numberRows){
        tar.setNumberRows(numberRows);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withPercent(Integer percent){
        tar.setPercent(percent);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumberOfProcessors(Integer number_of_processors){
        tar.setNumberOfProcessors(number_of_processors);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumber(Integer number){
        tar.setNumber(number);
        return this;
    }

    public OptimizeForBuilder<QueryHintBuilder<ParentBuilder>> withOptimizeFor(){
        return new OptimizeForBuilder<QueryHintBuilder<ParentBuilder>>
                (initNew(QueryHint.OptimizeFor::new,
                tar::getOptimizeFor,
                tar::setOptimizeFor))
            .in(this);
    }

    public QueryHintBuilder<ParentBuilder> withOptimizeFor(QueryHint.OptimizeFor... optimizeFors){
        tar.setOptimizeFor(Arrays.asList(optimizeFors));
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(String hint_name){
        initAdd(new StringConstant(hint_name),
                tar::getHintNameList,
                tar::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(String... hint_name){
        initAdd(Arrays.stream(hint_name)
                .map(StringConstant::new)
                .collect(Collectors.toList()),
                tar::getHintNameList,
                tar::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withXmlPlan(String xml_plan){
        tar.setXmlPlan(new StringConstant(xml_plan));
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withExposedObjectName(String exposedObjectName){
        tar.setExposedObjectName(exposedObjectName);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(TableHint table_hint){
        initAdd(table_hint,
                tar::getTableHintList,
                tar::setTableHintList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(TableHint... table_hint){
        initAdd(Arrays.asList(table_hint),
                tar::getTableHintList,
                tar::setTableHintList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withDelimiter(){
        tar.setUseDelimiter(true);
        return this;
    }


    public static class OptimizeForBuilder<ParentBuilder>
        extends CodeTreeBuilder<OptimizeForBuilder<ParentBuilder>,ParentBuilder,QueryHint.OptimizeFor> {

        public OptimizeForBuilder(QueryHint.OptimizeFor tar) {
            super(tar);
        }


        public static QueryHint.OptimizeFor OPTIMIZE_FOR_Item(
                String variableName,
                boolean useUnknown,
                String literalConstant){
            QueryHint.OptimizeFor optimizeFor = new  QueryHint.OptimizeFor();
            optimizeFor.setVariableName(variableName);
            optimizeFor.setUseUnknown(useUnknown);
            optimizeFor.setLiteralConstant(literalConstant);
            return optimizeFor;
        }

    }



    public static QueryHint HASH_GROUP(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_GROUP)
                .build();
    }

    public static QueryHint ORDER_GROUP(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.ORDER_GROUP)
                .build();
    }

    public static QueryHint CONCAT_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.CONCAT_UNION)
                .build();
    }

    public static QueryHint HASH_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_UNION)
                .build();
    }

    public static QueryHint MERGE_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_UNION)
                .build();
    }

    public static QueryHint LOOP_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.LOOP_JOIN)
                .build();
    }

    public static QueryHint MERGE_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_JOIN)
                .build();
    }

    public static QueryHint HASH_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_JOIN)
                .build();
    }


    public static QueryHint EXPAND_VIEWS(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.EXPAND_VIEWS)
                .build();
    }

    public static QueryHint FAST(Integer numberRows){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FAST)
                .withNumber(numberRows)
                .build();
    }

    public static QueryHint FORCE_ORDER(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FORCE_ORDER)
                .build();
    }

    public static QueryHint FORCE_EXTERNALPUSHDOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FORCE_EXTERNALPUSHDOWN)
                .build();
    }

    public static QueryHint DISABLE_EXTERNALPUSHDOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.DISABLE_EXTERNALPUSHDOWN)
                .build();
    }

    public static QueryHint IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX)
                .build();
    }

    public static QueryHint KEEP_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.KEEP_PLAN)
                .build();
    }

    public static QueryHint KEEPFIXED_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.KEEPFIXED_PLAN)
                .build();
    }

    public static QueryHint MAX_GRANT_PERCENT(Integer percent){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAX_GRANT_PERCENT)
                .withPercent(percent)
                .build();
    }

    public static QueryHint MIN_GRANT_PERCENT(Integer percent){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MIN_GRANT_PERCENT)
                .withPercent(percent)
                .build();
    }

    public static QueryHint MAXDOP(Integer numberOfProcessors){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAXDOP)
                .withNumberOfProcessors(numberOfProcessors)
                .build();
    }

    public static QueryHint MAXRECURSION(Integer number){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAXRECURSION)
                .withNumber(number)
                .build();
    }

    public static QueryHint NO_PERFORMANCE_SPOOL(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.NO_PERFORMANCE_SPOOL)
                .build();
    }

    public static QueryHint OPTIMIZE_FOR(QueryHint.OptimizeFor... optimizeFors){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.OPTIMIZE_FOR)
                .withOptimizeFor(optimizeFors)
                .build();
    }

    public static QueryHint OPTIMIZE_FOR_UNKNOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.OPTIMIZE_FOR_UNKNOWN)
                .build();
    }

    public static QueryHint PARAMETERIZATION_SIMPLE(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.PARAMETERIZATION_SIMPLE)
                .build();
    }

    public static QueryHint PARAMETERIZATION_FORCED(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.PARAMETERIZATION_FORCED)
                .build();
    }

    public static QueryHint RECOMPILE(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.RECOMPILE)
                .build();
    }

    public static QueryHint ROBUST_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.ROBUST_PLAN)
                .build();
    }

    public static QueryHint USE_HINT(String... hintName){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.USE_HINT)
                .withHintName(hintName)
                .build();
    }

    public static QueryHint USE_PLAN(String xml_plan){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.USE_PLAN)
                .withXmlPlan(xml_plan)
                .build();
    }

    public static QueryHint TABLE_HINT(String exposedObjectName,TableHint... tableHints){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.TABLE_HINT)
                .withExposedObjectName(exposedObjectName)
                .withHintName(tableHints)
                .build();
    }
}
