package com.xy.xsql.tsql.converter.elements.variables;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.elements.variables.SetVariableBuilderTest;
import com.xy.xsql.tsql.model.elements.variables.SetVariable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SetVariableConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SetVariableConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        assert true;
    }


    private Map<SetVariable,String> model2StringMap;

    @Before
    public void init(){
        SetVariableBuilderTest builderTest = new SetVariableBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SET @myvar = 'This is a test'");

        model2StringMap.put(
                builderTest.exampleB,
                "SET @state = N'Oregon'");

        model2StringMap.put(
                builderTest.exampleC1,
                "SET  @NewBalance  =  10");

        model2StringMap.put(
                builderTest.exampleC2,
                "SET  @NewBalance  =  @NewBalance  *  10");

        model2StringMap.put(
                builderTest.exampleC3,
                "SET @NewBalance *= 10");

//        model2StringMap.put(
//                builderTest.exampleD,
//                "SET @my_variable = my_cursor");

//        model2StringMap.put(
//                builderTest.exampleE,
//                "SET @CursorVar = CURSOR SCROLL DYNAMIC");

        model2StringMap.put(
                builderTest.exampleF,
                "SET @rows = (SELECT COUNT(*) FROM Sales.Customer)");

        model2StringMap.put(
                builderTest.exampleG,
                "SET @p.X = @p.X + 1.1");

        model2StringMap.put(
                builderTest.exampleH,
                "SET @p=point.SetXY(23.5, 23.5)");

//        model2StringMap.put(
//                builderTest.exampleI,
//                "SET @p.SetXY(22, 23)");

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