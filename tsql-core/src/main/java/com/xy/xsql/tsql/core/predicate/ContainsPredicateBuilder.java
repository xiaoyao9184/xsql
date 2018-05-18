package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.Contains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

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


    public ContainsPredicateBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnName(columnName);
        return this;
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

    public ContainsPredicateBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames) {
        target.setColumnList(columnNames);
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

    public ContainsPredicateBuilder<ParentBuilder> withProperty(ColumnName columnName, String propertyName) {
        target.setPropertyColumnName(columnName);
        target.setPropertyName(e_string(propertyName));
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withProperty(ColumnName columnName, StringConstant propertyName) {
        target.setPropertyColumnName(columnName);
        target.setPropertyName(propertyName);
        return this;
    }

    public ContainsPredicateBuilder<ParentBuilder> withContainsSearchCondition(String containsSearchCondition) {
        target.setContainsSearchCondition(e_string(containsSearchCondition));
        return this;
    }
}