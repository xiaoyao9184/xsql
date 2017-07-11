package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.MergeBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class MergeConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = MergeConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<MERGE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "MERGE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ INTO ]\n" +
                        "<target_table>\n" +
                        "[ WITH ( <merge_hint> ) ]\n" +
                        "[ as table_alias ]\n" +
                        "USING\n" +
                        "<table_source>\n" +
                        "ON\n" +
                        "<merge_search_condition>\n" +
                        "[ WHEN MATCHED [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_matched> ] [...n]\n" +
                        "[ WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_not_matched> ]\n" +
                        "[ WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_matched> ] [...n]\n" +
                        "[ <output_clause> ]\n" +
                        "[ OPTION ( <query_hint> [ ,...n ] ) ]");
    }

    @Test
    public void testMergeHint() throws Exception {
        BlockMeta b = MergeConverter.MergeHintConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<merge_hint> ::=\n" +
                        "{\n" +
                        "[ <table_hint_limited> ] [,...n]\n" +
                        "[ [ , ] INDEX ( index_val [,...n] ) ]\n" +
                        "}");
    }

    @Test
    public void testMatchedWhenThen() throws Exception {
        BlockMeta b = MergeConverter.MatchedWhenThenConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "WHEN MATCHED [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_matched>");
    }

    @Test
    public void testNotMatchedTargetWhenThen() throws Exception {
        BlockMeta b = MergeConverter.NotMatchedTargetWhenThenConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "WHEN NOT MATCHED [ BY TARGET ] [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_not_matched>");
    }

    @Test
    public void testNotMatchedSourceWhenThen() throws Exception {
        BlockMeta b = MergeConverter.NotMatchedSourceWhenThenConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "WHEN NOT MATCHED BY SOURCE [ AND <clause_search_condition> ] \n" +
                        "THEN <merge_matched>");
    }

    @Test
    public void testMergeMatched() throws Exception {
        BlockMeta b = MergeConverter.MergeMatchedConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<merge_matched> ::=\n" +
                        "{ UPDATE SET <set_clause> | DELETE }");
    }

    @Test
    public void testMergeNotMatched() throws Exception {
        BlockMeta b = MergeConverter.MergeNotMatchedConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<merge_not_matched> ::=\n" +
                        "{\n" +
                        "INSERT [ ( column_list ) ] \n" +
                        "{ VALUES ( values_list )\n" +
                        "| DEFAULT VALUES }\n" +
                        "}");
    }

    private Map<Merge,String> model2StringMap;

    @Before
    public void init(){
        MergeBuilderTest builderTest = new MergeBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        /*
          SearchCondition no ()
          Case ignored
         */
        model2StringMap.put(
                builderTest.exampleA,
                "MERGE Production.UnitMeasure AS target\n" +
                        "     USING (SELECT @UnitMeasureCode, @Name) AS source (UnitMeasureCode, Name)\n" +
                        "     ON target.UnitMeasureCode = source.UnitMeasureCode\n" +
                        "     WHEN MATCHED THEN\n" +
                        "     UPDATE SET Name = source.Name\n" +
                        "     WHEN NOT MATCHED THEN\n" +
                        "     INSERT (UnitMeasureCode, Name)\n" +
                        "     VALUES (source.UnitMeasureCode, source.Name)\n" +
                        "     OUTPUT DELETED.*, $action, INSERTED.* INTO @MyTempTable");
//                "MERGE Production.UnitMeasure AS target\n" +
//                        "     USING (SELECT @UnitMeasureCode, @Name) AS source (UnitMeasureCode, Name)\n" +
//                        "     ON (target.UnitMeasureCode = source.UnitMeasureCode)\n" +
//                        "     WHEN MATCHED THEN\n" +
//                        "     UPDATE SET Name = source.Name\n" +
//                        "     WHEN NOT MATCHED THEN\n" +
//                        "     INSERT (UnitMeasureCode, Name)\n" +
//                        "     VALUES (source.UnitMeasureCode, source.Name)\n" +
//                        "     OUTPUT deleted.*, $action, inserted.* INTO #MyTempTable");

        /*
          SearchCondition no ()
          Case ignored
         */
        model2StringMap.put(
                builderTest.exampleB,
                "MERGE Production.ProductInventory AS target\n" +
                        "     USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod\n" +
                        "     JOIN Sales.SalesOrderHeader AS soh\n" +
                        "     ON sod.SalesOrderID = soh.SalesOrderID\n" +
                        "     AND soh.OrderDate = @OrderDate\n" +
                        "     GROUP BY ProductID) AS source (ProductID, OrderQty)\n" +
                        "     ON target.ProductID = source.ProductID\n" +
                        "     WHEN MATCHED AND target.Quantity - source.OrderQty <= 0\n" +
                        "     THEN DELETE\n" +
                        "     WHEN MATCHED\n" +
                        "     THEN UPDATE SET target.Quantity = target.Quantity - source.OrderQty,\n" +
                        "     target.ModifiedDate = GETDATE()\n" +
                        "     OUTPUT $action, INSERTED.ProductID, INSERTED.Quantity,\n" +
                        "     INSERTED.ModifiedDate, DELETED.ProductID,\n" +
                        "     DELETED.Quantity, DELETED.ModifiedDate");
//                "MERGE Production.ProductInventory AS target\n" +
//                        "     USING (SELECT ProductID, SUM(OrderQty) FROM Sales.SalesOrderDetail AS sod\n" +
//                        "     JOIN Sales.SalesOrderHeader AS soh\n" +
//                        "     ON sod.SalesOrderID = soh.SalesOrderID\n" +
//                        "     AND soh.OrderDate = @OrderDate\n" +
//                        "     GROUP BY ProductID) AS source (ProductID, OrderQty)\n" +
//                        "     ON (target.ProductID = source.ProductID)\n" +
//                        "     WHEN MATCHED AND target.Quantity - source.OrderQty <= 0\n" +
//                        "     THEN DELETE\n" +
//                        "     WHEN MATCHED\n" +
//                        "     THEN UPDATE SET target.Quantity = target.Quantity - source.OrderQty,\n" +
//                        "     target.ModifiedDate = GETDATE()\n" +
//                        "     OUTPUT $action, Inserted.ProductID, Inserted.Quantity,\n" +
//                        "     Inserted.ModifiedDate, Deleted.ProductID,\n" +
//                        "     Deleted.Quantity, Deleted.ModifiedDate");

        /*
          SearchCondition no ()
          Case ignored
         */
        model2StringMap.put(
                builderTest.exampleC,
                "MERGE INTO Sales.SalesReason AS Target\n" +
                        "     USING (VALUES ('Recommendation','Other'), ('Review', 'Marketing'),\n" +
                        "     ('Internet', 'Promotion'))\n" +
                        "     AS Source (NewName, NewReasonType)\n" +
                        "     ON Target.Name = Source.NewName\n" +
                        "     WHEN MATCHED THEN\n" +
                        "     UPDATE SET ReasonType = Source.NewReasonType\n" +
                        "     WHEN NOT MATCHED BY TARGET THEN\n" +
                        "     INSERT (Name, ReasonType) VALUES (NewName, NewReasonType)\n" +
                        "     OUTPUT $action INTO @SummaryOfChanges");
//                "MERGE INTO Sales.SalesReason AS Target\n" +
//                        "     USING (VALUES ('Recommendation','Other'), ('Review', 'Marketing'),\n" +
//                        "     ('Internet', 'Promotion'))\n" +
//                        "     AS Source (NewName, NewReasonType)\n" +
//                        "     ON Target.Name = Source.NewName\n" +
//                        "     WHEN MATCHED THEN\n" +
//                        "     UPDATE SET ReasonType = Source.NewReasonType\n" +
//                        "     WHEN NOT MATCHED BY TARGET THEN\n" +
//                        "     INSERT (Name, ReasonType) VALUES (NewName, NewReasonType)\n" +
//                        "     OUTPUT $action INTO @SummaryOfChanges");
        /*
          Case ignored
         */
        model2StringMap.put(
                builderTest.exampleD,
                "MERGE Production.ProductInventory AS pi\n" +
                        "     USING (SELECT ProductID, SUM(OrderQty)\n" +
                        "     FROM Sales.SalesOrderDetail AS sod\n" +
                        "     JOIN Sales.SalesOrderHeader AS soh\n" +
                        "     ON sod.SalesOrderID = soh.SalesOrderID\n" +
                        "     AND soh.OrderDate BETWEEN '20030701' AND '20030731'\n" +
                        "     GROUP BY ProductID) AS src (ProductID, OrderQty)\n" +
                        "     ON pi.ProductID = src.ProductID\n" +
                        "     WHEN MATCHED AND pi.Quantity - src.OrderQty >= 0\n" +
                        "     THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty\n" +
                        "     WHEN MATCHED AND pi.Quantity - src.OrderQty <= 0\n" +
                        "     THEN DELETE\n" +
                        "     OUTPUT $action, INSERTED.ProductID, INSERTED.LocationID,\n" +
                        "     INSERTED.Quantity AS NewQty, DELETED.Quantity AS PreviousQty");
//                "MERGE Production.ProductInventory AS pi\n" +
//                        "     USING (SELECT ProductID, SUM(OrderQty)\n" +
//                        "     FROM Sales.SalesOrderDetail AS sod\n" +
//                        "     JOIN Sales.SalesOrderHeader AS soh\n" +
//                        "     ON sod.SalesOrderID = soh.SalesOrderID\n" +
//                        "     AND soh.OrderDate BETWEEN '20030701' AND '20030731'\n" +
//                        "     GROUP BY ProductID) AS src (ProductID, OrderQty)\n" +
//                        "     ON pi.ProductID = src.ProductID\n" +
//                        "     WHEN MATCHED AND pi.Quantity - src.OrderQty >= 0\n" +
//                        "     THEN UPDATE SET pi.Quantity = pi.Quantity - src.OrderQty\n" +
//                        "     WHEN MATCHED AND pi.Quantity - src.OrderQty <= 0\n" +
//                        "     THEN DELETE\n" +
//                        "     OUTPUT $action, Inserted.ProductID, Inserted.LocationID,\n" +
//                        "     Inserted.Quantity AS NewQty, Deleted.Quantity AS PreviousQty");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}