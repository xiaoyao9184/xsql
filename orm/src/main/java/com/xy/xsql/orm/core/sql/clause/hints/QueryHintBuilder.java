package com.xy.xsql.orm.core.sql.clause.hints;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.data.sql.clause.hints.QueryHint;
import com.xy.xsql.orm.data.sql.clause.hints.TableHint;
import com.xy.xsql.orm.data.sql.element.UnknownString;

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

    public QueryHintBuilder<ParentBuilder> withHintName(String hint_name){
        initAdd(new UnknownString(hint_name),
                tar::getHintNameList,
                tar::setHintNameList);
        return this;
    }

    public QueryHintBuilder<ParentBuilder> withXmlPlan(String xml_plan){
        tar.setXmlPlan(new UnknownString(xml_plan));
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
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.HASH_GROUP);
        return queryHint;
    }

    public static QueryHint ORDER_GROUP(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.ORDER_GROUP);
        return queryHint;
    }

    public static QueryHint CONCAT_UNION(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.CONCAT_UNION);
        return queryHint;
    }

    public static QueryHint HASH_UNION(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.HASH_UNION);
        return queryHint;
    }

    public static QueryHint MERGE_UNION(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MERGE_UNION);
        return queryHint;
    }

    public static QueryHint LOOP_JOIN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.LOOP_JOIN);
        return queryHint;
    }

    public static QueryHint MERGE_JOIN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MERGE_JOIN);
        return queryHint;
    }

    public static QueryHint HASH_JOIN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.HASH_JOIN);
        return queryHint;
    }


    public static QueryHint EXPAND_VIEWS(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.EXPAND_VIEWS);
        return queryHint;
    }

    public static QueryHint FAST(Integer numberRows){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.FAST);
        queryHint.setNumberRows(numberRows);
        return queryHint;
    }

    public static QueryHint FORCE_ORDER(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.FORCE_ORDER);
        return queryHint;
    }

    public static QueryHint FORCE_EXTERNALPUSHDOWN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.FORCE_EXTERNALPUSHDOWN);
        return queryHint;
    }

    public static QueryHint DISABLE_EXTERNALPUSHDOWN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.DISABLE_EXTERNALPUSHDOWN);
        return queryHint;
    }

    public static QueryHint IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.IGNORE_NONCLUSTERED_COLUMNSTORE_INDEX);
        return queryHint;
    }

    public static QueryHint KEEP_PLAN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.KEEP_PLAN);
        return queryHint;
    }

    public static QueryHint KEEPFIXED_PLAN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.KEEPFIXED_PLAN);
        return queryHint;
    }

    public static QueryHint MAX_GRANT_PERCENT(Integer percent){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MAX_GRANT_PERCENT);
        queryHint.setPercent(percent);
        return queryHint;
    }

    public static QueryHint MIN_GRANT_PERCENT(Integer percent){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MIN_GRANT_PERCENT);
        queryHint.setPercent(percent);
        return queryHint;
    }

    public static QueryHint MAXDOP(Integer number_of_processors){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MAXDOP);
        queryHint.setNumberOfProcessors(number_of_processors);
        return queryHint;
    }

    public static QueryHint MAXRECURSION(Integer number){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.MAXRECURSION);
        queryHint.setNumber(number);
        return queryHint;
    }

    public static QueryHint NO_PERFORMANCE_SPOOL(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.NO_PERFORMANCE_SPOOL);
        return queryHint;
    }

    public static QueryHint OPTIMIZE_FOR(QueryHint.OptimizeFor... optimizeFors){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.OPTIMIZE_FOR);
        queryHint.setOptimizeFor(Arrays.asList(optimizeFors));
        return queryHint;
    }

    public static QueryHint OPTIMIZE_FOR_UNKNOWN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.OPTIMIZE_FOR_UNKNOWN);
        return queryHint;
    }

    public static QueryHint PARAMETERIZATION_SIMPLE(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.PARAMETERIZATION_SIMPLE);
        return queryHint;
    }

    public static QueryHint PARAMETERIZATION_FORCED(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.PARAMETERIZATION_FORCED);
        return queryHint;
    }

    public static QueryHint RECOMPILE(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.RECOMPILE);
        return queryHint;
    }

    public static QueryHint ROBUST_PLAN(){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.ROBUST_PLAN);
        return queryHint;
    }

    public static QueryHint USE_HINT(String... hint_name){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.USE_HINT);
        queryHint.setHintNameList(
                Arrays.stream(hint_name)
                        .map(UnknownString::new)
                        .collect(Collectors.toList()));
        return queryHint;
    }

    public static QueryHint USE_PLAN(String xml_plan){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.USE_PLAN);
        queryHint.setXmlPlan(new UnknownString(xml_plan).withNQuote(true));
        return queryHint;
    }

    public static QueryHint TABLE_HINT(String exposedObjectName,TableHint... tableHints){
        QueryHint queryHint = new QueryHint();
        queryHint.setType(QueryHint.Type.TABLE_HINT);
        queryHint.setExposedObjectName(exposedObjectName);
        queryHint.setUseDelimiter(true);
        queryHint.setTableHintList(Arrays.asList(tableHints));
        return queryHint;
    }
}
