package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.predicates.Contains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_string;

/**
 * ContainsPredicateBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 * @param <ParentBuilder>
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class ContainsPredicateBuilder<ParentBuilder>
        extends ParentHoldBuilder<ContainsPredicateBuilder<ParentBuilder>,ParentBuilder,Contains> {

    public ContainsPredicateBuilder() {
        super(new Contains());
    }

    public ContainsPredicateBuilder(Contains target) {
        super(target);
    }


    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setColumnName(columnName);
        return this;
    }

    /**
     * set
     * @param columnName column name
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withColumnName(String columnName) {
        target.setColumnName(c(columnName));
        return this;
    }

    /**
     * set
     * @param columnNames column name
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withColumn(String... columnNames) {
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
    public ContainsPredicateBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames) {
        target.setColumnList(columnNames);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withAllColumn() {
        target.setUseAllColumn(true);
        return this;
    }

    /**
     * set
     * @param columnName column name
     * @param propertyName property name
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withProperty(String columnName, String propertyName) {
        target.setPropertyColumnName(c(columnName));
        target.setPropertyName(e_string(propertyName));
        return this;
    }

    /**
     * set
     * @param columnName ColumnName
     * @param propertyName property name
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withProperty(ColumnName columnName, String propertyName) {
        target.setPropertyColumnName(columnName);
        target.setPropertyName(e_string(propertyName));
        return this;
    }

    /**
     * set
     * @param columnName ColumnName
     * @param propertyName property name
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withProperty(ColumnName columnName, StringConstant propertyName) {
        target.setPropertyColumnName(columnName);
        target.setPropertyName(propertyName);
        return this;
    }

    /**
     * set
     * @param containsSearchCondition contains search condition
     * @return THIS
     */
    public ContainsPredicateBuilder<ParentBuilder> withContainsSearchCondition(String containsSearchCondition) {
        target.setContainsSearchCondition(e_string(containsSearchCondition));
        return this;
    }
}