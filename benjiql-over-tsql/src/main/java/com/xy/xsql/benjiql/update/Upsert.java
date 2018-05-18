package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.core.ClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.query.Select;
import com.xy.xsql.benjiql.query.WhereCondition;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.Where;
import com.xy.xsql.tsql.model.datatype.Null;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.jdbc.PlaceholderExpressionFactory.placeholder;

public abstract class Upsert<T> {

    private final T value;
    private final ProxyObjectMethodRecording<T> recorder;

    private List<UpdateSet<T>> setConditions = new ArrayList<>();
    private List<WhereCondition<T>> whereConditions = new ArrayList<>();
    private ClassTableMetaBuilder builder;

    public static class Insert<T> extends Upsert<T> {
        public Insert(T value) {
            super(value);
        }

        @Override
        public String toSql() {
            com.xy.xsql.tsql.model.statements.Insert insert = buildInsert();
            return BlockManager.INSTANCE.print(insert);
        }

        @Override
        public PlaceholderJSql toJSql() {
            Map.Entry<com.xy.xsql.tsql.model.statements.Insert,Stream<Object>> insertWithJSql = buildInsertWithJSql();
            String sql = BlockManager.INSTANCE.print(insertWithJSql.getKey());
            List<Object> args = insertWithJSql.getValue().collect(Collectors.toList());

            return new PlaceholderJSql()
                    .withSql(sql)
                    .withArgs(args);
        }

        private com.xy.xsql.tsql.model.statements.Insert buildInsert(){
            TableName tableName = super.buildClassTable().getTableName();

            List<ColumnName> columnNameList = super.setConditions.stream()
                    .map(s -> super.column(s.getFieldGetter()))
                    .collect(Collectors.toList());

//            List<List<Expression>> exceptionStream = super.setConditions.stream()
//                    .map(UpdateSet::getValue)
//                    .map(TSqlConversions::expression)
//                    .collect(ArrayList::new,
//                            (l,e) -> l.get(0).add(e),
//                            (l1,l2) -> l1.get(0).addAll(l2.get(0)));
//            TableValueConstructor tableValueConstructor = new TableValueConstructor();
//            tableValueConstructor.setRowValueExpressionListGroup(exceptionStream);

            TableValueConstructor tableValueConstructor = super.setConditions.stream()
                    .map(UpdateSet::getValue)
                    .map(TSqlConversions::expression)
                    .collect(this.buildTableValueConstructor());
//                    .collect(() -> {
//                                TableValueConstructor t = new TableValueConstructor();
//                                List<List<Expression>> list = new ArrayList<>();
//                                list.add(new ArrayList<>());
//                                t.setRowValueExpressionListGroup(list);
//                                return t;
//                            },
//                            (values,e) -> values.getRowValueExpressionListGroup().get(0).add(e),
//                            (values1,values2) -> values1.getRowValueExpressionListGroup().get(0).addAll(values2.getRowValueExpressionListGroup().get(0)));

            com.xy.xsql.tsql.model.statements.Insert insert = new com.xy.xsql.tsql.model.statements.Insert();
            insert.setTableName(tableName);
            insert.setColumns(columnNameList);
            insert.setValues(tableValueConstructor);
            return insert;
        }

        private Map.Entry<com.xy.xsql.tsql.model.statements.Insert,Stream<Object>> buildInsertWithJSql(){
            List<Object> args = new ArrayList<>();

            TableName tableName = super.buildClassTable().getTableName();

            List<ColumnName> columnNameList = super.setConditions.stream()
                    .map(s -> super.column(s.getFieldGetter()))
                    .collect(Collectors.toList());

            TableValueConstructor tableValueConstructor = super.setConditions.stream()
                    .map(UpdateSet::getValue)
                    .map(v -> {
                        args.add(v);
                        return TSqlConversions.placeholderExpression();
                    })
                    .collect(buildTableValueConstructor());
//                    .collect(() -> {
//                                TableValueConstructor t = new TableValueConstructor();
//                                List<List<Expression>> list = new ArrayList<>();
//                                list.add(new ArrayList<>());
//                                t.setRowValueExpressionListGroup(list);
//                                return t;
//                            },
//                            (values,e) -> values.getRowValueExpressionListGroup().get(0).add(e),
//                            (values1,values2) -> values1.getRowValueExpressionListGroup().get(0).addAll(values2.getRowValueExpressionListGroup().get(0)));

            com.xy.xsql.tsql.model.statements.Insert insert = new com.xy.xsql.tsql.model.statements.Insert();
            insert.setTableName(tableName);
            insert.setColumns(columnNameList);
            insert.setValues(tableValueConstructor);

            return new AbstractMap.SimpleEntry<>(
                    insert,
                    args.stream());
        }

