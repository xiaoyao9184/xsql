package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.hint.TableHintBuilderTest;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class TableHintConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TableHintConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_hint> ::=\n" +
                        "[ NOEXPAND ]\n" +
                        "{\n" +
                        "\tINDEX ( index_value [,...n] )\n" +
                        "\t| INDEX = ( index_value )\n" +
                        "\t| FORCESEEK [ ( index_value ( index_column_name [,...n] ) ) ]\n" +
                        "\t| FORCESCAN\n" +
                        "\t| FORCESEEK\n" +
                        "\t| HOLDLOCK\n" +
                        "\t| NOLOCK\n" +
                        "\t| NOWAIT\n" +
                        "\t| PAGLOCK\n" +
                        "\t| READCOMMITTED\n" +
                        "\t| READCOMMITTEDLOCK\n" +
                        "\t| READPAST\n" +
                        "\t| READUNCOMMITTED\n" +
                        "\t| REPEATABLEREAD\n" +
                        "\t| ROWLOCK\n" +
                        "\t| SERIALIZABLE\n" +
                        "\t| SNAPSHOT\n" +
                        "\t| SNAPSHOT\n" +
                        "\t| SPATIAL_WINDOW_MAX_CELLS = integer\n" +
                        "\t| TABLOCK\n" +
                        "\t| TABLOCKX\n" +
                        "\t| UPDLOCK\n" +
                        "\t| XLOCK\n" +
                        "}");
    }

    private Map<TableHint,String> model2StringMap;

    @Before
    public void init(){
        TableHintBuilderTest builderTest = new TableHintBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TABLOCK");

        model2StringMap.put(
                builderTest.exampleB1,
                "FORCESEEK");

        model2StringMap.put(
                builderTest.exampleB2,
                "FORCESEEK (PK_SalesOrderDetail_SalesOrderID_SalesOrderDetailID (SalesOrderID))");

        model2StringMap.put(
                builderTest.exampleC,
                "FORCESCAN");

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

            check = check
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