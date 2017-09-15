package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.core.ClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.UnknownExpression;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.In;
import com.xy.xsql.tsql.model.predicate.Like;
import com.xy.xsql.tsql.model.predicate.Predicate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.jdbc.PlaceholderExpressionFactory.placeholder;

public class Select<T> implements QueryChain<T> {
    private Class<T> cls;
    private final Optional<Join<?,?>> join;
    private final ProxyObjectMethodRecording<T> recorder;

    //lazy buildClassTable
    private List<WhereCondition<T>> whereConditions = new ArrayList<>();
    private ClassTableMetaBuilder builder;


    public Select(Class<T> cls) {
        this.cls = cls;
        this.join = Optional.empty();
        this.recorder = ProxyObjectMethodRecording.create(cls);
    }

    public Select(Class<T> cls, Join join) {
        this.cls = cls;
        this.join = Optional.of(join);
        this.recorder = ProxyObjectMethodRecording.create(cls);
    }


    public static <T> Select<T> from(Class<T> cls) {
        return new Select<>(cls);
    }


    public <U> RelationshipJoinSpecifier<T,U> join(JoinTables<T,U> relationship) {
        return new RelationshipJoin<>(this, relationship);
    }

    public <U> Join<T,U> join(Class<U> table) {
        return new Join<T,U>(this, table);
    }

    public <U> SelectComparison<T,U> and(Function<T,U> getter) {
        return where(getter);
    }

    public <U> SelectComparison<T,U> where(Function<T,U> getter) {
        WhereCondition<T> whereCondition = new WhereCondition<>(getter);
        whereConditions.add(whereCondition);
        return new SelectComparison<T, U>() {
            public Select<T> equalTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Select.this.column(fieldGetter);
                    return PredicateBuildFunction.equalTo(columnName,pValue);
                });
                return Select.this;
            }

            public Select<T> notEqualTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Select.this.column(fieldGetter);
                    return PredicateBuildFunction.notEqualTo(columnName,pValue);
                });
                return Select.this;
            }

            public Select<T> like(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Select.this.column(fieldGetter);
                    return PredicateBuildFunction.like(columnName,pValue);
                });
                return Select.this;
            }
        };
    }




    public String toSql() {
        com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification query = buildQuery();
        return BlockManager.INSTANCE.print(query);
//        return "SELECT * FROM " + fromClause() + buildWhere().map(sql -> " WHERE " + sql).orElse("");
    }

    public PlaceholderJSql toJSql(){
        Map.Entry<com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification,List<Object>> queryWithJSql = buildQueryWithJSql();
        String sql = BlockManager.INSTANCE.print(queryWithJSql.getKey());
        List<Object> args = queryWithJSql.getValue();

        return new PlaceholderJSql()
                .withSql(sql)
                .withArgs(args);
    }


    private ClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new ClassTableMetaBuilder()
                    .build(this.cls);
        }
        return builder;
    }

    private com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification buildQuery(){
        com.xy.xsql.tsql.model.clause.select.Select.SelectItem item = new com.xy.xsql.tsql.model.clause.select.Select.SelectItem();
        item.setUseAll(true);

        From from = new From();
        from.setTableSourceList(Collections.singletonList(fromClause()));

        Where where = buildWhere()
                .orElse(null);

        com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification query = new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification();
        query.setSelectList(Collections.singletonList(item));
        query.setFrom(from);
        query.setWhere(where);

        return query;
    }

    private Optional<Where> buildWhere() {
        Stream<Predicate> sp = join
                .map(Join::wherePredicates)
                .orElse(Stream.empty());

        sp = Stream.concat(sp,
                this.wherePredicates());
        List<Predicate> wherePredicates = sp.collect(Collectors.toList());

        return TSqlConversions.where(wherePredicates);
    }

    private Map.Entry<com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification,List<Object>> buildQueryWithJSql(){
        com.xy.xsql.tsql.model.clause.select.Select.SelectItem item = new com.xy.xsql.tsql.model.clause.select.Select.SelectItem();
        item.setUseAll(true);

        From from = new From();
        from.setTableSourceList(Collections.singletonList(fromClause()));

        Optional<Map.Entry<Where,List<Object>>> whereWithArgs = buildWhereWithJSql();
        Where where = whereWithArgs.map(Map.Entry::getKey).orElse(null);

        com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification query = new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification();
        query.setSelectList(Collections.singletonList(item));
        query.setFrom(from);
        query.setWhere(where);

        return new AbstractMap.SimpleEntry<>(query,whereWithArgs.map(Map.Entry::getValue).orElse(Collections.emptyList()));
    }

    private Optional<Map.Entry<Where,List<Object>>> buildWhereWithJSql() {
        Stream<Map.Entry<Predicate,Object>> predicateObjectStream = join
                .map(j -> {
                    Stream<Map.Entry<Predicate,Object>> s1 = j.wherePredicatesWithJSql();
                    Stream<Map.Entry<Predicate,Object>> s2 = this.wherePredicatesWithJSql();
                    return Stream.concat(s1,s2);
                })
                .orElse(this.wherePredicatesWithJSql());

        Map<Predicate,Object> predicateObjectMap = predicateObjectStream
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (u, v) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", u));
                        },
                        LinkedHashMap::new));
        Optional<Where> optionalWhere = TSqlConversions.where(predicateObjectMap.keySet());

        return optionalWhere
                .map(where -> new AbstractMap.SimpleEntry<>(where, new ArrayList<>(predicateObjectMap.values())));
    }






    @Override
    public ProxyObjectMethodRecording<T> recorder(Function<T, ?> p1) {
        p1.apply(recorder.getObject());
        return recorder;
    }

    @Override
    public ColumnName column(Function<T, ?> p1) {
        p1.apply(recorder.getObject());
        return buildClassTable().getColumnName(recorder.getMethod());
    }

    @Override
    public From.TableSource fromClause() {
        return join
                .map(Join::fromClause)
                .orElseGet(() -> {
                    From.BaseTable table = new From.BaseTable();
                    table.setTableName(buildClassTable().getTableName());
                    return table;
                });
    }

    @Override
    public Stream<Predicate> wherePredicates() {
        return whereConditions.stream()
                .map(WhereCondition::buildPredicate);
    }

    @Override
    public Stream<Object> whereValues() {
        return whereConditions.stream()
                .map(WhereCondition::getValue);
    }

    @Override
    public Stream<Map.Entry<Predicate,Object>> wherePredicatesWithJSql(){
        return whereConditions.stream()
                .map(whereCondition -> {
                    Object value = whereCondition.getValue();
                    Predicate predicate = whereCondition.getPredicateFunction().apply(
                            whereCondition.getFieldGetter(),
                            placeholder());
                    return new AbstractMap.SimpleEntry<>(
                            predicate,
                            value);
                });
    }




