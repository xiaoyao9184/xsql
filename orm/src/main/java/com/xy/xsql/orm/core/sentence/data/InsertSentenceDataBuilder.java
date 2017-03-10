package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.x.XInsert;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.Value;
import com.xy.xsql.orm.data.sql.sentence.insert.InsertSentence;

import java.util.List;

/**
 * InsertSentenceDataBuilder
 * core InsertSentence by code
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class InsertSentenceDataBuilder implements BaseBuilder<Void,InsertSentence>,
        XInsert<InsertSentenceDataBuilder> {

    private InsertSentence insertSentence;


    public XInsert<XInsert> toX() {
        return (XInsert)this;
    }

    @Override
    public InsertSentence build(Void av) {
        return this.insertSentence;
    }

    @Override
    public InsertSentenceDataBuilder insert() {
        if(this.insertSentence == null){
            this.insertSentence = new InsertSentence();
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder into() {
        this.insert();
        return this;
    }

    @Override
    public InsertSentenceDataBuilder insertInto(String tableName) {
        this.insert();
        this.insertSentence.withTable(new Table(tableName));
        return this;
    }

    @Override
    public InsertSentenceDataBuilder insertInto(Table table) {
        this.insert();
        this.insertSentence.withTable(table);
        return this;
    }

    @Override
    public InsertSentenceDataBuilder table(String tableName) {
        this.insertSentence.withTable(new Table(tableName));
        return this;
    }

    @Override
    public InsertSentenceDataBuilder table(Table table) {
        this.insertSentence.withTable(table);
        return this;
    }

    @Override
    public InsertSentenceDataBuilder columns(String... columns) {
        for (String columnName: columns) {
            this.insertSentence.withColumn(new Column(columnName));
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder columns(List<Column> columnList) {
        for (Column column: columnList) {
            this.insertSentence.withColumn(column);
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder values(int valueCount) {
        for (int i = 1; i <= valueCount; i++) {
            this.insertSentence.withValues(new Value());
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder values(int valueCount, int valueRepeatCount) {
        for (int index = 0; index < valueRepeatCount; index++) {
            for (int i = 1; i <= valueCount; i++) {
                this.insertSentence.withValues(index, new Value());
            }
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder values(List<Value> valueList) {
        for (Value value: valueList) {
            this.insertSentence.withValues(value);
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder values(List<Value>... valueRepeatList) {
        int index = 0;
        for (List<Value> valueList: valueRepeatList) {
            for (Value value: valueList) {
                this.insertSentence.withValues(index,value);
            }
            index++;
        }
        return this;
    }

    @Override
    public InsertSentenceDataBuilder columnsValues(String... columns) {
        return this.columns(columns).values(columns.length);
    }

    @Override
    public InsertSentenceDataBuilder columnsValues(List<Column> columnList) {
        return this.columns(columnList).values(columnList.size());
    }

    @Override
    public InsertSentenceDataBuilder columnsValues(List<Column> columnList, int valueRepeatCount) {
        return this.columns(columnList).values(columnList.size(),valueRepeatCount);
    }

    @Override
    public InsertSentenceDataBuilder columnsValues(List<Column> columnList, List<Value> valueList) {
        return this.columns(columnList).values(valueList);
    }

    @Override
    public InsertSentenceDataBuilder columnsValues(List<Column> columnList, List<Value>... valueGroupList) {
        return this.columns(columnList).values(valueGroupList);
    }

}
