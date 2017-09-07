package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.ddl.JoinTables;

import java.io.Serializable;
import java.util.function.Function;

public interface RelationshipJoinSpecifier<T,U> {

    <V extends Serializable> CanOnlyJoin<U> using(Function<T, V> p1);
    <V extends Serializable, W extends Serializable> CanOnlyJoin<U> using(Function<T, V> p1, Function<T, W> p2);

    interface CanOnlyJoin<V>  {
        <W> JoinSpecifier<V,W> join(Class<W> table);
        <W> RelationshipJoinSpecifier<V,W> join(JoinTables<V, W> table);
    }

}
