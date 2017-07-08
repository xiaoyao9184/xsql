package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.OutputBuilder.*;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_negative;
import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.dml.MergeBuilder.MERGE;
import static com.xy.xsql.tsql.core.statement.dml.UpdateBuilder.SetItemBuilder.s;

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
        Merge merge = new MergeBuilder()
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

        Assert.assertEquals(merge.getTargetTable().toString(),"table");
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
        Merge merge = new MergeBuilder()
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

        Assert.assertEquals(merge.getTableAlias().toString(),"t");
    }


    // @formatter:off
    Select.QuerySpecification query = QUERY()
            .$(c("@UnitMeasureCode"))
            .$(c("@Name"))
            .build();

    //parent+quick
    public Merge exampleA = MERGE()
                .$(t("Production","UnitMeasure"))
                .$As("target")
                .$Using()
                    .$(query)
                    .$As("source","UnitMeasureCode","Name")
                .$On()
                    .$(p_equal(
                            c("target","UnitMeasureCode"),
                            c("source","UnitMeasureCode")
                    ))
                    .and()
                .$When_Matched()
                    .$Then()
                        .$Update_Set(
                                s(c("Name"),
                                        c("source","Name"))
                        )
                    .and()
                .$When_Not_Matched()
                    .$Then()
                        .$Insert(c("UnitMeasureCode"),c("Name"))
                        .$Values()
                            .$(
                                c("source","UnitMeasureCode"),
                                c("source","Name"))
                            .and()
                        .and()
                    .and()
                //OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable
                .$OutPut()
                    .$(c_deleted(),
                            c_$action(),
                            c_inserted())
                    .$Into("MyTempTable")
                    .and()
                .done();
    // @formatter:on

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
        Assert.assertEquals(exampleA.getTargetTable().toString(),"Production.UnitMeasure");
        Assert.assertEquals(exampleA.getTableAlias().toString(),"target");
        Assert.assertEquals(exampleA.getMatchedWhenThenList().size(),1);
        Assert.assertNotNull(exampleA.getNotMatchedWhenThenTarget());
    }


    // @formatter:off
    Select.QuerySpecification queryB = QUERY()
            .$(c("ProductID"))
            .$(c("SUM(OrderQty)"))
            .$From()
                .$()
                    .$(t("Sales","SalesOrderDetail"),"sod")
                    .$Join()
                    .$(t("Sales","SalesOrderHeader"),"soh")
                    .$On()
                        .$Predicate(p_equal(
                                c("sod","SalesOrderID"),
                                c("soh","SalesOrderID")))
                        .$_AndPredicate(p_equal(c("soh","OrderDate"),
                                e_variable("OrderDate")))
                        .and()
                    .and()
                .and()
            .$GroupBy()
                .$(c("ProductID"))
                .and()
            .build();
    //parent+quick
    public Merge exampleB = MERGE()
                .$(t("Production","ProductInventory"))
                .$As("target")
                .$Using()
                    .$(queryB)
                    .$As("source","ProductID","OrderQty")
                .$On()
                    .$(p_equal(
                            c("target","ProductID"),
                            c("source","ProductID")
                    ))
                    .and()
                .$When_Matched()
                    .$And()
                        .$Predicate(p_less_equal(
                                e_negative(c("target","Quantity"),
                                        c("source","OrderQty")),
                                e_number(0)
                        ))
                        .and()
                    .$Then()
                        .$Delete()
                    .and()
                .$When_Matched()
                    .$Then()
                        .$Update_Set(
                                s(c("target","Quantity"),
                                       e_negative(
                                    c("target","Quantity"),
                                    c("source","OrderQty"))),
                                s(c("target","ModifiedDate"),
                                       e("GETDATE()"))
                        )
                    .and()
                .$OutPut()
                    .$Output(c_$action())
                    .$Output(c_inserted("ProductID"),
                            c_inserted("Quantity"),
                            c_inserted("ModifiedDate"),
                            c_deleted("ProductID"),
                            c_deleted("Quantity"),
                            c_deleted("ModifiedDate"))
                    .and()
                .done();
    // @formatter:on

    /**
     * MERGE Production.ProductInventory AS target
     USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod
     JOIN Sales.SalesOrderHeader AS soh
     ON sod.SalesOrderID = soh.SalesOrderID
     AND soh.OrderDate = @OrderDate
     GROUP BY ProductID) AS source (ProductID, OrderQty)
     ON (target.ProductID = source.ProductID)
     WHEN MATCHED AND target.Quantity - source.OrderQty <= 0
     THEN DELETE
     WHEN MATCHED
     THEN UPDATE SET target.Quantity = target.Quantity - source.OrderQty,
     target.ModifiedDate = GETDATE()
     OUTPUT $action, Inserted.ProductID, Inserted.Quantity, Inserted.ModifiedDate, Deleted.ProductID,
     Deleted.Quantity, Deleted.ModifiedDate;
     */
    @Test
    public void testExampleB(){
        Assert.assertEquals(exampleB.getTargetTable().toString(),"Production.ProductInventory");
        Assert.assertEquals(exampleB.getTableAlias().toString(),"target");
        Assert.assertEquals(exampleB.getMatchedWhenThenList().size(),2);
        Assert.assertNull(exampleB.getNotMatchedWhenThenTarget());
    }

}
