package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.expression.Expressions.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 *
 * FreeTextPredicateBuilder
 *
 * @see FreeText
 * @param <ParentBuilder>
 */
public class FreeTextPredicateBuilder<ParentBuilder>
        extends CodeTreeBuilder<FreeTextPredicateBuilder<ParentBuilder>,ParentBuilder,FreeText> {

    public FreeTextPredicateBuilder() {
        super(new FreeText());
    }

    public FreeTextPredicateBuilder(FreeText predicate) {
        super(predicate);
    }

    public FreeTextPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
        target.setColumnName(c(columnName));
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnName(columnName);
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withColumn(String... columnNames) {
        target.setColumnList(
                Arrays.stream(columnNames)
                        .map(ColumnName::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames) {
        target.setColumnList(columnNames);
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withAllColumn() {
        target.setUseAllColumn(true);
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withFreeText(String freetextString) {
        target.setFreetextString(e_string(freetextString));
        return this;
    }

}
