package com.xy.xsql.block.tsql.core.variable;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.variable.SelectVariableBuilderTest;
import com.xy.xsql.tsql.model.variable.SelectVariable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SelectVariableConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = SelectVariableConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        assert true;
    }


    private Map<SelectVariable,String> model2StringMap;

    @Before
    public void init(){
        SelectVariableBuilderTest builderTest = new SelectVariableBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SELECT @var1 = 'Generic Name'");

        model2StringMap.put(
                builderTest.exampleB1,
                "SELECT @var1 = 'Generic Name'");

        model2StringMap.put(
                builderTest.exampleB2,
                "SELECT @var1 = (SELECT Name\n" +
                        "     FROM Sales.Store\n" +
                        "     WHERE CustomerID = 1000)");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.printMeta(key);
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