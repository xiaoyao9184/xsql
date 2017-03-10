package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * COUNT(column_name)
 *
 * COUNT(DISTINCT column_name)
 *
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Count implements Expression {
    private boolean distinct;
    private Column column;

    public Count(){
        this.column = new Column();
    }

    public Count(Column column){
        this.column = column;
    }

    public Count(Column column, boolean distinct){
        this.column = column;
        this.distinct = distinct;
    }

    @Override
    public List<Element> toElementList() {
        if(this.distinct){
            return new ListBuilder<Element>()
                    .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                    .withItem(OtherEnum.GROUP_START)
                    .withItem(GrammarEnum.DISTINCT)
                    .withItem(OtherEnum.SPACE)
                    .withItem(column)
                    .withItem(OtherEnum.GROUP_END)
                    .build(null);
        }else{
            return new ListBuilder<Element>()
                    .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                    .withItem(OtherEnum.GROUP_START)
                    .withItem(column)
                    .withItem(OtherEnum.GROUP_END)
                    .build(null);
        }
    }
}
