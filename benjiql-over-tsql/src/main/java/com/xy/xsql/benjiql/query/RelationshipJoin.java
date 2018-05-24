package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.RelationshipClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.builder.chain.elements.operators.Operators;
import com.xy.xsql.tsql.model.queries.predicates.Comparison;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RelationshipJoin<T,U>
        implements RelationshipJoinSpecifier<T,U>, QueryChain<U> {
    private final QueryChain<T> from;
    private final JoinTables<T, U> to;
    private final ProxyObjectMethodRecording<U> recorder;

    //lazy buildClassTable
    //all conditions is about left table
    private List<Function<T,?>> joinConditions = new ArrayList<>();
    private RelationshipClassTableMetaBuilder builder;


    public RelationshipJoin(QueryChain<T> from, JoinTables<T, U> to) {
        this.from = from;
        this.to = to;
        this.recorder = ProxyObjectMethodRecording.create(to.getRightTable());
    }

    @Override
    public <V extends Serializable> CanOnlyJoin<U> using(Function<T, V> p1) {
        joinConditions.add(p1);

        return new CanOnlyJoin<U>() {
            public <W> JoinSpecifier<U, W> join(Class<W> table) {
                return new Join<>(RelationshipJoin.this, table);
            }

            @Override
            public <W> RelationshipJoinSpecifier<U, W> join(JoinTables<U, W> table) {
                return new RelationshipJoin<>(RelationshipJoin.this,  table);
            }
        };
    }

    @Override
    public <V extends Serializable, W extends Serializable> CanOnlyJoin<U> using(Function<T, V> p1, Function<T, W> p2) {
        CanOnlyJoin<U> result = using(p1);
        joinConditions.add(p2);
        return result;
    }




    private RelationshipClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new RelationshipClassTableMetaBuilder()
                    .build(this.to);
        }
        return builder;
    }


    @Override
    public ProxyObjectMethodRecording<U> recorder(Function<U, ?> p1) {
        p1.apply(recorder.getObject());
        return recorder;
    }

    @Override
    public ColumnName column(Function<U, ?> p1) {
        p1.apply(recorder.getObject());
        return buildClassTable().getColumnName(recorder.getMethod());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public From.TableSource fromClause() {
        From.JoinedTable table = new From.JoinedTable();
        table.setUseJoinOn(true);
        table.setJoinType(new From.JoinType(From.JoinTypeKeywords.JOIN));
        //left
        table.setTableSource(from.fromClause());
        //right
        TableName tableName = buildClassTable().getTableName();
        From.BaseTable toTable = new From.BaseTable();
        toTable.setTableName(tableName);
        table.setTableSource2(toTable);
        //on
        List<Predicate> joinPredicates = joinConditions.stream()
                .map(function -> {
                    ColumnName fromColumnName = from.column(function);

                    ColumnName toColumnName = buildClassTable().getColumnName(from.recorder(function).getMethod());

//                    ColumnName toColumnName = new ColumnName();
//                    toColumnName.setName(fromColumnName.getTable().getTableOrViewName() + "_" + fromColumnName.getName());
//                    toColumnName.setTable(buildClassTable().getTableName());

                    Comparison comparison = new Comparison();
                    comparison.setExpression(fromColumnName);
                    comparison.setOperator(Operators.$Equal);
                    comparison.setOperatorExpression(toColumnName);
                    return comparison;
                })
                .map(comparison -> (Predicate)comparison)
                .collect(Collectors.toList());

        TSqlConversions.searchCondition(joinPredicates)
                .ifPresent(table::setSearchCondition);

        return table;
    }

    @Override
    public Stream<Predicate> wherePredicates() {
        return from.wherePredicates();
    }

    @Override
    public Stream<Object> whereValues() {
        return from.whereValues();
    }

    @Override
    public Stream<Map.Entry<Predicate,Object>> wherePredicatesWithJSql(){
        return from.wherePredicatesWithJSql();
    }

//    @Override
//    public int setPlaceholders(PreparedStatement statement) throws SQLException {
//        return from.setPlaceholders(statement);
//    }
}
