package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.OutputBuilderTest;
import com.xy.xsql.tsql.model.clause.Output;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OutputConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OutputConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OUTPUT Clause> ::=\n" +
                        "[ OUTPUT <dml_select_list> INTO { @table_variable | output_table } [ ( column_list ) ] ]\n" +
                        "[ OUTPUT <dml_select_list> ]");
    }

    @Test
    public void testMetaPrint_DmlSelectList() throws Exception {
        BlockMeta b = OutputConverter.DmlSelectListConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<dml_select_list> ::=\n" +
                        "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ] [,...n]");
    }

    @Test
    public void testMetaPrint_DmlSelect() throws Exception {
        BlockMeta b = OutputConverter.DmlSelectConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ]");
    }

    @Test
    public void testMetaPrint_ColumnName() throws Exception {
        BlockMeta b = OutputConverter.ColumnNameConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_name> ::=\n" +
                        "{ DELETED | INSERTED | from_table_name } . { * | column_name }\n" +
                        "| $action");
    }


    private Map<Output,String> model2StringMap;

    @Before
    public void init(){
        OutputBuilderTest builderTest = new OutputBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate\n" +
                        "     INTO @MyTableVar");

        model2StringMap.put(
                builderTest.exampleB,
                "OUTPUT DELETED.*");

        model2StringMap.put(
                builderTest.exampleC,
                "OUTPUT INSERTED.BusinessEntityID,\n" +
                        "     DELETED.VacationHours,\n" +
                        "     INSERTED.VacationHours,\n" +
                        "     INSERTED.ModifiedDate");

        model2StringMap.put(
                builderTest.exampleD,
                "OUTPUT INSERTED.BusinessEntityID,\n" +
                        "     DELETED.VacationHours,\n" +
                        "     INSERTED.VacationHours,\n" +
                        "     INSERTED.VacationHours - DELETED.VacationHours,\n" +
                        "     INSERTED.ModifiedDate");

        model2StringMap.put(
                builderTest.exampleE,
                "OUTPUT DELETED.ScrapReasonID,\n" +
                        "     INSERTED.ScrapReasonID,\n" +
                        "     INSERTED.WorkOrderID,\n" +
                        "     INSERTED.ProductID,\n" +
                        "     p.Name\n" +
                        "     INTO @MyTestVar");

        model2StringMap.put(
                builderTest.exampleF,
                "OUTPUT DELETED.ProductID,\n" +
                        "     p.Name,\n" +
                        "     p.ProductModelID,\n" +
                        "     DELETED.ProductPhotoID\n" +
                        "     INTO @MyTableVar");

        model2StringMap.put(
                builderTest.exampleG,
                "OUTPUT DELETED.DocumentSummary,\n" +
                        "     INSERTED.DocumentSummary\n" +
                        "     INTO @MyTableVar");

        model2StringMap.put(
                builderTest.exampleH,
                "OUTPUT INSERTED.ScrapReasonID, INSERTED.Name,\n" +
                        "     INSERTED.ModifiedDate");

        model2StringMap.put(
                builderTest.exampleI,
                "OUTPUT INSERTED.LastName,\n" +
                        "     INSERTED.FirstName,\n" +
                        "     INSERTED.CurrentSales");

        model2StringMap.put(
                builderTest.exampleJ,
                "OUTPUT DELETED.ProductID,\n" +
                        "     p.Name,\n" +
                        "     p.ProductModelID,\n" +
                        "     DELETED.ProductPhotoID\n" +
                        "     INTO @MyTableVar\n" +
                        "     OUTPUT DELETED.ProductID, DELETED.ProductPhotoID, GETDATE() DeletedDate");

        model2StringMap.put(
                builderTest.exampleK,
                "OUTPUT $action, DELETED.ProductID");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ModelMetaBlockPrinter.print(key);
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

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);
            System.out.println("==========");

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\t", "")
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