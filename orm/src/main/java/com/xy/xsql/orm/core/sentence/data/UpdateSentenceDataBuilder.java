package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.orm.core.x.XUpdate;
import com.xy.xsql.orm.data.sql.element.info.*;
import com.xy.xsql.orm.data.sql.sentence.update.SetItem;
import com.xy.xsql.orm.data.sql.sentence.update.UpdateSentence;

import java.util.List;

/**
 * UpdateSentenceDataBuilder
 * core UpdateSentence by code
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class UpdateSentenceDataBuilder implements BaseBuilder<Void,UpdateSentence>,
        XUpdate<UpdateSentenceDataBuilder> {

    private UpdateSentence updateSentence;

    @Override
    public UpdateSentence build(Void av) {
        return this.updateSentence;
    }

    @Override
    public UpdateSentenceDataBuilder update() {
        if(this.updateSentence == null){
            this.updateSentence = new UpdateSentence();
        }
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder update(String table) {
        this.update();
        this.updateSentence.withTable(new Table(table));
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder update(Table table) {
        this.update();
        this.updateSentence.withTable(table);
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder table(String table) {
        this.updateSentence.withTable(new Table(table));
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder table(Table table) {
        this.updateSentence.withTable(table);
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder set(String... columns) {
        for (String columnName: columns) {
            this.updateSentence.withSet(
                    new SetItem()
                            .withColumn(new Column(columnName)));
        }
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder set(Column column) {
        this.updateSentence.withSet(
                new SetItem()
                        .withColumn(column));
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder set(List<Column> columnList) {
        for (Column column: columnList) {
            this.updateSentence.withSet(
                    new SetItem()
                            .withColumn(column));
        }
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder set(Column column, Value value) {
        this.updateSentence.withSet(
                new SetItem()
                        .withColumn(column)
                        .withSetExpression(value));
        return this;
    }

    @Override
    public UpdateSentenceDataBuilder set(List<Column> columnList, List<Value> valueList) {
        int index = 0;
        for (Column column: columnList) {
            this.updateSentence.withSet(
                    new SetItem()
                            .withColumn(column)
                            .withSetExpression(valueList.get(index)));
            index++;
        }
        return this;
    }

}
