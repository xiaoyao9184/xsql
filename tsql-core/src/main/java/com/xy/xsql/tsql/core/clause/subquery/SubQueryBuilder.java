package com.xy.xsql.tsql.core.clause.subquery;

import com.xy.xsql.core.builder.SubBuilder;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class SubQueryBuilder<Done>
        extends SubBuilder<SubQueryBuilder<Done>,Void,Done> {

    public SelectSubQueryBuilder<SubQueryBuilder<Done>> select() {
        return new SelectSubQueryBuilder<SubQueryBuilder<Done>>().in(this);
    }

    public TableValueConstructorBuilder<SubQueryBuilder<Done>> values() {
        return new TableValueConstructorBuilder<SubQueryBuilder<Done>>().in(this);
    }

    public SubQueryBuilder<Done> withAs(){

        return this;
    }
}
