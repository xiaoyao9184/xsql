package com.xy.xsql.orm.data.sql.sentence.insert;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.expression.operator.relational.Group;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class InsertSentence extends CustomizeSentence {

    private Table table;

    private List<Column> columns;

    private boolean useValues = true;

    private List<Group> groupValues;

    public InsertSentence() {
        super();
    }


    public void withTable(Table table) {
        this.table = table;
    }

    public void withColumn(Column column) {
        if(this.columns == null){
            this.columns = new ArrayList<>();
        }
        this.columns.add(column);
    }

    public void withUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public void withValues(Expression value) {
        if(this.groupValues == null){
            this.groupValues = new ArrayList<>();
            this.groupValues.add(new Group());
        }
        this.groupValues.get(0).withExpression(value);
    }

    public void withValues(int index, Expression value) {
        if(this.groupValues == null){
            this.groupValues = new ArrayList<>();
        }
        try {
            ListUtil.fill(this.groupValues,index+1,Group.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        this.groupValues.get(index).withExpression(value);
    }

    public Table getTable() {
        return table;
    }

    public List<Column> getColumn() {
        return columns;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public Group getValues() {
        return groupValues.get(0);
    }

    public List<Group> getGroupValues() {
        return groupValues;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.INSERT)
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.INTO)
                .append(OtherEnum.SPACE)
                .append(table)
                .append(OtherEnum.GROUP_START);
        for (Column column: columns) {
            builder.append(column);
            builder.append(OtherEnum.DELIMITER);
        }
        builder
                .append(OtherEnum.GROUP_END)
                .append(useValues ? GrammarEnum.VALUES : null);
        for (Group group: groupValues) {
            builder.append(group.toElementList(),OtherEnum.DELIMITER);
            builder.append(OtherEnum.SPACE);
        }
        return new BaseElementsSentence(builder.build(null));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(GrammarEnum.INSERT)
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.INTO)
                .append(OtherEnum.SPACE)
                .append(table.getFullName())
                .append(OtherEnum.SPACE)
                .append(OtherEnum.GROUP_START);
        for (Column column: columns) {
            builder.append(column);
            builder.append(OtherEnum.DELIMITER);
        }
        builder.append(OtherEnum.GROUP_END)
                .append(useValues ? GrammarEnum.VALUES : "");
        for (Group group: groupValues) {
            builder.append(group);
            builder.append(OtherEnum.SPACE);
        }
        return builder.toString();
    }
}
