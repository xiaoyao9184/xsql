package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.core.ClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.benjiql.query.Select;
import com.xy.xsql.benjiql.query.WhereCondition;
import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.predicate.Predicate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.jdbc.PlaceholderExpressionFactory.placeholder;

public class Delete<T> {
    private final Class<T> cls;
    private final ProxyObjectMethodRecording<T> recorder;

    //lazy buildClassTable
    private final List<WhereCondition<T>> whereConditions = new ArrayList<>();
    private ClassTableMetaBuilder builder;

    public Delete(Class<T> cls) {
        this.cls = cls;
        this.recorder = ProxyObjectMethodRecording.create(cls);
    }



    public static <T> Delete<T> delete(Class<T> cls) {
        return new Delete<T>(cls);
    }

    public static <T,U> DeleteFromRelationship<T,U> delete(JoinTables<T, U> join) {
        return new DeleteFromRelationship<T,U>(join.leftTable, join.rightTable);
    }


    public <U> DeleteComparison<T,U> and(Function<T,U> getter) {
        return where(getter);
    }

    public <U> DeleteComparison<T,U> where(Function<T,U> getter) {
        WhereCondition<T> whereCondition = new WhereCondition<>(getter);
        whereConditions.add(whereCondition);
        return new Delete.DeleteComparison<T, U>() {
            public Delete<T> equalTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    fieldGetter.apply(recorder.getObject());
                    ColumnName columnName = buildClassTable().getColumnName(recorder.getMethod());
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.equalTo(columnName,pValue);
                });
                return Delete.this;
            }

            public Delete<T> notEqualTo(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    fieldGetter.apply(recorder.getObject());
                    ColumnName columnName = buildClassTable().getColumnName(recorder.getMethod());
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.notEqualTo(columnName,pValue);
                });
                return Delete.this;
            }

            public Delete<T> like(U value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((fieldGetter,pValue) -> {
                    fieldGetter.apply(recorder.getObject());
                    ColumnName columnName = buildClassTable().getColumnName(recorder.getMethod());
                    columnName.setTable(null);
                    return Select.PredicateBuildFunction.like(columnName,pValue);
                });
                return Delete.this;
            }
        };
    }


    public String toSql() {
        com.xy.xsql.tsql.model.statement.dml.Delete delete = buildDelete();
        return BlockManager.INSTANCE.print(delete);
    }

    public PlaceholderJSql toJSql(){
        Map.Entry<com.xy.xsql.tsql.model.statement.dml.Delete,Stream<Object>> queryWithJSql = buildDeleteWithJSql();
        com.xy.xsql.tsql.model.statement.dml.Delete delete = queryWithJSql.getKey();
        List<Object> args = queryWithJSql.getValue().collect(Collectors.toList());

        return new PlaceholderJSql()
                .withSql(BlockManager.INSTANCE.print(delete))
                .withArgs(args);
    }


    private ClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new ClassTableMetaBuilder()
                    .build(this.cls);
        }
        return builder;
    }

    private com.xy.xsql.tsql.model.statement.dml.Delete buildDelete(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(cls.getSimpleName()));

        Where where = buildWhere()
                .orElse(null);

        com.xy.xsql.tsql.model.statement.dml.Delete delete = new com.xy.xsql.tsql.model.statement.dml.Delete();
        delete.setTableName(tableName);
        delete.setWhere(where);

        return delete;
    }

    public Optional<Where> buildWhere() {
        return TSqlConversions.where(this.wherePredicates().collect(Collectors.toList()));
    }

    private Map.Entry<com.xy.xsql.tsql.model.statement.dml.Delete,Stream<Object>> buildDeleteWithJSql(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(cls.getSimpleName()));

        Map.Entry<Optional<Where>,Stream<Object>> kv = buildWhereWithJSql();

        com.xy.xsql.tsql.model.statement.dml.Delete delete = new com.xy.xsql.tsql.model.statement.dml.Delete();
        delete.setTableName(tableName);
        delete.setWhere(kv.getKey().orElse(null));

        return new AbstractMap.SimpleEntry<>(delete,kv.getValue());
    }

    public Map.Entry<Optional<Where>,Stream<Object>> buildWhereWithJSql() {
        Map.Entry<Stream<Predicate>,Stream<Object>> kv = this.wherePredicatesWithJSql();

        Optional<Where> optionalWhere = TSqlConversions.where(kv.getKey()
                .collect(Collectors.toList()));

        return new AbstractMap.SimpleEntry<>(optionalWhere,kv.getValue());
    }


//    @Override
    public Stream<Predicate> wherePredicates() {
        return whereConditions.stream()
                .map(whereCondition -> {
                    Predicate predicate = whereCondition.getPredicateFunction().apply(
                            whereCondition.getFieldGetter(),
                            whereCondition.getValue());
                    return predicate;
                });
    }

//    @Override
    public Stream<Object> whereValues() {
        return whereConditions.stream()
                .map(WhereCondition::getValue);
    }

//    @Override
    @SuppressWarnings("Duplicates")
    public Map.Entry<Stream<Predicate>,Stream<Object>> wherePredicatesWithJSql(){
        List<Object> values = new ArrayList<>();
        Stream<Predicate> predicates = whereConditions.stream()
                .map(whereCondition -> {
                    Object value = whereCondition.getValue();
                    values.add(value);
                    value = placeholder();
                    Predicate predicate = whereCondition.getPredicateFunction().apply(
                            whereCondition.getFieldGetter(),
                            value);
                    return predicate;
                });

        return new AbstractMap.SimpleEntry<>(
                predicates,
                values.stream());
    }


    public interface DeleteComparison<T,U> {
        Delete<T> equalTo(U value);
        Delete<T> notEqualTo(U value);
        Delete<T> like(U value);
    }

}