        public static Collector<Expression,TableValueConstructor,TableValueConstructor> buildTableValueConstructor(){
            return Collector.<Expression,TableValueConstructor>of(() -> {
                        TableValueConstructor t = new TableValueConstructor();
                        List<List<Expression>> list = new ArrayList<>();
                        list.add(new ArrayList<>());
                        t.setRowValueExpressionListGroup(list);
                        return t;
                    },
                    (t,e) -> t.getRowValueExpressionListGroup().get(0).add(e),
                    BinaryOperator.maxBy((t1,t2) -> 0));
        }
    }

    public static class Update<T> extends Upsert<T> {
        public Update(T value) {
            super(value);
        }

        @Override
        public String toSql() {
            com.xy.xsql.tsql.model.queries.Update update = buildUpdate();
            return BlockManager.INSTANCE.print(update);
        }

        @Override
        public PlaceholderJSql toJSql() {
            Map.Entry<com.xy.xsql.tsql.model.queries.Update,Stream<Object>> insertWithJSql = buildUpdateWithJSql();
            String sql = BlockManager.INSTANCE.print(insertWithJSql.getKey());
            List<Object> args = insertWithJSql.getValue().collect(Collectors.toList());

            return new PlaceholderJSql()
                    .withSql(sql)
                    .withArgs(args);
        }

        private com.xy.xsql.tsql.model.queries.Update buildUpdate(){
            TableName tableName = super.buildClassTable().getTableName();

            List<com.xy.xsql.tsql.model.queries.Update.SetItem> setItemList = super.setConditions.stream()
                    .map(UpdateSet::buildSetItem)
                    .collect(Collectors.toList());

            Where where = super.buildWhere()
                    .orElse(null);

            com.xy.xsql.tsql.model.queries.Update update = new com.xy.xsql.tsql.model.queries.Update();
            update.setTableName(tableName);
            update.setSets(setItemList);
            update.setWhere(where);
            return update;
        }

        private Map.Entry<com.xy.xsql.tsql.model.queries.Update,Stream<Object>> buildUpdateWithJSql(){
            List<Object> values = new ArrayList<>();

            TableName tableName = super.buildClassTable().getTableName();

            List<com.xy.xsql.tsql.model.queries.Update.SetItem> setItemList = super.setConditions.stream()
                    .map(s -> {
                        values.add(s.getValue());
                        return s.buildSetItem(TSqlConversions.placeholderExpression());
                    })
                    .collect(Collectors.toList());

            Map.Entry<Optional<Where>,Stream<Object>> whereWithJSql = super.buildWhereWithJSql();
            values.addAll(whereWithJSql.getValue().collect(Collectors.toList()));

            com.xy.xsql.tsql.model.queries.Update update = new com.xy.xsql.tsql.model.queries.Update();
            update.setTableName(tableName);
            update.setSets(setItemList);
            update.setWhere(whereWithJSql.getKey().orElse(null));

            return new AbstractMap.SimpleEntry<>(
                    update,
                    values.stream());
        }

    }

    @SuppressWarnings("unchecked")
    public Upsert(T value) {
        this.value = value;
        this.recorder = ProxyObjectMethodRecording.create((Class<T>) value.getClass());
    }

    public static <T> Insert<T> insert(T value) {
        return new Insert<T>(value);
    }

    public static <T,U> InsertRelationship<T,U> insert(T leftValue, U rightValue) {
        return new InsertRelationship<T, U>(leftValue, rightValue);
    }

    public static <T> Update<T> update(T value) {
        return new Update<T>(value);
    }

    public <U extends Serializable> Upsert<T> value(Function<T,U> getter) {
        return value(getter,getter.apply(value));
    }

