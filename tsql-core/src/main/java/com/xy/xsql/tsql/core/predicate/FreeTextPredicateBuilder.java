package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.predicate.FreeText;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

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
        tar.setColumnName(c(columnName));
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withColumn(String... columnNames) {
        tar.setColumnList(
                Arrays.stream(columnNames)
                        .map(ColumnName::new)
                        .collect(Collectors.toList()));
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withAllColumn() {
        tar.setUseAllColumn(true);
        return this;
    }

    public FreeTextPredicateBuilder<ParentBuilder> withFreeText(String freetextString) {
        tar.setFreetextString(e_string(freetextString));
        return this;
    }

}
