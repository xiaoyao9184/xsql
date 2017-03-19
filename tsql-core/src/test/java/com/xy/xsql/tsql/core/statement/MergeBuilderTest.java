package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.ddl.rename.ReName;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.statement.MergeBuilder.MERGE;
import static com.xy.xsql.tsql.core.statement.ddl.ReNameBuilder.RENAME_DATABASE;

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
                    .and()
                .withMergeSearchCondition()
                    .withPredicate()
                        ._Comparison()
                        .withExpression(e_number(1))
                        .withOperator(Operators.EQUAL)
                        .withExpression(e_number(1))
                        .and()
                    .withAndOrNotItem()
                        .withAnd()
                        .withPredicate()
                            ._Comparison()
                            .withExpression(e_number(1))
                            .withOperator(Operators.EQUAL)
                            .withExpression(e_number(1))
                            .and()
                        .and()
                    .and()
                .withMatchedWhenThen()
                    .withMergeMatched()
                        .withSet(true)
                        .withSetItem()
                            .withColumnName("c1")
                            .and()
                        .and()
                    .and()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .and()
                    .and()
                .withNotMatchedWhenThenSource()
                    .and()
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
                    .and()
                .withMatchedWhenThen()
                    .withMergeMatched()
                        .withSet(true)
                        .withSetItem()
                            .withColumnName("c1")
                            .and()
                        .and()
                    .and()
                .withNotMatchedWhenThenTarget()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .and()
                    .and()
                .withNotMatchedWhenThenSource()
                    .withClauseSearchCondition()
                        .and()
                    .and()
                .build(null);

        Assert.assertEquals(delete.getTableAlias().toString(),"t");
    }




    /**
     * MERGE Production.UnitMeasure AS target
     USING (SELECT @UnitMeasureCode, @Name) AS source (UnitMeasureCode, Name)
     ON (target.UnitMeasureCode = source.UnitMeasureCode)
     WHEN MATCHED THEN
     UPDATE SET Name = source.Name
     WHEN NOT MATCHED THEN
     INSERT (UnitMeasureCode, Name)
     VALUES (source.UnitMeasureCode, source.Name)
     OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable
     */
    @Test
    public void testExampleA(){
//        // @formatter:off
//        Merge merge = MERGE()
//                .$Into(t("Production","UnitMeasure"))
//                .$As("target")
//                .$Using()
//                .$On()
//
//        // @formatter:on
//
//        Assert.assertEquals(reName.getDbName(),"AdWorks");
//        Assert.assertEquals(reName.getNewName(),"AdWorks2");
    }

}
