package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.predicate.Contains;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * ContainsPredicateBuilder
 *
 * @see Contains
 * @param <ParentBuilder>
 */
public class ContainsPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<ContainsPredicateBuilder<ParentBuilder>,ParentBuilder,Contains> {

    public ContainsPredicateBuilder() {
        super(new Contains());
    }

    public ContainsPredicateBuilder(Contains predicate) {
        super(predicate);
    }

    public ContainsPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
        target.setColumnName(c(columnName));
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withColumn(String... columnNames) {
        target.setColumnList(
                Arrays.stream(columnNames)
                        .map(ColumnName::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withAllColumn() {
        target.setUseAllColumn(true);
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withProperty(String columnName, String propertyName) {
        target.setPropertyColumnName(c(columnName));
        target.setPropertyName(e_string(propertyName));
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withContainsSearchCondition(String containsSearchCondition) {
        target.setContainsSearchCondition(e_string(containsSearchCondition));
        return this;
    }
}