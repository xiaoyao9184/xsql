package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.core.ProxyObjectMethodRecording;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.Predicate;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface QueryChain<COLUMN> {


//    Recorder<T> recorder();

    ProxyObjectMethodRecording<COLUMN> recorder(Function<COLUMN, ?> p1);

    ColumnName column(Function<COLUMN, ?> p1);

//    String tableName();
    From.TableSource fromClause();
//    String fieldName(String fieldName);

//    Optional<Where> buildWhere();

    Stream<Predicate> wherePredicates();

    Stream<Object> whereValues();

    Stream<Map.Entry<Predicate,Object>> wherePredicatesWithJSql();

//    public int setPlaceholders(PreparedStatement statement) throws SQLException;
}
