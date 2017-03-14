package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.core.ListBuilder;

import java.util.List;

/**
 * UCASE(column_name)
 *
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Ucase implements Expression {
    private Column column;

    public Ucase(){
        this.column = new Column();
    }

    public Ucase(Column column){
        this.column = column;
    }

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                .withItem(OtherEnum.GROUP_START)
                .withItem(column)
                .withItem(OtherEnum.GROUP_END)
                .build(null);
    }
}