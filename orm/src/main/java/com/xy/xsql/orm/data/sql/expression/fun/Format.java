package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.core.ListBuilder;

import java.util.List;

/**
 * FORMAT(column_name,format)
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Format implements Expression {
    private Column column;
    private String format;

    public Format(){
        this.column = new Column();
    }

    public Format(Column column, String format){
        this.column = column;
        this.format = format;
    }

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                .withItem(OtherEnum.GROUP_START)
                .withItem(column)
                .withItem(OtherEnum.DELIMITER)
                .withItem(new UnknownString(format))
                .withItem(OtherEnum.GROUP_END)
                .build(null);
    }
}
