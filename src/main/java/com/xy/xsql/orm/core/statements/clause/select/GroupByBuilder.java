package com.xy.xsql.orm.core.statements.clause.select;

import com.xy.xsql.orm.core.statements.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.select.GroupBy;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class GroupByBuilder<Done>
        extends SubBuilder<GroupByBuilder<Done>,Void,Done> {

    private GroupBy groupBy;

    public GroupByBuilder(GroupBy groupBy) {
        this.groupBy = groupBy;
    }
}
