package com.xy.xsql.tsql.core.clause.hints;

import com.xy.xsql.core.builder.CodeTreeBuilder;
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

    public QueryHintBuilder<ParentBuilder> withNumberOfProcessors(Integer numberOfProcessors){
        tar.setNumberOfProcessors(numberOfProcessors);
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

    @Deprecated
    public QueryHintBuilder<ParentBuilder> withHintName(String hintName){
        initAdd(new StringConstant(hintName),
                tar::getHintNameList,
                tar::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(String... hintNames){
        initAdd(Arrays.stream(hintNames)
                .map(StringConstant::new)
                .collect(Collectors.toList()),
                tar::getHintNameList,
                tar::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withXmlPlan(String xmlPlan){
        tar.setXmlPlan(new StringConstant(xmlPlan));
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withExposedObjectName(String exposedObjectName){
        tar.setExposedObjectName(exposedObjectName);
        return this;
    }

    @Deprecated
    public QueryHintBuilder<ParentBuilder> withHintName(TableHint tableHint){
        initAdd(tableHint,
                tar::getTableHintList,
                tar::setTableHintList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(TableHint... tableHints){
        initAdd(Arrays.asList(tableHints),
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
                .withNumberRows(numberRows)
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