    public <U extends Serializable> Upsert<T> value(Function<T,U> getter,Object value) {
        UpdateSet<T> updateSet = new UpdateSet<>();
        updateSet.setFieldGetter(getter);
        updateSet.setValue(value);
        updateSet.setSetItemFunction((f,v) -> {
            ColumnName columnName = column(getter);
            columnName.setTable(null);
            Expression expression = TSqlConversions.expression(v);
            com.xy.xsql.tsql.model.queries.Update.ColumnAssignmentSet set = new com.xy.xsql.tsql.model.queries.Update.ColumnAssignmentSet();
            set.setColumnName(columnName);
            if(expression instanceof Null){
                set.setUseNull(true);
            }else{
                set.setExpression(expression);
            }
            return set;
        });
        setConditions.add(updateSet);
        return this;
    }



//    public void execute(Supplier<Connection> connectionFactory) throws SQLException {
//        try (Connection connection = connectionFactory.get()) {
//            PreparedStatement statement = connection.prepareStatement(toSql());
//            for (int i = 0; i < setFieldNames.size(); i++) {
//                Conventions.JdbcSetter setter = Conventions.getSetter(setFieldNames.get(i).value.getClass());
//                setter.apply(statement, i + 1, setFieldNames.get(i).value);
//            }
//            for (int i = 0; i < whereFieldNames.size(); i++) {
//                Conventions.JdbcSetter setter = Conventions.getSetter(whereFieldNames.get(i).value.getClass());
//                setter.apply(statement, i + 1 + setFieldNames.size(), whereFieldNames.get(i).value);
//            }
//            statement.executeUpdate();
//        }
//    }

    public <U> UpdateComparison<T,U> and(Function<T,U> getter) {
        return where(getter);
    }

    public <U> UpdateComparison<T,U> where(Function<T,U> getter) {
        WhereCondition<T> whereCondition = new WhereCondition<>(getter);
        whereConditions.add(whereCondition);
        return new UpdateComparison<T,U>(){
            public Upsert<T> equalTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Upsert.this.column(fieldGetter);
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.equalTo(columnName,pValue);
                });
                return Upsert.this;
            }
            public Upsert<T> notEqualTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Upsert.this.column(fieldGetter);
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.notEqualTo(columnName,pValue);
                });
                return Upsert.this;
            }
            public Upsert<T> like(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    ColumnName columnName = Upsert.this.column(fieldGetter);
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.like(columnName,pValue);
                });
                return Upsert.this;
            }
        };
    }


    public abstract String toSql();

    public abstract PlaceholderJSql toJSql();

    private ClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new ClassTableMetaBuilder()
                    .build(this.value.getClass());
        }
        return builder;
    }

    private Optional<Where> buildWhere() {
        return TSqlConversions.where(this.wherePredicates().collect(Collectors.toList()));
    }

    private Map.Entry<Optional<Where>,Stream<Object>> buildWhereWithJSql() {
        Map.Entry<Stream<Predicate>,Stream<Object>> kv = this.wherePredicatesWithJSql();

        Optional<Where> optionalWhere = TSqlConversions.where(kv.getKey()
                .collect(Collectors.toList()));

        return new AbstractMap.SimpleEntry<>(optionalWhere,kv.getValue());
    }


    public ColumnName column(Function<T, ?> p1) {
        p1.apply(recorder.getObject());
        return buildClassTable().getColumnName(recorder.getMethod());
    }

    public Stream<Predicate> wherePredicates() {
        return whereConditions.stream()
                .map(WhereCondition::buildPredicate);
    }

    public Map.Entry<Stream<Predicate>,Stream<Object>> wherePredicatesWithJSql(){
        List<Object> values = new ArrayList<>();
        Stream<Predicate> predicates = whereConditions.stream()
                .map(whereCondition -> {
                    Object value = whereCondition.getValue();
                    values.add(value);
                    value = placeholder();
                    return whereCondition.buildPredicate(value);
                });

        return new AbstractMap.SimpleEntry<>(
                predicates,
                values.stream());
    }



    public interface UpdateComparison<T,U> {
        Upsert<T> equalTo(U value);
        Upsert<T> notEqualTo(U value);
        Upsert<T> like(U value);
    }


    public static class UpdateSet<T> {
        private Function<T,?> fieldGetter;
        private Object value;
        private BiFunction<Function<T,?>, Object, com.xy.xsql.tsql.model.queries.Update.SetItem> setItemFunction;

        public Function<T, ?> getFieldGetter() {
            return fieldGetter;
        }

        public void setFieldGetter(Function<T, ?> fieldGetter) {
            this.fieldGetter = fieldGetter;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public BiFunction<Function<T, ?>, Object, com.xy.xsql.tsql.model.queries.Update.SetItem> getSetItemFunction() {
            return setItemFunction;
        }

        public void setSetItemFunction(BiFunction<Function<T, ?>, Object, com.xy.xsql.tsql.model.queries.Update.SetItem> setItemFunction) {
            this.setItemFunction = setItemFunction;
        }


        public com.xy.xsql.tsql.model.queries.Update.SetItem buildSetItem(){
            return setItemFunction.apply(fieldGetter,value);
        }

        public com.xy.xsql.tsql.model.queries.Update.SetItem buildSetItem(Object value){
            return setItemFunction.apply(fieldGetter,value);
        }
    }
}

