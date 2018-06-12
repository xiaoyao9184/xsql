package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.EoMonthFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.EoMonth;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class EoMonthConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = EoMonthConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<EOMONTH> ::=\n" +
                        "EOMONTH ( start_date [ , month_to_add ] )");
    }

    private Map<EoMonth,String> model2StringMap;

    @Before
    public void init(){
        EoMonthFunctionTest builderTest = new EoMonthFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "EOMONTH ( @date )");
        model2StringMap.put(
                builderTest.exampleC,
                "EOMONTH ( @date, 1 )");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}