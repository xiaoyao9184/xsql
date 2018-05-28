package com.xy.xsql.tsql.style.constructor.introducer.statements;

import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.Update;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.tsql.style.constructor.introducer.queries.from;
import com.xy.xsql.tsql.style.constructor.introducer.queries.select.select;
import com.xy.xsql.tsql.style.constructor.introducer.queries.select_;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.FROM;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.JOIN;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.output.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.group_by.GROUP_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.values.VALUES;
import static com.xy.xsql.tsql.style.constructor.introducer.statements.merge.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.update.SET;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
public class mergeTest {


    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/statements/merge-transact-sql#examples
     */

    // @formatter:off
    private Select queryA = select_.$(select_.SELECT(
            select_.$(c("@UnitMeasureCode"))
            .$$(c("@Name"))
    )).build();

    //parent+quick
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
    public Merge exampleA = MERGE(
                t("Production","UnitMeasure")
                ,AS("target")
                ,USING(queryA,AS("source"),"UnitMeasureCode","Name")
                ,ON(p_equal(
                            c("target","UnitMeasureCode"),
                            c("source","UnitMeasureCode")
                    ))
                ,WHEN_MATCHED(THEN(UPATE(
                        SET(c("Name"),c("source","Name"))
                    )))
                ,WHEN_NOT_MATCHED(THEN(INSERT(
                        $(c("UnitMeasureCode"),c("Name"))
                        ,VALUES(c("UnitMeasureCode"),c("Name"))
                    )))
                ,OUTPUT(
                        DELETED()
                        .$action()
                        .INSERTED()
                        ,INTO("MyTempTable"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTargetTable().toString(),"Production.UnitMeasure");
        assertEquals(exampleA.getTableAlias().toString(),"target");
        assertEquals(exampleA.getMatchedWhenThenList().size(),1);

        assertEquals(exampleA.getMatchedWhenThenList().get(0).getMergeMatched().getSets().size(),1);
        assertTrue(exampleA.getMatchedWhenThenList().get(0).getMergeMatched().isUseSet());
        Update.SetItem setItem = exampleA.getMatchedWhenThenList().get(0).getMergeMatched().getSets().get(0);
        assertEquals(setItem.getClass(),Update.ColumnAssignmentSet.class);

        assertNotNull(exampleA.getNotMatchedWhenThenTarget());
        assertEquals(exampleA.getNotMatchedWhenThenTarget().getMergeNotMatched().getColumns().size(),2);
        assertEquals(exampleA.getNotMatchedWhenThenTarget().getMergeNotMatched().getValues().getRowValueExpressionListGroup().size(),1);
        assertEquals(exampleA.getNotMatchedWhenThenTarget().getMergeNotMatched().getValues().getRowValueExpressionListGroup().get(0).size(),2);
    }


    // @formatter:off
    private Select queryB = select_.$(select_.SELECT(
            select_.$(c("ProductID"))
            .$$(c("SUM(OrderQty)"))
            ,FROM(t("Sales","SalesOrderDetail"),AS("sod"))
                    .$(JOIN(t("Sales","SalesOrderHeader"),AS("soh"))
                            ,from.ON(p_equal(
                                c("sod","SalesOrderID"),
                                c("soh","SalesOrderID")
                            )).AND(p_equal(c("soh","OrderDate"),
                                    e_variable("OrderDate")
                            ))
                    )
            ,GROUP_BY(c("ProductID"))
    )).build();

    //parent+quick
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
     OUTPUT $action, Inserted.ProductID, Inserted.Quantity,
     Inserted.ModifiedDate, Deleted.ProductID,
     Deleted.Quantity, Deleted.ModifiedDate
     */
    public Merge exampleB = MERGE(
            t("Production","ProductInventory")
            ,AS("target")
            ,USING(queryB,AS("source"),"ProductID","OrderQty")
            ,ON(p_equal(
                        c("target","ProductID"),
                        c("source","ProductID")
                ))
            ,WHEN_MATCHED(AND(p_less_equal(
                            e_subtraction(c("target","Quantity"),
                                    c("source","OrderQty")),
                            e_number(0)
                            ))
                        ,THEN(DELETE())
            ).WHEN_MATCHED(THEN(
                    UPATE(SET(
                            c("target","Quantity"),
                            e_subtraction(
                                    c("target","Quantity"),
                                    c("source","OrderQty"))
                    ).$$(
                            c("target","ModifiedDate"),
                            e("GETDATE()")
                    ))
            ))
            ,OUTPUT(
                    $action()
                    .INSERTED("ProductID")
                    .INSERTED("Quantity")
                    .INSERTED("ModifiedDate")
                    .INSERTED("ProductID")
                    .INSERTED("Quantity")
                    .INSERTED("ModifiedDate")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTargetTable().toString(),"Production.ProductInventory");
        assertEquals(exampleB.getTableAlias().toString(),"target");
        assertEquals(exampleB.getMatchedWhenThenList().size(),2);
        assertNull(exampleB.getNotMatchedWhenThenTarget());
    }


    // @formatter:off
    private TableValueConstructor valueC = VALUES()
            .$(e_string("Recommendation"),e_string("Other"))
            .$(e_string("Review"),e_string("Marketing"))
            .$(e_string("Internet"),e_string("Promotion"))
            .build();

    //parent+quick
    /**
     * MERGE INTO Sales.SalesReason AS Target
     USING (VALUES ('Recommendation','Other'), ('Review', 'Marketing'),
     ('Internet', 'Promotion'))
     AS Source (NewName, NewReasonType)
     ON Target.Name = Source.NewName
     WHEN MATCHED THEN
     UPDATE SET ReasonType = Source.NewReasonType
     WHEN NOT MATCHED BY TARGET THEN
     INSERT (Name, ReasonType) VALUES (NewName, NewReasonType)
     OUTPUT $action INTO @SummaryOfChanges
     */
    public Merge exampleC = MERGE(
            INTO()
            ,t("Sales","SalesReason")
            ,AS("Target")
            ,USING(VALUES(
                    e_string("Recommendation"),e_string("Other"))
                    .$$(e_string("Review"),e_string("Marketing"))
                    .$$(e_string("Internet"),e_string("Promotion"))
                    ,AS("Source"),
                    "NewName","NewReasonType")
            ,ON(p_equal(
                        c("Target","Name"),
                        c("Source","NewName")
            ))
            ,WHEN_MATCHED(THEN(
                    UPATE(SET(
                            c("ReasonType"),
                            c("Source","NewReasonType")
                    ))
            ))
            ,WHEN_NOT_MATCHED_BY_TARGET(THEN(
                    INSERT(
                            $(c("Name"),c("ReasonType"))
                            ,VALUES(c("NewName"),c("NewReasonType"))
                    )
            ))
            ,OUTPUT(
                    $action()
                    ,INTO("SummaryOfChanges")
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getTargetTable().toString(),"Sales.SalesReason");
        assertEquals(exampleC.getTableAlias().toString(),"Target");
        assertEquals(exampleC.getMatchedWhenThenList().size(),1);
        assertNotNull(exampleC.getNotMatchedWhenThenTarget());
    }


    // @formatter:off
    private Select queryD = select_.$(select_.SELECT(
            select_.$(c("ProductID"))
            .$$(c("SUM(OrderQty)"))
            ,FROM(t("Sales","SalesOrderDetail"),AS("sod"))
                    .$(JOIN(
                            t("Sales","SalesOrderHeader"),AS("soh")
                    ),from.ON(
                            p_equal(
                                c("sod","SalesOrderID"),
                                c("soh","SalesOrderID")
                    )).AND(
                            p_between(c("soh","OrderDate"),
                                e_string("20030701"),
                                e_string("20030731"))
                    ))
            ,GROUP_BY(c("ProductID")))
    ).build();

    //parent+quick
    /**
     * MERGE Production.ProductInventory AS pi
     USING (SELECT ProductID, SUM(OrderQty)
     FROM Sales.SalesOrderDetail AS sod
     JOIN Sales.SalesOrderHeader AS soh
     ON sod.SalesOrderID = soh.SalesOrderID
     AND soh.OrderDate BETWEEN '20030701' AND '20030731'
     GROUP BY ProductID) AS src (ProductID, OrderQty)
     ON pi.ProductID = src.ProductID
     WHEN MATCHED AND pi.Quantity - src.OrderQty >= 0
     THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty
     WHEN MATCHED AND pi.Quantity - src.OrderQty <= 0
     THEN DELETE
     OUTPUT $action, Inserted.ProductID, Inserted.LocationID,
     Inserted.Quantity AS NewQty, Deleted.Quantity AS PreviousQty
     */
    public Merge exampleD = MERGE(
            t("Production","ProductInventory")
            ,AS("pi")
            ,USING(queryD,AS("src"),"ProductID","OrderQty")
            ,ON(p_equal(
                        c("pi","ProductID"),
                        c("src","ProductID")
                ))
            ,WHEN_MATCHED(AND(
                    p_greater_equal(
                            e_subtraction(
                                    c("pi","Quantity"),
                                    c("src","OrderQty")),
                            e_number(0)
                    )
            ),THEN(UPATE(SET(
                    c("pi","Quantity"),
                    e_subtraction(
                            c("pi","Quantity"),
                            c("src","OrderQty"))
                    )
            )))
            .WHEN_MATCHED(AND(p_less_equal(
                            e_subtraction(
                                    c("pi","Quantity"),
                                    c("src","OrderQty")),
                            e_number(0)
                    ))
                    ,THEN(DELETE())
            )
            ,OUTPUT(
                    $action()
                    .INSERTED("ProductID")
                    .INSERTED("LocationID")
                    .INSERTED("Quantity",AS("NewQty"))
                    .DELETED("Quantity",AS("PreviousQty"))
            )
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getTargetTable().toString(),"Production.ProductInventory");
        assertEquals(exampleD.getTableAlias().toString(),"pi");
        assertEquals(exampleD.getMatchedWhenThenList().size(),2);
        assertNull(exampleD.getNotMatchedWhenThenTarget());
    }
}