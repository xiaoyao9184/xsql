package com.xy.xsql.orm.data.sql.sentence.update;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class UpdateSentence extends CustomizeSentence {

    private Table table;
    private List<SetItem> sets;
    private Expression where;


    public UpdateSentence() {
        super();
    }


    public void withTable(Table table) {
        this.table = table;
    }

    public void withSet(SetItem set) {
        if(this.sets == null){
            this.sets = new ArrayList<>();
        }
        this.sets.add(set);
    }

    public void withWhere(Expression where){
        this.where = where;
    }



    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.UPDATE)
                .append(OtherEnum.SPACE)
                .append(table)
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.SET);
        for (SetItem set: sets) {
            builder.append(set.toElementList(),OtherEnum.SPACE);
            builder.append(OtherEnum.DELIMITER);
        }

        return new BaseElementsSentence(builder.build(null));
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(GrammarEnum.UPDATE)
                .append(OtherEnum.SPACE)
                .append(table.getFullName())
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.SET);
        for (SetItem set: sets) {
            builder.append(set);
            builder.append(OtherEnum.DELIMITER);
        }

        return builder.toString();
    }

}
