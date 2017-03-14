package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.core.ListBuilder;

import java.util.List;

/**
 * SUM(column_name)
 *
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Sum implements Expression {
    private Column column;

    public Sum(){
        this.column = new Column();
    }

    public Sum(Column column){
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
