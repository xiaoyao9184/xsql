package com.xy.xsql.core.selecter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by xiaoyao9184 on 2017/9/26
 */
public interface PredicateSelector<Select,Result>
        extends BaseSelector<Select,Optional<Result>> {

    Map<Result,Predicate<Select>> predicates();

    default Optional<Result> select(Select select) {
        return predicates().entrySet()
                .stream()
                .filter(predicate -> predicate.getValue().test(select))
                .findAny()
                .map(Map.Entry::getKey);
    }
}
