package com.xy.xsql.orm.core.sql.clause.select;

import com.xy.xsql.orm.core.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OrderByBuilder<Done>
        extends SubBuilder<OrderByBuilder<Done>,Void,Done> {

    private OrderBy orderBy;

    public OrderByBuilder(OrderBy orderBy) {
        this.orderBy = orderBy;
    }
}
