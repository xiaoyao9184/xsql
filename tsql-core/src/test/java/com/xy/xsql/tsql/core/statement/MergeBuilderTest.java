package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.tsql.core.clause.OutputBuilder;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_$action;
import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_deleted;
import static com.xy.xsql.tsql.core.clause.OutputBuilder.c_inserted;
import static com.xy.xsql.tsql.core.element.ColumnNameBuilder.c;
import static com.xy.xsql.tsql.core.element.TableNameBuilder.t;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.predicate.PredicateBuilder.p_equal;
import static com.xy.xsql.tsql.core.statement.MergeBuilder.MERGE;

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
                .withTargetTable("table")
                .withTableSource()._Base()
                    .withTableName(t("table2"))
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
                .withWhenMatchedThen()
                    .withMergeMatched()
                        .withSet(true)
                        .withSetItem()._ColumnAssignment()
                            .withColumnName(c("c1"))
                            .and()
                        .and()
                    .and()
                .withWhenNotMatchedTargetThen()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .and()
                    .and()
                .withWhenNotMatchedSourceThen()
                    .and()
                .build();

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
                .withTargetTable("table")
                .withAs(true)
                .withTableAlias("t")
                .withTableSource()._Base()
                    .withTableName(t("table2"))
                    .and()
                .withWhenMatchedThen()
                    .withMergeMatched()
                        .withSet(true)
                        .withSetItem()._ColumnAssignment()
                            .withColumnName(c("c1"))
                            .and()
                        .and()
                    .and()
                .withWhenNotMatchedTargetThen()
                    .withByTarget(true)
                    .withMergeNotMatched()
                        .withColumn(c("c1"))
                        .and()
                    .and()
                .withWhenNotMatchedSourceThen()
                    .withClauseSearchCondition()
                        .and()
                    .and()
                .build();

        Assert.assertEquals(delete.getTableAlias().toString(),"t");
    }
//
//
//
//
//    /**
//     * MERGE Production.UnitMeasure AS target
//     USING (SELECT @UnitMeasureCode, @Name) AS source (UnitMeasureCode, Name)
//     ON (target.UnitMeasureCode = source.UnitMeasureCode)
//     WHEN MATCHED THEN
//     UPDATE SET Name = source.Name
//     WHEN NOT MATCHED THEN
//     INSERT (UnitMeasureCode, Name)
//     VALUES (source.UnitMeasureCode, source.Name)
//     OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable
//     */
//    @Test
//    public void testExampleA(){
//        // @formatter:off
//        Merge merge = MERGE()
//                .$Into(t("Production","UnitMeasure"))
//                .$As("target")
//                .$Using()
//                    .withDerivedTable()
//                //  todo
//                        .out()
//                    .withTableAlias("source")
//                    //TODO
//                    .and()
//                .$On()
//                    .$(p_equal(
//                            c("target","UnitMeasureCode"),
//                            c("source","UnitMeasureCode")
//                    ))
//                    .and()
//                .$When_Matched()
//                    .withMergeMatched()
//                        .withSetItem()
//                            .withColumnName("Name")
//                            .withExpression(c("source","Name"))
//                            .and()
//                        .and()
//                    .and()
//                .$When_Not_Matched()
//                    .withMergeNotMatched()
//                        .withColumn(c("UnitMeasureCode"),c("Name"))
//                        .withValues()
//                            .$(
//                                e_rv("source","UnitMeasureCode"),
//                                e_rv("source","Name"))
//                            .and()
//                        .and()
//                    .and()
//                //OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable
//                .$OutPut()
//                    .$(c_deleted(),
//                            c_$action(),
//                            c_inserted())
//                    .$Into("MyTempTable")
//                .done();
//
//
//        // @formatter:on
//
////        Assert.assertEquals(reName.getDbName(),"AdWorks");
////        Assert.assertEquals(reName.getNewName(),"AdWorks2");
//    }
//
//    /**
//     * MERGE Production.ProductInventory AS target
//     USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod
//     JOIN Sales.SalesOrderHeader AS soh
//     ON sod.SalesOrderID = soh.SalesOrderID
//     AND soh.OrderDate = @OrderDate
//     GROUP BY ProductID) AS source (ProductID, OrderQty)
//     ON (target.ProductID = source.ProductID)
//     WHEN MATCHED AND target.Quantity - source.OrderQty <= 0
//     THEN DELETE
//     WHEN MATCHED
//     THEN UPDATE SET target.Quantity = target.Quantity - source.OrderQty,
//     target.ModifiedDate = GETDATE()
//     OUTPUT $action, Inserted.ProductID, Inserted.Quantity, Inserted.ModifiedDate, Deleted.ProductID,
//     Deleted.Quantity, Deleted.ModifiedDate;
//     */
//    @Test
//    public void testExampleB(){
//        // @formatter:off
//        Merge merge = MERGE()
//                .$Into(t("Production","ProductInventory"))
//                .$As("target")
//                .$Using()
//                    .withDerivedTable()
//                //  todo
//                        .out()
//                    .withTableAlias("source")
//                    //TODO
//                    .and()
//                .$On()
//                    .$(p_equal(
//                            c("target","ProductID"),
//                            c("source","ProductID")
//                    ))
//                    .and()
//                .$When_Matched()
//                    .withMergeMatched()
//                        .withSetItem()
//                            .withColumnName("Name")
//                            .withExpression(c("source","Name"))
//                            .and()
//                        .and()
//                    .and()
//                .$When_Not_Matched()
//                    .withMergeNotMatched()
//                        .withColumn(c("UnitMeasureCode"),c("Name"))
//                        .withValues()
//                            .$(
//                                e_rv("source","UnitMeasureCode"),
//                                e_rv("source","Name"))
//                            .and()
//                        .and()
//                    .and()
//                //OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable
//                .$OutPut()
//                    .$(c_deleted(),
//                            c_$action(),
//                            c_inserted())
//                    .$Into("MyTempTable")
//                .done();
//
//
//        // @formatter:on
//
////        Assert.assertEquals(reName.getDbName(),"AdWorks");
////        Assert.assertEquals(reName.getNewName(),"AdWorks2");
//    }

}
