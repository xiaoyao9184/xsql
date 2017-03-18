package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;

/**
 * Created by xiaoyao9184 on 2017/1/11.
 */
public class MergeBuilderTest {

    /**
     * MERGE table USING table2
     * ON <merge_search_condition>
     * WHEN MATCHED
     * THEN <merge_matched>
     * WHEN NOT MATCHED
     * THEN <merge_not_matched>
     * WHEN NOT MATCHED BY SOURCE
     * THEN <merge_matched>
     */
    @Test
    public void testBaseBuild(){
        Merge delete = new MergeBuilder()
                .withTableName("table")
                .withTableSource()
                    .withTableName("table2")
                    .out()
                .withMergeSearchCondition()
                    .withPredicate()
                        .Comparison()
                        .withExpression(e_number(1))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_number(1))
                        .out()
                    .withAndOrNotItem()
                        .withAnd()
                        .withPredicate()
                            .Comparison()
                            .withExpression(e_number(1))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_number(1))
                            .out()
                        .out()
                    .out()
                .withMatchedWhenThenList()
                    .withItem()
                        .withMergeMatched()
                            .withSet(true)
                            .withSetList()
                                .withItem()
                                    .withColumnName("c1")
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .out()
                    .out()
                .withNotMatchedWhenThenSourceList()
                    .withItem()
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(delete.getTargetTable().toString(),"table");
    }
    /**
     * MERGE INTO table AS t USING table2
     * ON <merge_search_condition>
     * WHEN MATCHED
     * THEN <merge_matched>
     * WHEN NOT MATCHED
     * THEN <merge_not_matched>
     * WHEN NOT MATCHED BY SOURCE
     * THEN <merge_matched>
     */
    @Test
    public void testClauseSearchConditionBuild(){
        Merge delete = new MergeBuilder()
                .withInto(true)
                .withTableName("table")
                .withAs(true)
                .withTableAlias("t")
                .withTableSource()
                    .withTableName("table2")
                    .out()
                .withMatchedWhenThenList()
                    .withItem()
                        .withMergeMatched()
                            .withSet(true)
                            .withSetList()
                                .withItem()
                                    .withColumnName("c1")
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .out()
                    .out()
                .withNotMatchedWhenThenSourceList()
                    .withItem()
                        .out()
                    .out()
                .build(null);

        Assert.assertEquals(delete.getTableAlias().toString(),"t");
    }

}
