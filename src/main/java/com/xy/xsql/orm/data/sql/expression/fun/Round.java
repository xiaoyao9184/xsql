package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * ROUND(column_name,decimals)
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Round implements Expression {
    private Column column;
    private int decimals;

    public Round(){
        this.column = new Column();
    }

    public Round(Column column, int decimals){
        this.column = column;
    }

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                .withItem(OtherEnum.GROUP_START)
                .withItem(column)
                .withItem(OtherEnum.DELIMITER)
                .withItem(new UnknownString(decimals + ""))
                .withItem(OtherEnum.GROUP_END)
                .build(null);
    }
}
