package com.xy.xsql.orm.core.statements.clause;

import com.xy.xsql.orm.core.statements.SubBuilder;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.info.Table;

import java.util.ArrayList;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WhereBuilder<Done>
        extends SubBuilder<WhereBuilder<Done>,Void,Done> {

    private Where where;

    public WhereBuilder(Where where) {
        this.where = where;
    }
}
