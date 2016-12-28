package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.x.XSelect;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.info.*;
import com.xy.xsql.orm.data.sql.expression.NumberString;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.select.SelectSentence;

import java.util.List;
import java.util.Map;

/**
 * CodeSentenceDataBuilder
 * core BaseElementsSentence by code
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class SelectSentenceDataBuilder implements BaseBuilder<BaseElementsSentence,SelectSentence>, 
        XSelect<SelectSentenceDataBuilder> {

    private List<Element> elementList;
    private SelectSentence selectSentence;


    @Override
    public SelectSentence build(BaseElementsSentence baseElementsSentence) {
        this.elementList = baseElementsSentence.getData();
        fullElement();
        this.selectSentence = new SelectSentence();
        return this.selectSentence;
    }

    public void fullElement(){
        int i = 0;
        for (Element element: elementList) {
            GrammarEnum.SELECT.equals(element);
        }
    }


    @Override
    public SelectSentenceDataBuilder select() {
        this.selectSentence = new SelectSentence();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder distinct() {
        this.selectSentence.withDistinct();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder top(int count, boolean percent) {
        Top top = new Top();
        top.setCountExpression(new NumberString(count));
        top.setUsePercent(percent);
        this.selectSentence.withTop(top);
        return this;
    }

    @Override
    public SelectSentenceDataBuilder columns(String... columns) {
        for (String columnName: columns) {
            this.selectSentence.withColumn(new Column(columnName));
        }
        return this;
    }

    @Override
    public SelectSentenceDataBuilder columns(List<Column> columnList) {
        for (Column column: columnList) {
            this.selectSentence.withColumn(column);
        }
        return this;
    }

    @Override
    public SelectSentenceDataBuilder from() {
        return this;
    }

    @Override
    public SelectSentenceDataBuilder from(String tableName) {
        this.selectSentence.withTable(new Table(tableName));
        return this;
    }

    @Override
    public SelectSentenceDataBuilder from(Table table) {
        this.selectSentence.withTable(table);
        return this;
    }


    @Override
    public SelectSentenceDataBuilder from(Sentence sentence) {
        this.selectSentence.withTable(new Table(sentence));
        return this;
    }

    @Override
    public SelectSentenceDataBuilder leftjoin() {
        this.selectSentence.getJoins().get(this.selectSentence.getJoins().size() - 1)
                .withLeft();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder leftjoin(String tableName) {
//        this.selectSentence.withJoin(new JoinItem()
//                .withLeft()
//                .withTable(new Table(tableName)));
        return this;
    }

    @Override
    public SelectSentenceDataBuilder leftjoin(Table table) {
//        this.selectSentence.withJoin(new JoinItem()
//                .withLeft()
//                .withTable(table));
        return this;
    }

    @Override
    public SelectSentenceDataBuilder leftjoin(Map<Table, List<Param>> leftJoinParam) {
        //TODO
        return this;
    }

    @Override
    public SelectSentenceDataBuilder on() {
//        this.selectSentence.getJoins().get(this.selectSentence.getJoins().size() - 1)
//                .withOn();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder on(String columnName, String relationship) {
//        this.selectSentence.getLastJoin()
//                .withOn()
//                .withColumns(new Column(columnName))
//                .withRelationship(relationship)
//                .withValue();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder on(Column column, Column leftColumn) {
//        this.selectSentence.getLastJoin()
//                .withOn()
//                .withColumns(new Column(columnName))
//                .withRelationship(relationship)
//                .withValue();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder on(Column column, Value value) {
//        this.selectSentence.getLastJoin()
//                .withOn()
//                .withColumns(new Column(columnName))
//                .withRelationship(relationship)
//                .withValue();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder on(Param param) {
//        this.selectSentence.getLastJoin()
//                .withOn()
//                .withColumns(new Column(columnName))
//                .withRelationship(relationship)
//                .withValue();
        return this;
    }

    @Override
    public SelectSentenceDataBuilder orderBy() {
        return this;
    }

    @Override
    public SelectSentenceDataBuilder orderBy(String... columns) {
        for (String columnName: columns) {
//            this.selectSentence.withOrder(new Column(columnName));
        }
        return this;
    }

    @Override
    public SelectSentenceDataBuilder orderBy(List<Order> orderList) {
        for (Order order: orderList) {
//            this.selectSentence.withOrder(order);
        }
        return this;
    }
}
