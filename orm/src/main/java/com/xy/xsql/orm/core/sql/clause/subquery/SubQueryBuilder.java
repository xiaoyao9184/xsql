package com.xy.xsql.orm.core.sql.clause.subquery;

import com.xy.xsql.orm.core.SubBuilder;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class SubQueryBuilder<Done>
        extends SubBuilder<SubQueryBuilder<Done>,Void,Done> {

    public SelectSubQueryBuilder<SubQueryBuilder<Done>> select() {
        return new SelectSubQueryBuilder<SubQueryBuilder<Done>>().in(this);
    }

    public ValuesSubQueryBuilder<SubQueryBuilder<Done>> values() {
        return new ValuesSubQueryBuilder<SubQueryBuilder<Done>>().in(this);
    }

    public SubQueryBuilder<Done> withAs(){

        return this;
    }
}
