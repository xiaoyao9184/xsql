package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;

/**
 * FreeTextPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings("WeakerAccess")
public class FreeTextPredicateBuilder<ParentBuilder>
        extends ParentHoldBuilder<FreeTextPredicateBuilder<ParentBuilder>,ParentBuilder,FreeText> {

    public FreeTextPredicateBuilder() {
        super(new FreeText());
    }

    public FreeTextPredicateBuilder(FreeText target) {
        super(target);
    }

    /**
     * set
     * @param columnName column name
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
        target.setColumnName(c(columnName));
        return this;
    }

    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnName(columnName);
        return this;
    }

    /**
     * set
     * @param columnNames column name
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withColumn(String... columnNames) {
        target.setColumnList(
                Arrays.stream(columnNames)
                        .map(ColumnName::new)
                        .collect(Collectors.toList()));
        return this;
    }

    /**
     * set
     * @param columnNames ColumnName
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames) {
        target.setColumnList(columnNames);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withAllColumn() {
        target.setUseAllColumn(true);
        return this;
    }

    /**
     * set
     * @param freetextString freetext string
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withFreeText(String freetextString) {
        target.setFreetextString(e_string(freetextString));
        return this;
    }

    /**
     * set
     * @param freetextString freetext string
     * @return THIS
     */
    public FreeTextPredicateBuilder<ParentBuilder> withFreeText(StringConstant freetextString) {
        target.setFreetextString(freetextString);
        return this;
    }

}
