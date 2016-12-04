package com.xy.xsql.orm.core.sentence.data;

import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.x.XDelete;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.delete.DeleteSentence;

import java.util.List;

/**
 * DeleteSentenceDataBuilder
 * core DeleteSentence by code
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class DeleteSentenceDataBuilder implements BaseBuilder<Void,DeleteSentence>,
        XDelete<DeleteSentenceDataBuilder> {

    private DeleteSentence deleteSentence;

    @Override
    public DeleteSentence build(Void aVoid) {
        return this.deleteSentence;
    }

    @Override
    public DeleteSentenceDataBuilder delete(String... tableNames) {
        if(this.deleteSentence == null){
            this.deleteSentence = new DeleteSentence();
        }
        for (String tableName: tableNames) {
            this.deleteSentence.withTable(new Table(tableName));
        }
        return this;
    }

    @Override
    public DeleteSentenceDataBuilder delete(Table... tables) {
        if(this.deleteSentence == null){
            this.deleteSentence = new DeleteSentence();
        }
        for (Table table: tables) {
            this.deleteSentence.withTable(table);
        }
        return this;
    }

    @Override
    public DeleteSentenceDataBuilder delete(List<Table> tableList) {
        if(this.deleteSentence == null){
            this.deleteSentence = new DeleteSentence();
        }
        for (Table table: tableList) {
            this.deleteSentence.withTable(table);
        }
        return this;
    }

    @Override
    public DeleteSentenceDataBuilder from(String tableName) {
        this.deleteSentence.withFromTable(new Table(tableName));
        return this;
    }

    @Override
    public DeleteSentenceDataBuilder from(Table table) {
        this.deleteSentence.withFromTable(table);
        return this;
    }

}
