package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.predicate.Exists;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * ExistsPredicateBuilder
 *
 * @see Exists
 * @param <ParentBuilder>
 */
public class ExistsPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ExistsPredicateBuilder<ParentBuilder>,ParentBuilder,Exists> {

    public ExistsPredicateBuilder() {
        super(new Exists());
    }

    public ExistsPredicateBuilder(Exists predicate) {
        super(predicate);
    }

    public ExistsPredicateBuilder<ParentBuilder> withSubQuery(Select subquery) {
        tar.setSubquery(subquery);
        return this;
    }

}
