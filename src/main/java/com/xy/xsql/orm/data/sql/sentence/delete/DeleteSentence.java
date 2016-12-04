package com.xy.xsql.orm.data.sql.sentence.delete;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.data.sql.sentence.select.JoinItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class DeleteSentence extends CustomizeSentence {

    private List<Table> tables;
    private Table table;
    private List<JoinItem> joins;
    private Expression where;
//    private Limit limit;
//    private List<OrderByElement> orderByElements;


    public DeleteSentence() {
        super();
    }


    public void withTable(Table table) {
        if(this.tables == null){
            this.tables = new ArrayList<>();
        }
        this.tables.add(table);
    }

    public void withFromTable(Table table) {
        this.table = table;
    }

    public void withJoin(JoinItem join) {
        if(this.joins == null){
            this.joins = new ArrayList<>();
        }
        this.joins.add(join);
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.DELETE);
        if(tables != null && tables.size() > 0){
            builder.append(OtherEnum.SPACE);
            int i = 0;
            for(Table t : tables){
                if(i != 0){
                    builder.append(OtherEnum.DELIMITER);
                }
                builder.append(t.getAliasName());
                i++;
            }
        }

        builder.append(OtherEnum.SPACE);
        builder.append(GrammarEnum.FROM);
        builder.append(OtherEnum.SPACE);
        builder.append(table.getFullName());
        builder.append(OtherEnum.SPACE);
        if (joins != null) {
            for (JoinItem join : joins) {
                if (join.isSimple()) {
                    builder.append(OtherEnum.DELIMITER)
                            .append(join.toElementList(),OtherEnum.SPACE);
                } else {
                    builder.append(OtherEnum.SPACE)
                            .append(join.toElementList(),OtherEnum.SPACE);
                }
            }
        }

        if( where != null ){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.WHERE)
                    .append(OtherEnum.SPACE)
                    .append(where);
        }


        return new BaseElementsSentence(builder.build(null));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(GrammarEnum.DELETE);
        if(tables != null && tables.size() > 0){
            builder.append(OtherEnum.SPACE);
            int i = 0;
            for(Table t : tables){
                if(i != 0){
                    builder.append(OtherEnum.DELIMITER);
                }
                builder.append(t.getAliasName());
                i++;
            }
        }

        builder.append(OtherEnum.SPACE);
        builder.append(GrammarEnum.FROM);
        builder.append(OtherEnum.SPACE);
        builder.append(table.getFullName());
        builder.append(OtherEnum.SPACE);
        if (joins != null) {
            for (JoinItem join : joins) {
                if (join.isSimple()) {
                    builder.append(OtherEnum.DELIMITER).append(join);
                } else {
                    builder.append(OtherEnum.SPACE).append(join);
                }
            }
        }

        if( where != null ){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.WHERE)
                    .append(OtherEnum.SPACE)
                    .append(where);
        }

//        if(orderByElements!=null){
//            b.append(PlainSelect.orderByToString(orderByElements));
//        }
//
//        if(limit != null){
//            b.append(limit);
//        }
        return builder.toString();
    }
}
