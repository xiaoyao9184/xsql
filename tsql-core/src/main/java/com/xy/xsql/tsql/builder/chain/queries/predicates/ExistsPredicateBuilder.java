package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.predicates.Exists;
import com.xy.xsql.tsql.model.queries.Select;

/**
 * ExistsPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class ExistsPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ExistsPredicateBuilder<ParentBuilder>,ParentBuilder,Exists> {

    public ExistsPredicateBuilder() {
        super(new Exists());
    }

    public ExistsPredicateBuilder(Exists predicate) {
        super(predicate);
    }

    /**
     * set
     * @param subQuery Select
     * @return THIS
     */
    public ExistsPredicateBuilder<ParentBuilder> withSubQuery(Select subQuery) {
        target.setSubquery(subQuery);
        return this;
    }

}
