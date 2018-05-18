package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.benjiql.core.RelationshipClassTableMetaBuilder;
import com.xy.xsql.benjiql.core.TSqlConversions;
import com.xy.xsql.benjiql.ddl.JoinTables;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statements.Insert;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.benjiql.update.Upsert.Insert.buildTableValueConstructor;

public class InsertRelationship<L,R>  {
    private final L leftValue;
    private final R rightValue;

    private List<Upsert.UpdateSet<L>> leftConditions = new ArrayList<>();
    private List<Upsert.UpdateSet<R>> rightConditions = new ArrayList<>();
    private final ProxyObjectMethodRecording<L> leftRecorder;
    private final ProxyObjectMethodRecording<R> rightRecorder;
    private RelationshipClassTableMetaBuilder builder;

    @SuppressWarnings("unchecked")
    public InsertRelationship(L leftValue, R rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.leftRecorder = ProxyObjectMethodRecording.create((Class<L>) leftValue.getClass());
        this.rightRecorder = ProxyObjectMethodRecording.create((Class<R>) rightValue.getClass());
    }


    public <V extends Serializable> InsertRelationship<L,R> valueLeft(Function<L,V> getter) {
        Upsert.UpdateSet<L> updateSet = new Upsert.UpdateSet<>();
        updateSet.setFieldGetter(getter);
        updateSet.setValue(getter.apply(leftValue));
        leftConditions.add(updateSet);
        return this;
    }

    public <V extends Serializable> InsertRelationship<L,R> valueRight(Function<R,V> getter) {
        Upsert.UpdateSet<R> updateSet = new Upsert.UpdateSet<>();
        updateSet.setFieldGetter(getter);
        updateSet.setValue(getter.apply(rightValue));
        rightConditions.add(updateSet);
        return this;
    }



    public ColumnName leftColumn(Function<L, ?> p1) {
        p1.apply(leftRecorder.getObject());
        ColumnName columnName = buildClassTable().getColumnName(leftRecorder.getMethod());
        columnName.setTable(null);
        return columnName;
    }

    public ColumnName rightColumn(Function<R, ?> p1) {
        p1.apply(rightRecorder.getObject());
        ColumnName columnName = buildClassTable().getColumnName(rightRecorder.getMethod());
        columnName.setTable(null);
        return columnName;
    }

    private RelationshipClassTableMetaBuilder buildClassTable(){
        if(builder == null){
            builder = new RelationshipClassTableMetaBuilder()
                    .build(JoinTables.relationship(leftValue.getClass(),rightValue.getClass()));
        }
        return builder;
    }


    public String toSql() {
        Insert insert = buildInsert();
        return BlockManager.INSTANCE.print(insert);
    }

    public PlaceholderJSql toJSql() {
        Map.Entry<Insert,Stream<Object>> insertWithJSql = buildInsertWithJSql();
        String sql = BlockManager.INSTANCE.print(insertWithJSql.getKey());
        List<Object> args = insertWithJSql.getValue().collect(Collectors.toList());

        return new PlaceholderJSql()
                .withSql(sql)
                .withArgs(args);
    }

    private Insert buildInsert(){
        TableName tableName = buildClassTable().getTableName();

        Stream<ColumnName> column1 = leftConditions.stream()
                .map(s -> leftColumn(s.getFieldGetter()));
        Stream<ColumnName> column2 = rightConditions.stream()
                .map(s -> rightColumn(s.getFieldGetter()));
        List<ColumnName> columnNameList =
                Stream.concat(column1,column2)
                .collect(Collectors.toList());

        TableValueConstructor tableValueConstructor = Stream
                .concat(
                    leftConditions.stream(),
                    rightConditions.stream())
                .map(Upsert.UpdateSet::getValue)
                .map(TSqlConversions::expression)
                .collect(buildTableValueConstructor());
//                .collect(() -> {
//                            TableValueConstructor t = new TableValueConstructor();
//                            List<List<Expression>> list = new ArrayList<>();
//                            list.add(new ArrayList<>());
//                            t.setRowValueExpressionListGroup(list);
//                            return t;
//                        },
//                        (values,e) -> values.getRowValueExpressionListGroup().get(0).add(e),
//                        (values1,values2) -> values1.getRowValueExpressionListGroup().get(0).addAll(values2.getRowValueExpressionListGroup().get(0)));

        Insert insert = new Insert();
        insert.setTableName(tableName);
        insert.setColumns(columnNameList);
        insert.setValues(tableValueConstructor);
        return insert;
    }

    private Map.Entry<Insert,Stream<Object>> buildInsertWithJSql(){
        List<Object> args = new ArrayList<>();

        TableName tableName = buildClassTable().getTableName();

        Stream<ColumnName> column1 = leftConditions.stream()
                .map(s -> leftColumn(s.getFieldGetter()));
        Stream<ColumnName> column2 = rightConditions.stream()
                .map(s -> rightColumn(s.getFieldGetter()));
        List<ColumnName> columnNameList =
                Stream.concat(column1,column2)
                        .collect(Collectors.toList());

        TableValueConstructor tableValueConstructor = Stream
                .concat(
                        leftConditions.stream(),
                        rightConditions.stream())
                .map(Upsert.UpdateSet::getValue)
                .map(v -> {
                    args.add(v);
                    return TSqlConversions.placeholderExpression();
                })
                .collect(buildTableValueConstructor());

        Insert insert = new Insert();
        insert.setTableName(tableName);
        insert.setColumns(columnNameList);
        insert.setValues(tableValueConstructor);

        return new AbstractMap.SimpleEntry<>(
                insert,
                args.stream());
    }

}
