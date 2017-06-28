package com.xy.xsql.tsql.core.clause.hints;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.variable.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * QueryHintBuilder
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
        target.setType(type);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumberRows(Integer numberRows){
        target.setNumberRows(numberRows);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withPercent(Integer percent){
        target.setPercent(percent);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumberOfProcessors(Integer numberOfProcessors){
        target.setNumberOfProcessors(numberOfProcessors);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withNumber(Integer number){
        target.setNumber(number);
        return this;
    }

    public OptimizeForBuilder<QueryHintBuilder<ParentBuilder>> withOptimizeFor(){
        return new OptimizeForBuilder<QueryHintBuilder<ParentBuilder>>
                (initNew(QueryHint.OptimizeFor::new,
                target::getOptimizeFor,
                target::setOptimizeFor))
            .in(this);
    }

    public QueryHintBuilder<ParentBuilder> withOptimizeFor(QueryHint.OptimizeFor... optimizeFors){
        target.setOptimizeFor(Arrays.asList(optimizeFors));
        return this;
    }

    @Deprecated
    public QueryHintBuilder<ParentBuilder> withHintName(String hintName){
        initAdd(new StringConstant(hintName),
                target::getHintNameList,
                target::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(String... hintNames){
        if(CheckUtil.isNullOrEmpty(hintNames)){
            return this;
        }
        initAdd(Arrays.stream(hintNames)
                .map(n -> new StringConstant(n).withQuote())
                .collect(Collectors.toList()),
                target::getHintNameList,
                target::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withXmlPlan(String xmlPlan){
        target.setXmlPlan(new StringConstant(xmlPlan));
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withExposedObjectName(String exposedObjectName){
        target.setExposedObjectName(exposedObjectName);
        return this;
    }

    @Deprecated
    public QueryHintBuilder<ParentBuilder> withHintName(TableHint tableHint){
        initAdd(tableHint,
                target::getTableHintList,
                target::setTableHintList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withHintName(TableHint... tableHints){
        if(CheckUtil.isNullOrEmpty(tableHints)){
            return this;
        }
        initAdd(Arrays.asList(tableHints),
                target::getTableHintList,
                target::setTableHintList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withDelimiter(){
        target.setUseDelimiter(true);
        return this;
    }


    /**
     * OptimizeForBuilder
     * @param <ParentBuilder>
     */
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
            optimizeFor.setVariableName(new LocalVariable(variableName));
            optimizeFor.setUseUnknown(useUnknown);
            optimizeFor.setLiteralConstant(new StringConstant(literalConstant).withQuote());
            return optimizeFor;
        }

    }




    /*
    Quick build
     */

    /**
     * Quick build
     * @return
     */
    public static QueryHint HASH_GROUP(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_GROUP)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint ORDER_GROUP(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.ORDER_GROUP)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint CONCAT_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.CONCAT_UNION)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint HASH_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_UNION)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MERGE_UNION(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_UNION)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint LOOP_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.LOOP_JOIN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MERGE_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MERGE_JOIN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint HASH_JOIN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.HASH_JOIN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint EXPAND_VIEWS(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.EXPAND_VIEWS)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint FAST(Integer numberRows){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FAST)
                .withNumberRows(numberRows)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint FORCE_ORDER(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FORCE_ORDER)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint FORCE_EXTERNALPUSHDOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.FORCE_EXTERNALPUSHDOWN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint DISABLE_EXTERNALPUSHDOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.DISABLE_EXTERNALPUSHDOWN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint KEEP_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.KEEP_PLAN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint KEEPFIXED_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.KEEPFIXED_PLAN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MAX_GRANT_PERCENT(Integer percent){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAX_GRANT_PERCENT)
                .withPercent(percent)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MIN_GRANT_PERCENT(Integer percent){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MIN_GRANT_PERCENT)
                .withPercent(percent)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MAXDOP(Integer numberOfProcessors){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAXDOP)
                .withNumberOfProcessors(numberOfProcessors)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint MAXRECURSION(Integer number){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.MAXRECURSION)
                .withNumber(number)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint NO_PERFORMANCE_SPOOL(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.NO_PERFORMANCE_SPOOL)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint OPTIMIZE_FOR(QueryHint.OptimizeFor... optimizeFors){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.OPTIMIZE_FOR)
                .withOptimizeFor(optimizeFors)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint OPTIMIZE_FOR_UNKNOWN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.OPTIMIZE_FOR_UNKNOWN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint PARAMETERIZATION_SIMPLE(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.PARAMETERIZATION_SIMPLE)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint PARAMETERIZATION_FORCED(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.PARAMETERIZATION_FORCED)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint RECOMPILE(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.RECOMPILE)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint ROBUST_PLAN(){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.ROBUST_PLAN)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint USE_HINT(String... hintName){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.USE_HINT)
                .withHintName(hintName)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint USE_PLAN(String xml_plan){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.USE_PLAN)
                .withXmlPlan(xml_plan)
                .build();
    }

    /**
     * Quick build
     * @return
     */
    public static QueryHint TABLE_HINT(String exposedObjectName,TableHint... tableHints){
        return new QueryHintBuilder<Void>()
                .withType(QueryHint.Type.TABLE_HINT)
                .withExposedObjectName(exposedObjectName)
                .withHintName(tableHints)
                .build();
    }
}
