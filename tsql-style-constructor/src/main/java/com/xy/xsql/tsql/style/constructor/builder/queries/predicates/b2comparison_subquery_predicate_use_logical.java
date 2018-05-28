package com.xy.xsql.tsql.style.constructor.builder.queries.predicates;

import com.xy.xsql.core.builder.SimpleBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public interface b2comparison_subquery_predicate_use_logical
        extends SimpleBuilder<ComparisonSubQuery> {

    b2comparison_subquery_predicate_use_logical $equal(ALL subquery);
    b2comparison_subquery_predicate_use_logical $equal(SOME subquery);
    b2comparison_subquery_predicate_use_logical $equal(ANY subquery);

    b2comparison_subquery_predicate_use_logical $not_equal(ALL subquery);
    b2comparison_subquery_predicate_use_logical $not_equal(SOME subquery);
    b2comparison_subquery_predicate_use_logical $not_equal(ANY subquery);

    b2comparison_subquery_predicate_use_logical $not_equal_not_iso(ALL subquery);
    b2comparison_subquery_predicate_use_logical $not_equal_not_iso(SOME subquery);
    b2comparison_subquery_predicate_use_logical $not_equal_not_iso(ANY subquery);

    b2comparison_subquery_predicate_use_logical $greater(ALL subquery);
    b2comparison_subquery_predicate_use_logical $greater(SOME subquery);
    b2comparison_subquery_predicate_use_logical $greater(ANY subquery);

    b2comparison_subquery_predicate_use_logical $greater_equal(ALL subquery);
    b2comparison_subquery_predicate_use_logical $greater_equal(SOME subquery);
    b2comparison_subquery_predicate_use_logical $greater_equal(ANY subquery);

    b2comparison_subquery_predicate_use_logical $not_greater_equal_not_iso(ALL subquery);
    b2comparison_subquery_predicate_use_logical $not_greater_equal_not_iso(SOME subquery);
    b2comparison_subquery_predicate_use_logical $not_greater_equal_not_iso(ANY subquery);

    b2comparison_subquery_predicate_use_logical $less(ALL subquery);
    b2comparison_subquery_predicate_use_logical $less(SOME subquery);
    b2comparison_subquery_predicate_use_logical $less(ANY subquery);

    b2comparison_subquery_predicate_use_logical $less_equal(ALL subquery);
    b2comparison_subquery_predicate_use_logical $less_equal(SOME subquery);
    b2comparison_subquery_predicate_use_logical $less_equal(ANY subquery);

    b2comparison_subquery_predicate_use_logical $not_less_not_iso(ALL subquery);
    b2comparison_subquery_predicate_use_logical $not_less_not_iso(SOME subquery);
    b2comparison_subquery_predicate_use_logical $not_less_not_iso(ANY subquery);


    class ALL extends SelectBuilder {}
    class SOME extends SelectBuilder {}
    class ANY extends SelectBuilder {}

}
