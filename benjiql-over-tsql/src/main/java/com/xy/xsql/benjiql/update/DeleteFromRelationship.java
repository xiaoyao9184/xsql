package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.RelationshipClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.benjiql.query.Select;
import com.xy.xsql.benjiql.query.WhereCondition;
import com.xy.xsql.benjiql.util.Conventions;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.entity.model.jpql.PlaceholderJPql;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.UnknownExpression;
import com.xy.xsql.tsql.model.predicate.Predicate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteFromRelationship<L, R> {
    private final Class<L> leftCls;
    private final Class<R> rightCls;

    //lazy
    private final List<WhereCondition<L>> leftWhereConditions = new ArrayList<>();
    private final List<WhereCondition<R>> rightWhereConditions = new ArrayList<>();
    private final ProxyObjectMethodRecording<L> leftRecorder;
    private final ProxyObjectMethodRecording<R> rightRecorder;
    private final JoinTables<L,R> joinTables;
    private RelationshipClassTableMetaBuilder builder;

    public DeleteFromRelationship(Class<L> left, Class<R> right) {
        this.leftCls = left;
        this.rightCls = right;
        this.leftRecorder = ProxyObjectMethodRecording.create(left);
        this.rightRecorder = ProxyObjectMethodRecording.create(right);
        this.joinTables = new JoinTables<>(this.leftCls, this.rightCls);
    }

    public <V> DeleteJoinComparison<L,R,V> andLeft(Function<L,V> getter) {
        return whereLeft(getter);
    }

    @SuppressWarnings("Duplicates")
    public <V> DeleteJoinComparison<L,R,V> whereLeft(Function<L,V> getter) {
        WhereCondition<L> whereCondition = new WhereCondition<>(getter);
        leftWhereConditions.add(whereCondition);
        return new DeleteJoinComparison<L, R, V>() {
            @Override
            public DeleteFromRelationship<L, R> equalTo(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.equalTo(leftColumn(f),v));
                return DeleteFromRelationship.this;
            }

            public DeleteFromRelationship<L, R> notEqualTo(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.notEqualTo(leftColumn(f),v));
                return DeleteFromRelationship.this;
            }

            public DeleteFromRelationship<L, R> like(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.like(leftColumn(f),v));
                return DeleteFromRelationship.this;
            }
        };
    }

    public <V> DeleteJoinComparison<L,R,V> andRight(Function<R,V> getter) {
        return whereRight(getter);
    }

    @SuppressWarnings("Duplicates")
    public <V> DeleteJoinComparison<L,R,V> whereRight(Function<R,V> getter) {
        WhereCondition<R> whereCondition = new WhereCondition<>(getter);
        rightWhereConditions.add(whereCondition);
        return new DeleteJoinComparison<L, R, V>() {
            @Override
            public DeleteFromRelationship<L, R> equalTo(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.equalTo(rightColumn(f),v));
                return DeleteFromRelationship.this;
            }

            public DeleteFromRelationship<L, R> notEqualTo(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.notEqualTo(rightColumn(f),v));
                return DeleteFromRelationship.this;
            }

            public DeleteFromRelationship<L, R> like(V value) {
                whereCondition.setValue(value);
                whereCondition.setPredicateFunction((f, v) -> Select.PredicateBuildFunction.like(rightColumn(f),v));
                return DeleteFromRelationship.this;
            }
        };
    }


    public ColumnName leftColumn(Function<L, ?> p1) {
        p1.apply(leftRecorder.getObject());
        ColumnName columnName = buildRelationshipTable().getColumnName(leftRecorder.getMethod());
        columnName.setTable(null);
        return columnName;
    }

    public ColumnName rightColumn(Function<R, ?> p1) {
        p1.apply(rightRecorder.getObject());
        ColumnName columnName = buildRelationshipTable().getColumnName(rightRecorder.getMethod());
        columnName.setTable(null);
        return columnName;
    }

    private RelationshipClassTableMetaBuilder buildRelationshipTable(){
        if(builder == null){
            builder = new RelationshipClassTableMetaBuilder()
                    .build(this.joinTables);
        }
        return builder;
    }


    public String toSql() {
        com.xy.xsql.tsql.model.statement.dml.Delete delete = buildTSql();
        return BlockManager.INSTANCE.print(delete);
    }

    private com.xy.xsql.tsql.model.statement.dml.Delete buildTSql(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(leftCls, rightCls));

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

    public Stream<Predicate> wherePredicates() {
        Stream<Predicate> left = leftWhereConditions.stream()
                .map(WhereCondition::buildPredicate);
        Stream<Predicate> right = rightWhereConditions.stream()
                .map(WhereCondition::buildPredicate);
        return Stream.concat(left,right);
    }


    public PlaceholderJPql toJPql(){
        Map.Entry<com.xy.xsql.tsql.model.statement.dml.Delete,Stream<Object>> queryWithJPql = buildTSqlWithJPql();
        com.xy.xsql.tsql.model.statement.dml.Delete delete = queryWithJPql.getKey();
        List<Object> args = queryWithJPql.getValue().collect(Collectors.toList());

        return new PlaceholderJPql()
                .withSql(BlockManager.INSTANCE.print(delete))
                .withArgs(args);
    }

    private Map.Entry<com.xy.xsql.tsql.model.statement.dml.Delete,Stream<Object>> buildTSqlWithJPql(){
        TableName tableName = new TableName();
        tableName.setTableOrViewName(Conventions.toDbName(leftCls, rightCls));

        Map.Entry<Optional<Where>,Stream<Object>> kv = buildWhereWithJPql();

        com.xy.xsql.tsql.model.statement.dml.Delete delete = new com.xy.xsql.tsql.model.statement.dml.Delete();
        delete.setTableName(tableName);
        delete.setWhere(kv.getKey().orElse(null));

        return new AbstractMap.SimpleEntry<>(delete,kv.getValue());
    }

    public Map.Entry<Optional<Where>,Stream<Object>> buildWhereWithJPql() {
        Map.Entry<Stream<Predicate>,Stream<Object>> kv = this.wherePredicatesWithJPql();

        Optional<Where> optionalWhere = TSqlConversions.where(kv.getKey()
                .collect(Collectors.toList()));

        return new AbstractMap.SimpleEntry<>(optionalWhere,kv.getValue());
    }

    @SuppressWarnings("Duplicates")
    public Map.Entry<Stream<Predicate>,Stream<Object>> wherePredicatesWithJPql(){
        List<Object> values = new ArrayList<>();

        Stream<Predicate> left = leftWhereConditions.stream()
                .map(whereCondition -> {
                    Object value = whereCondition.getValue();
                    values.add(value);
                    value = new UnknownExpression("?");
                    Predicate predicate = whereCondition.getPredicateFunction().apply(
                            whereCondition.getFieldGetter(),
                            value);
                    return predicate;
                });
        Stream<Predicate> right = rightWhereConditions.stream()
                .map(whereCondition -> {
                    Object value = whereCondition.getValue();
                    values.add(value);
                    value = new UnknownExpression("?");
                    Predicate predicate = whereCondition.getPredicateFunction().apply(
                            whereCondition.getFieldGetter(),
                            value);
                    return predicate;
                });

        return new AbstractMap.SimpleEntry<>(
                Stream.concat(left,right),
                values.stream());
    }



    public interface DeleteJoinComparison<L,R,V> {
        DeleteFromRelationship<L,R> equalTo(V value);
        DeleteFromRelationship<L,R> notEqualTo(V value);
        DeleteFromRelationship<L,R> like(V value);
    }


}