//    public int setPlaceholders(PreparedStatement statement) throws SQLException {
//        int start = join.map(j -> unchecked(() -> j.setPlaceholders(statement))).orElse(0);
//        for (int i = 0; i < whereFieldNames.size(); i++) {
//            Conventions.JdbcSetter setter = Conventions.getSetter(whereFieldNames.get(i).value.getClass());
//            setter.apply(statement, start + i + 1, whereFieldNames.get(i).value);
//        }
//        return whereFieldNames.size() + start;
//    }

    public interface SelectComparison<T,U> {
        Select<T> equalTo(U value);
        Select<T> notEqualTo(U value);
        Select<T> like(U value);
    }

//
//    public interface ThreeFunction<A1, A2, A3, R> {
//
//        /**
//         * Applies this function to the given arguments.
//         *
//         * @param a1 the first function argument
//         * @param a2 the second function argument
//         * @param a3 the three function argument
//         * @return the function result
//         */
//        R apply(A1 a1, A2 a2, A3 a3);
//    }
//
//    public static <T> Predicate equalTo(Function<Function<T,?>,ColumnName> columnFunction, Function<T,?> fieldGetter, Object value){
//        ColumnName columnName = columnFunction.apply(fieldGetter);
//
//        Comparison comparison = new Comparison();
//        comparison.setExpression(columnName);
//        comparison.setOperator(Operators.EQUAL);
//        comparison.setOperatorExpression(TSqlConversions.expression(value));
//
//        return comparison;
//    }
//
//    public static <T> Predicate notEqualTo(Function<Function<T,?>,ColumnName> columnFunction, Function<T,?> fieldGetter, Object value){
//        ColumnName columnName = columnFunction.apply(fieldGetter);
//
//        Comparison comparison = new Comparison();
//        comparison.setExpression(columnName);
//        comparison.setOperator(Operators.NOT_EQUAL_NOT_ISO);
//        comparison.setOperatorExpression(TSqlConversions.expression(value));
//
//        return comparison;
//    }
//
//    public static <T> Predicate like(Function<Function<T,?>,ColumnName> columnFunction, Function<T,?> fieldGetter, Object value){
//        ColumnName columnName = columnFunction.apply(fieldGetter);
//
//        Like like = new Like();
//        like.setExpression(columnName);
//        like.setLikeExpression(new StringConstant(value.toString()).withQuote());
//
//        return like;
//    }

    @FunctionalInterface
    public interface PredicateBuildFunction {

        /**
         * Applies this function to the given arguments
         * @param columnName columnName
         * @param value value
         * @return Predicate
         */
        Predicate apply(com.xy.xsql.tsql.model.element.ColumnName columnName, Object value);

        static <T> Predicate equalTo(ColumnName columnName, Object value){
            Comparison comparison = new Comparison();
            comparison.setExpression(columnName);
            comparison.setOperator(Operators.EQUAL);
            comparison.setOperatorExpression(TSqlConversions.expression(value));
            return comparison;
        }

        static <T> Predicate notEqualTo(ColumnName columnName, Object value){
            Comparison comparison = new Comparison();
            comparison.setExpression(columnName);
            comparison.setOperator(Operators.NOT_EQUAL_NOT_ISO);
            comparison.setOperatorExpression(TSqlConversions.expression(value));
            return comparison;
        }

        static <T> Predicate like(ColumnName columnName, Object value){
            Like like = new Like();
            like.setExpression(columnName);
            if(value instanceof UnknownExpression){
                like.setLikeExpression(new StringConstant(value.toString()));
            }else{
                like.setLikeExpression(new StringConstant(value.toString()).withQuote());
            }
            return like;
        }

        static <T> Predicate in(ColumnName columnName, Object... values){
            In like = new In();
            like.setExpression(columnName);

            List<Expression> expressionList = Stream.of(values)
                    .map(TSqlConversions::expression)
                    .collect(Collectors.toList());
            like.setExpressionList(expressionList);
            return like;
        }
    }

}
