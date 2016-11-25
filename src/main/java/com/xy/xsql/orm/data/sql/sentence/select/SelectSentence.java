package com.xy.xsql.orm.data.sql.sentence.select;

import com.xy.xsql.orm.build.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class SelectSentence extends CustomizeSentence {

    private boolean useDistinct;
    private TopItem top;
    private List<Column> columns;
    private Table table;
    private List<JoinItem> joins;


    public SelectSentence() {
        super();
    }




    public SelectSentence withDistinct(){
        this.useDistinct = true;
        return this;
    }


    public void withTop(TopItem topItem) {
        this.top = topItem;
    }

    public SelectSentence withColumn(Column column) {
        if(this.columns == null){
            this.columns = new ArrayList<>();
        }
        this.columns.add(column);
        return this;
    }

    public SelectSentence withTable(Table table) {
        this.table = table;
        return this;
    }

    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.SELECT);
        if(useDistinct){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.DISTINCT);
        }
        if(top != null){
            builder.append(top.toElementList(),OtherEnum.SPACE);
        }

        return new BaseElementsSentence(builder.build(null));
    }

    @Override
    public String toString(){
        //TODO
//        StringBuilder builder = new StringBuilder()
//                .append(GrammarEnum.SELECT);
//        if(useDistinct){
//            builder.append(OtherEnum.SPACE)
//                    .append(GrammarEnum.DISTINCT);
//        }
//        if(top != null){
//            builder.append(top.toElementList(),OtherEnum.SPACE);
//
//            builder.append(OtherEnum.SPACE)
//                    .append(GrammarEnum.TOP)
//                    .append(OtherEnum.SPACE)
//                    .append(new UnknownString(top_count + ""));
//            if(top_percent){
//                builder.append(OtherEnum.SPACE)
//                        .append(GrammarEnum.PERCENT);
//            }
//        }
//        return builder.toString();
        return null;
    }

}
