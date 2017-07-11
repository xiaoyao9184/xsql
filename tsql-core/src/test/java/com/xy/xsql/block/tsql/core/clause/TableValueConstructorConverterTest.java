package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilderTest;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TableValueConstructorConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = TableValueConstructorConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Table Value Constructor> ::=\n" +
                        "VALUES ( <row value expression list> ) [,...n]");
    }

    @Test
    public void testRowValueExpressionList() throws Exception {
        BlockMeta b = TableValueConstructorConverter.RowValueExpressionListConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<row value expression list> ::=\n" +
                        "{ <row value expression> } [,...n]");
    }

    @Test
    public void testRowValueExpression() throws Exception {
        BlockMeta b = TableValueConstructorConverter.RowValueExpressionConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<row value expression> ::=\n" +
                        "{ DEFAULT | NULL | expression }");
    }


    private Map<TableValueConstructor,String> model2StringMap;

    @Before
    public void init(){
        TableValueConstructorBuilderTest builderTest = new TableValueConstructorBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923'), (N'Y3', N'Cubic Yards', '20080923')");

        model2StringMap.put(
                builderTest.exampleB,
                "VALUES ('Recommendation','Other'), ('Advertisement', DEFAULT), (NULL, 'Promotion')");

        model2StringMap.put(
                builderTest.exampleC,
                "VALUES ('Blade'), ('Crown Race'), ('AWC Logo Cap')");

        model2StringMap.put(
                builderTest.exampleD,
                "VALUES ('Recommendation','Other'), ('Review', 'Marketing'), ('Internet', 'Promotion')");
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