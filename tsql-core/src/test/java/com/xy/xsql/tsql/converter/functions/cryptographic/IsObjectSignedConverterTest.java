package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.IsObjectSignedFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.Is_ObjectSigned;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IsObjectSignedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsObjectSignedConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IS_OBJECTSIGNED> ::=\n" +
                        "IS_OBJECTSIGNED ( 'OBJECT' , @object_id , @class , @thumbprint )");
    }

    private Map<Is_ObjectSigned,String> model2StringMap;

    @Before
    public void init(){
        IsObjectSignedFunctionTest builderTest = new IsObjectSignedFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "IS_OBJECTSIGNED(\n" +
                        "     'OBJECT', OBJECT_ID(@objectname), 'certificate', @thumbprint\n" +
                        "     )");
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