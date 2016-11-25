package com.xy.xsql.orm.data.sql.expression.fun;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;
import com.xy.xsql.orm.util.ListBuilder;

import java.util.List;

/**
 * NOW()
 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Now implements Expression {

    @Override
    public List<Element> toElementList() {
        return new ListBuilder<Element>()
                .withItem(new UnknownString(this.getClass().getSimpleName().toUpperCase()))
                .withItem(OtherEnum.GROUP_START)
                .withItem(OtherEnum.GROUP_END)
                .build(null);
    }
}
