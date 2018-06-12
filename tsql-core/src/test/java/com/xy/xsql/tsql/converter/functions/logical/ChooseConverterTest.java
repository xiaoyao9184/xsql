package com.xy.xsql.tsql.converter.functions.logical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.logical.ChooseFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.logical.Choose;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ChooseConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ChooseConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CHOOSE> ::=\n" +
                        "CHOOSE ( index , val [,...n] )");
    }

    private Map<Choose,String> model2StringMap;

    @Before
    public void init(){
        ChooseFunctionTest builderTest = new ChooseFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CHOOSE ( 3, 'Manager', 'Director', 'Developer', 'Tester' )");
        model2StringMap.put(
                builderTest.example2,
                "CHOOSE (ProductCategoryID, 'A','B','C','D','E')");
        model2StringMap.put(
                builderTest.example3,
                "CHOOSE(MONTH(HireDate),'Winter','Winter', 'Spring','Spring','Spring','Summer','Summer',\n" +
                        "     'Summer','Autumn','Autumn','Autumn','Winter')");
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