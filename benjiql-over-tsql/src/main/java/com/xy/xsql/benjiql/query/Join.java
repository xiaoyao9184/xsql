package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.core.ClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.predicate.Comparison;
import com.xy.xsql.tsql.model.predicate.Predicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Join<T,U>
        implements JoinSpecifier<T,U>  {
    private final QueryChain<T> from;
    private final Class<U> to;

    //lazy buildClassTable
    private List<Function<T,?>> joinConditions = new ArrayList<>();
    private ClassTableMetaBuilder builder;


    public Join(QueryChain<T> from, Class<U> to) {
        this.from = from;
        this.to = to;
    }

    public <V extends Serializable> Select<U> using(Function<T, V> p1) {
        joinConditions.add(p1);
        return new Select<>(to, this);
    }

    public <V extends Serializable, W extends Serializable> Select<U> using(Function<T, V> p1, Function<T,W> p2) {
        Select<U> toSelect = using(p1);
        joinConditions.add(p2);
        return toSelect;
    }




    private ClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new ClassTableMetaBuilder()
                    .build(this.to);
        }
        return builder;
    }

    @SuppressWarnings("Duplicates")
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

                    Comparison comparison = new Comparison();
                    comparison.setExpression(fromColumnName);
                    comparison.setOperator(Operators.EQUAL);
                    comparison.setOperatorExpression(toColumnName);
                    return comparison;
                })
                .map(comparison -> (Predicate)comparison)
                .collect(Collectors.toList());

        TSqlConversions.searchCondition(joinPredicates)
                .ifPresent(table::setSearchCondition);

        return table;
    }

    public Stream<Predicate> wherePredicates() {
        return from.wherePredicates();
    }

    public Stream<Map.Entry<Predicate,Object>> wherePredicatesWithJSql(){
        return from.wherePredicatesWithJSql();
    }

}
