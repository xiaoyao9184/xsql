package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.core.ListBuilder;

import java.util.List;

/**
 * MID(column_name,start[,length])
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Mid implements Expression {
    private Column column;
    private int start;
    private int length;

    public Mid(){
        this.column = new Column();
        this.start = 1;
        this.length = -1;
    }

    public Mid(Column column, int start){
        this.column = column;
        this.start = start;
        this.length = -1;
    }

    public Mid(Column column, int start, int length){
        this.column = column;
        this.start = start;
        this.length = length;
    }

    @Override
    public List<Element> toElementList() {
        if(length <= 0){
            return new ListBuilder<Element>()
                    .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                    .withItem(OtherEnum.GROUP_START)
                    .withItem(column)
                    .withItem(OtherEnum.DELIMITER)
                    .withItem(new UnknownString(start + ""))
                    .withItem(OtherEnum.GROUP_END)
                    .build(null);
        }else{
            return new ListBuilder<Element>()
                    .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                    .withItem(OtherEnum.GROUP_START)
                    .withItem(column)
                    .withItem(OtherEnum.DELIMITER)
                    .withItem(new UnknownString(start + ""))
                    .withItem(OtherEnum.DELIMITER)
                    .withItem(new UnknownString(length + ""))
                    .withItem(OtherEnum.GROUP_END)
                    .build(null);
        }
    }
}
